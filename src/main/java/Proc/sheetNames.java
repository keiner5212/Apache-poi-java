package Proc;

import java.io.IOException;
import javax.swing.JComboBox;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.xml.sax.SAXException;

public class sheetNames extends Thread {

    private final String path;
    private final JComboBox<String> names;

    public sheetNames(String path, JComboBox<String> names) {
        this.path = path;
        this.names = names;
    }

    @Override
    public void run() {
        names.removeAllItems();
        OPCPackage p = null;
        try {
            p = OPCPackage.open(path, PackageAccess.READ);
            XLSX2CSV xlsx2csv = new XLSX2CSV(p, -1);
            xlsx2csv.getSheets(names);
        } catch (IOException | InvalidOperationException | OpenXML4JException | SAXException ex) {
            System.out.println("sheetNames " + ex.getMessage());
        } finally {
            p.revert();
        }
    }

}
