package GUI;

import Proc.ProcessExcel;
import Proc.sheetNames;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import loading.LoadScreen;

/**
 *
 * @author Administrator
 */
public class main extends javax.swing.JFrame {

    private JFileChooser fc = new JFileChooser();

    public main() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        fc.setFileFilter(new FileNameExtensionFilter("xlsx & xlsm", "xlsm", "xlsx"));
        String usr = System.getenv("USERNAME");
        fc.setCurrentDirectory(new File("C:\\Users\\" + usr + "\\Downloads"));
        ImageIcon iconobtn = new ImageIcon("src\\main\\java\\res\\icon.png");
        setIconImage(iconobtn.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        seleccionarfile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        aceptar = new javax.swing.JButton();
        filedir = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seleccionarfile.setText("Seleccionar archivo");
        seleccionarfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarfileActionPerformed(evt);
            }
        });
        getContentPane().add(seleccionarfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        aceptar.setText("aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        getContentPane().add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));
        getContentPane().add(filedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 330, 20));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarfileActionPerformed
        int result = fc.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {
            LoadScreen ls = new LoadScreen(false);
            ls.start();
            String fileName = fc.getSelectedFile().getAbsolutePath();
            filedir.setText(fileName);

            try {
                sheetNames x = new sheetNames(fileName, jComboBox1);
                x.start();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_seleccionarfileActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        String fileName = fc.getSelectedFile().getAbsolutePath();
        LoadScreen ls = new LoadScreen(true);
        ls.start();
        ProcessExcel x = new ProcessExcel(fileName, jComboBox1.getSelectedIndex(), ls);
        x.start();
    }//GEN-LAST:event_aceptarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            cargar();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for (int i = 0; i < tabla.size(); i++) {
            for (int j = 0; j < tabla.get(i).size(); j++) {
                System.out.println(tabla.get(i).get(j)+"  ("+i+","+j+")");
            }
            System.out.println("\n");
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private ArrayList<ArrayList<String>> tabla;
    public void cargar() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream("src\\main\\java\\temp\\tabla.data");
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        tabla = (ArrayList<ArrayList<String>>) objStream.readObject();
        objStream.close();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.out.println(ex.getMessage());
                }
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel filedir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JButton seleccionarfile;
    // End of variables declaration//GEN-END:variables
}
