package loading;

public class LoadScreen extends Thread{
    public boolean loop;

    public LoadScreen(boolean loop) {
        this.loop = loop;
    }
    
    @Override
    public void run() {
        Screen x=new Screen();
        x.setVisible(true);
        do {            
            for (int i = 0; i < 100; i+=2) {
                x.barra.setValue(i);
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } while (loop);
        x.setVisible(false);
    }
    
}
