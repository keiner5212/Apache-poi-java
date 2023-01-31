package Procesing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Procesing extends Thread {

    private ArrayList<ArrayList<String>> tabla;
    private ArrayList<ArrayList<String>> tablaUbicaciones;
    private ArrayList<ArrayList<String>> historial;
    private final ArrayList<Integer> listaInteres;
    private int headerPos = 0;
    private final String lab;
    private final String headersOut[] = {
        "LABORATORIO", "UBICACION", "SAMPLE ID", "EQUIPO", "MODELO", "COMPONENTE",
        "TIPO ACEITE", "FECHA TOMA DE MUESTRA", "FECHA ANÁLISIS DE MUESTRA", "HORAS ACEITE",
        "CAMBIO ACEITE", "COMENTARIOS", "V100C", "NIT",
        "OXI", "SUL", "TAN", "TBN",
        "MAGNESIO", "CALCIO", "ZINC", "FÓSFORO",
        "BORO", "FUEL", "VANADIO", "AGUA(PPM)",
        "GLYCOL", "SODIO", "POTASIO", "SILICIO",
        "HOLLÍN", "ALUMINIO", "CROMO", "COBRE",
        "HIERRO", "PLOMO", "ESTAÑO", "MANGANESO",
        "MOLIBDENO", "NIQUEL", "ISO"
    };

    private final XSSFWorkbook workbook = new XSSFWorkbook();
    private final XSSFCellStyle styleGris = workbook.createCellStyle();
    private final XSSFCellStyle styleAmarillo = workbook.createCellStyle();
    private final XSSFCellStyle styleRojo = workbook.createCellStyle();

    public Procesing(String lab) {
        tabla = new ArrayList<>();
        historial = new ArrayList<>();
        listaInteres = new ArrayList<>();
        this.lab = lab;
        tablaUbicaciones = new ArrayList<>();

        styleGris.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        styleGris.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont negrilla = workbook.createFont();
        negrilla.setBold(true);
        styleGris.setFont(negrilla);

        styleAmarillo.setFillForegroundColor(IndexedColors.YELLOW.index);
        styleAmarillo.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        styleRojo.setFillForegroundColor(IndexedColors.RED.index);
        styleRojo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    @Override
    public void run() {
        boolean newH = false;
        try {
            cargar();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            cargarUbicaciones();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        getHeader();
        getInteresPositionsLab1();
        if (listaInteres.size() < 30) {
            listaInteres.clear();
            getInteresPositionsLab2();
        }
        File arc = new File("src\\main\\java\\temp\\history.data");
        if (!arc.exists()) {
            historial.add(new ArrayList<>());
            historial.get(0).addAll(Arrays.asList(headersOut));
            newH = true;
        } else {
            try {
                cargarHistorial();
            } catch (IOException | ClassNotFoundException ex) {
                historial = new ArrayList<>();
                System.out.println("save " + ex.getMessage());
            }
        }

        for (int i = headerPos + 1; i < tabla.size(); i++) {
            historial.add(new ArrayList<>());
            historial.get(historial.size() - 1).add(lab.equals("Escribe aqui...") ? "-" : lab);
            String ubi = searhUbicacion(
                    tabla.get(i).get(listaInteres.get(1)),
                    tabla.get(i).get(listaInteres.get(2)));
            historial.get(historial.size() - 1).add(ubi);
            for (int j : listaInteres) {
                if (j == 9999) {
                    historial.get(historial.size() - 1).add("-");
                } else {
                    if (listaInteres.indexOf(j) == 9) {
                        historial.get(historial.size() - 1).add(" ");
                    }
                    historial.get(historial.size() - 1).add(tabla.get(i).get(j));
                }
            }
        }
        save(historial, "history");
        deleteFile("tabla");
        ArrayList<String> maxLDato = new ArrayList<>();
        for (String headersOut1 : headersOut) {
            maxLDato.add("");
        }
        Sheet sheet = workbook.createSheet("RES");
        int numeroRenglon = 0;

        for (ArrayList<String> arrayList : historial) {
            Row row = sheet.createRow(numeroRenglon++);
            int numeroCelda = 0;
            for (String dato : arrayList) {
                if (dato.length() > maxLDato.get(numeroCelda).length()) {
                    maxLDato.set(numeroCelda, dato);
                }
                Cell cell = row.createCell(numeroCelda++);
                double datoD;
                String datoS = dato;
                try {
                    dato = dato.replaceAll(",", ".");
                    datoD = Double.parseDouble(dato);
                    cell.setCellValue(datoD);
                } catch (NumberFormatException e) {
                    cell.setCellValue(datoS);
                }
                if (numeroRenglon == 1 && newH) {
                    cell.setCellStyle(styleGris);
                }
            }
        }
        for (int i = 0; i < headersOut.length; i++) {
            int tamano = 6500;
            if (maxLDato.get(i).length() < 10) {
                tamano = maxLDato.get(i).length() * 500;
            }
            sheet.setColumnWidth(i, tamano);
        }
        saveExcel();
    }

    public void saveExcel() {
        try {
            FileOutputStream out = new FileOutputStream(new File("src\\main\\java\\outs\\history.xlsx"));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFile(String name) {
        File arc = new File("src\\main\\java\\temp\\" + name + ".data");
        if (arc.exists()) {
            arc.delete();
            System.out.println("success...");
        }
    }

    public String searhUbicacion(String id, String model) {
        String out = "-";
        for (ArrayList<String> tablaUbicacione : tablaUbicaciones) {
            if (!tablaUbicacione.get(0).equals("4007")) {
                if (tablaUbicacione.get(0).equals(id) && tablaUbicacione.get(2).equals(model)) {
                    out = tablaUbicacione.get(1);
                }
            }
        }
        return out;
    }

    public void save(ArrayList<ArrayList<String>> tabla, String name) {
        try {
            FileOutputStream fout = new FileOutputStream("src\\main\\java\\temp\\" + name + ".data");
            try (ObjectOutputStream writeStream = new ObjectOutputStream(fout)) {
                writeStream.writeObject(tabla);
            }
            System.out.println("success...");
        } catch (IOException e) {
            System.out.println("save " + e.getMessage());
        }
    }

    private void cargar() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream("src\\main\\java\\temp\\tabla.data");
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        tabla = (ArrayList<ArrayList<String>>) objStream.readObject();
        objStream.close();
    }

    private void cargarUbicaciones() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream("src\\main\\java\\temp\\ubicaciones.data");
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        tablaUbicaciones = (ArrayList<ArrayList<String>>) objStream.readObject();
        objStream.close();
    }

    private void cargarHistorial() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream("src\\main\\java\\temp\\history.data");
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        historial = (ArrayList<ArrayList<String>>) objStream.readObject();
        objStream.close();
    }

    private void getHeader() {
        for (int i = 0; i < tabla.size(); i++) {
            for (int j = 0; j < tabla.get(i).size(); j++) {
                if (tabla.get(i).get(j).contains("ID")) {
                    headerPos = i;
                    return;
                }
            }
        }
    }

    private void getInteresPositionsLab2() {
        String headers[] = {
            "No. de control de laboratorio", "ID de equipo",
            "Model", "Compartimento", "Fluid Weight",
            "Fecha de Toma de Muestra", "Fecha de laboratorio", "Meter on Fluid",
            "Fluido Cambiado", "V100", "NIT", "OXI", "SUL", "TAN",
            "TBN", "Mg", "Ca", "Zn", "P", "B", "PFc", "V",
            "W", "Glycol-%", "Na", "K", "Si", "ST", "Al", "Cr", "Cu",
            "Fe", "Pb", "Sn", "Mn", "Mo", "Ni", "ISO"
        };
        for (String header : headers) {
            int index;
            if (!header.equals("Glycol-%")) {
                if (header.equals("NIT")
                        || header.equals("P")
                        || header.equals("B")
                        || header.equals("W")
                        || header.equals("OXI")
                        || header.equals("SUL")
                        || header.equals("TAN")
                        || header.equals("TBN")
                        || header.equals("Mg")
                        || header.equals("Ca")
                        || header.equals("K")
                        || header.equals("Si")
                        || header.equals("V")
                        || header.equals("ST")
                        || header.equals("Al")
                        || header.equals("Cr")
                        || header.equals("Cu")
                        || header.equals("Fe")
                        || header.equals("Pb")
                        || header.equals("Sn")
                        || header.equals("Mn")
                        || header.equals("Mo")
                        || header.equals("Ni")
                        || header.equals("ISO")) {
                    index = searchi(header);
                } else {
                    index = searchc(header);
                }
            } else {
                index = 9999;
            }
            if (index != -1) {
                listaInteres.add(index);
            }
        }
    }

    private void getInteresPositionsLab1() {
        String headers[] = {
            "SAMPLE ID", "UNIT ID", "UNIT MODEL",
            "COMPONENT LOCATION", "OIL WEIGHT",
            "DATE TAKEN", "DATE ANALIZED",
            "HOURS OIL", "OIL CHANGED",
            "Visc_Measure", "NIT -", "OXI -",
            "Sulfation", "TAN (", "TBN (", "Mg (",
            "Ca (", "Zn", "P", "B (", "Fuel Dilution",
            "V (ppm)", "Water-ppm", "Glycol", "Na (", "K (", "Si (",
            "Soot-%wt", "Al (", "Cr (", "Cu (", "Fe (", "Pb (",
            "Sn (", "Mn (", "Mo (", "Ni (", "ISOCODE"};
        for (String header : headers) {
            int index;
            if (header.equals("P") || header.equals("Zn")) {
                index = searchi(header);
            } else {
                index = searchc(header);
            }
            if (index != -1) {
                listaInteres.add(index);
            }
        }
    }

    private int searchc(String target) {
        for (int i = 0; i < tabla.get(headerPos).size(); i++) {
            if (tabla.get(headerPos).get(i).contains(target)) {
                return i;
            }
        }
        return -1;
    }

    private int searchi(String target) {
        for (int i = 0; i < tabla.get(headerPos).size(); i++) {
            if (tabla.get(headerPos).get(i).trim().equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

}
