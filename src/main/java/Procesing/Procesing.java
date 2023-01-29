package Procesing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Procesing extends Thread {

    private ArrayList<ArrayList<String>> tabla;
    private ArrayList<ArrayList<String>> tablaRes;
    private ArrayList<Integer> listaInteres;
    private int headerPos = 0;
    private String lab;

    public Procesing(String lab) {
        tabla = new ArrayList<>();
        tablaRes = new ArrayList<>();
        listaInteres = new ArrayList<>();
        this.lab = lab;
    }

    @Override
    public void run() {
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
    }

    private void cargar() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream("src\\main\\java\\temp\\tabla.data");
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        tabla = (ArrayList<ArrayList<String>>) objStream.readObject();
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
            int index = search(header);
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
            "Visc_Measure", "NIT", "OXI",
            "Sulfation", "TAN", "TBN", "Mg",
            "Ca", "Zn", "P", "B", "Fuel Dilution",
            "V", "Water", "Glycol", "Na", "K", "Si",
            "Soot", "Al", "Cr", "Cu", "Fe", "Pb",
            "Sn", "Mn", "Mo", "Ni", "ISOCODE"};
        for (String header : headers) {
            int index = search(header);
            if (index != -1) {
                listaInteres.add(index);
            }
        }
    }

    private int search(String target) {
        for (int i = 0; i < tabla.get(headerPos).size(); i++) {
            if (tabla.get(headerPos).get(i).contains(target)) {
                return i;
            }
        }
        return -1;
    }

}
