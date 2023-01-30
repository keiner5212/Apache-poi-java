package Procesing;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
    private ArrayList<ArrayList<String>> historial;
    private ArrayList<Integer> listaInteres;
    private int headerPos = 0;
    private String lab;
    private  String headersOut[] = {
            "Sample Id", "Equipo", "Modelo", "Componente",
            "Tipo Aceite", "Fecha Toma de muestra", "Fecha Análisis de muestra", "Horas Aceite",
            "Cambio Aceite", "Comentarios", "V100C", "NIT",
            "OXI", "SUL", "TAN", "TBN",
            "Magnesio", "Calcio", "Zinc", "Fósforo",
            "Boro", "Fuel", "Vanadio", "Agua(ppm)",
            "Glycol", "Sodio", "Potasio", "Silicio",
            "Hollín", "Aluminio", "Cromo", "Cobre",
            "Hierro", "Plomo", "Estaño", "Manganeso",
            "Molibdeno","Niquel","ISO"
        };
    
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFCellStyle styleGris = workbook.createCellStyle();
    private XSSFCellStyle styleAmarillo = workbook.createCellStyle();
    private XSSFCellStyle styleRojo = workbook.createCellStyle();
    
    public Procesing(String lab) {
        tabla = new ArrayList<>();
        historial = new ArrayList<>();
        listaInteres = new ArrayList<>();
        this.lab = lab;
        
        styleGris.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        styleGris.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont negrilla=workbook.createFont();
        negrilla.setBold(true);
        styleGris.setFont(negrilla);
        
        styleAmarillo.setFillForegroundColor(IndexedColors.YELLOW.index);
        styleAmarillo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        styleRojo.setFillForegroundColor(IndexedColors.RED.index);
        styleRojo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    @Override
    public void run() {
        boolean newH=false;
        try {
            cargar();
        } catch (IOException | ClassNotFoundException ex) {
            tabla = new ArrayList<>();
            System.out.println(ex.getMessage());
        }
        getHeader();
        getInteresPositionsLab1();
        if (listaInteres.size() < 30) {
            listaInteres.clear();
            getInteresPositionsLab2();
        }
        File arc = new File("src\\main\\java\\temp\\history.data");
        if (!arc.exists()){
            historial.add(new ArrayList<>());
            for (String header : headersOut) {
                historial.get(0).add(header);
            }
            newH=true;
        }else{
            try {
                cargarHistorial();
            } catch (IOException | ClassNotFoundException ex) {
                historial = new ArrayList<>();
                System.out.println("save " + ex.getMessage());
            }
        }
        for (int i = headerPos+1; i <tabla.size() ; i++) {
            historial.add(new ArrayList<>());
            for (int j: listaInteres) {
                if (listaInteres.indexOf(j)==9) {
                    historial.get(historial.size()-1).add(" ");
                }
                historial.get(historial.size()-1).add(tabla.get(i).get(j));
            }
        }
        save(historial, "history");
        deleteFile("tabla");
        
        Sheet sheet = workbook.createSheet("RES");
        int numeroRenglon = 0;
        for (ArrayList<String> arrayList : historial) {
            Row row = sheet.createRow(numeroRenglon++);
            int numeroCelda = 0;
            for (String dato : arrayList) {
                Cell cell = row.createCell(numeroCelda++);
                double datoD;
                String datoS=dato;
                try {
                    dato = dato.replaceAll(",",".");
                    datoD=Double.parseDouble(dato);
                    cell.setCellValue(datoD);
                } catch (Exception e) {
                    cell.setCellValue(datoS);
                }
                if (numeroRenglon==1 && newH) {
                    cell.setCellStyle(styleGris);
                }
            }
        }
        for (int i = 0; i < headersOut.length; i++) {
            int tamano = 6500;
            sheet.setColumnWidth(i, tamano);
        }
        saveExcel();
    }
    
    public void saveExcel(){
        try {
            //Se genera el documento
            FileOutputStream out = new FileOutputStream(new File("src\\main\\java\\outs\\history.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteFile(String name) {
        File arc = new File("src\\main\\java\\temp\\"+name+".data");
        if (arc.exists()) {
            arc.delete();
            System.out.println("success...");
        }
    }
    
    public void save(ArrayList<ArrayList<String>> tabla,String name) {
        try {
            FileOutputStream fout = new FileOutputStream("src\\main\\java\\temp\\"+name+".data");
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
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", "", "", "",
            "", ""
        };
        for (String header : headers) {
            int index;
            if (header.equalsIgnoreCase("") || header.equalsIgnoreCase("")) {
                index = searchi(header);
            }else{
                index = searchc(header);
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
            if (header.equalsIgnoreCase("P") || header.equalsIgnoreCase("Zn")) {
                index = searchi(header);
            }else{
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
            if (tabla.get(headerPos).get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

}
