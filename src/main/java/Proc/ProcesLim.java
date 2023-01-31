package Proc;

import loading.LoadScreen;

/**
 *
 * @author Administrator
 */
public class ProcesLim extends Thread  {
    private final String path;
    private final int sheet;
    private final LoadScreen ls;

    public ProcesLim(String path, int sheet, LoadScreen ls) {
        this.path = path;
        this.sheet = sheet;
        this.ls = ls;
    }

    @Override
    public void run() {
        Process x = new Process(path, sheet,"limites");
        x.start();

        try {
            x.join();
        } catch (InterruptedException ex) {
            System.out.println("ProcessExcel " + ex.getMessage());
        }

        ls.loop = false;
    }
}
