package Proc;

import Procesing.Procesing;
import loading.LoadScreen;

public class ProcessExcel extends Thread {

    private String path;
    private String lab;
    private int sheet;
    private LoadScreen ls;

    public ProcessExcel(String path, int sheet, LoadScreen ls, String lab) {
        this.path = path;
        this.sheet = sheet;
        this.ls = ls;
        this.lab = lab;
    }

    @Override
    public void run() {
        Process x = new Process(path, sheet);
        x.start();

        try {
            x.join();
        } catch (InterruptedException ex) {
            System.out.println("ProcessExcel " + ex.getMessage());
        }

        Procesing pc = new Procesing(lab);
        pc.start();
        try {
            pc.join();
        } catch (InterruptedException ex) {
            System.out.println("ProcessExcel " + ex.getMessage());
        }

        ls.loop = false;
    }

}
