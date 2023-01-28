package Procesing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Procesing extends Thread {

    private ArrayList<ArrayList<String>> tabla;
    private int header = 0;

    public Procesing() {
        tabla = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            cargar();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        getHeader();
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
                if (tabla.get(i).get(j).equalsIgnoreCase("id")) {
                    header = i;
                    return;
                }
            }
        }
    }

}
