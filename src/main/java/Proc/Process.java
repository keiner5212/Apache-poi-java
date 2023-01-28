package Proc;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;

public class Process extends Thread {

    private String path;
    private int sheet;

    public Process(String path, int sheet) {
        this.path = path;
        this.sheet = sheet;
    }

    @Override
    public void run() {
        OPCPackage p = null;
        try {
            p = OPCPackage.open(path, PackageAccess.READ);
            XLSX2CSV xlsx2csv = new XLSX2CSV(p, -1);
            xlsx2csv.process(sheet);
        } catch (Exception ex) {
            System.out.println("Process " + ex.getMessage());
        } finally {
            p.revert();
        }
    }

}
