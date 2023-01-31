package Proc;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.xml.sax.SAXException;

public class Process extends Thread {

    private final String path;
    private final int sheet;
    private final String name;

    public Process(String path, int sheet,String name) {
        this.path = path;
        this.sheet = sheet;
        this.name=name;
    }

    @Override
    public void run() {
        OPCPackage p = null;
        try {
            p = OPCPackage.open(path, PackageAccess.READ);
            XLSX2CSV xlsx2csv = new XLSX2CSV(p, -1,name);
            xlsx2csv.process(sheet);
        } catch (IOException | InvalidOperationException | OpenXML4JException | SAXException ex) {
            System.out.println("Process " + ex.getMessage());
        } finally {
            p.revert();
        }
    }

}
