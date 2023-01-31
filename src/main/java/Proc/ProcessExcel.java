package Proc;

import Procesing.Procesing;
import loading.LoadScreen;

public class ProcessExcel extends Thread {

    private final String path;
    private final String lab;
    private final int sheet;
    private final LoadScreen ls;
    private final String name;

    public ProcessExcel(String path, int sheet, LoadScreen ls, String lab,String name) {
        this.path = path;
        this.sheet = sheet;
        this.ls = ls;
        this.lab = lab;
        this.name=name;
    }

    @Override
    public void run() {
        Process x = new Process(path, sheet,name);
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
