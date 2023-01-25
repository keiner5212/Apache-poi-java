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
        imagenguia.setIcon(new ImageIcon("src\\main\\java\\res\\start.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        imagenguia = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        seleccionarfile = new javax.swing.JButton();
        sheets = new javax.swing.JComboBox<>();
        aceptar = new javax.swing.JButton();
        filedir = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(173, 213, 250));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(173, 213, 250));
        jPanel1.setForeground(new java.awt.Color(173, 213, 250));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jList1.setBackground(new java.awt.Color(149, 184, 246));
        jList1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 0));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Exportar historial", "Eliminar historial", "Analizar excel", "Gestionar limites" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 110));

        imagenguia.setFocusable(false);
        jPanel1.add(imagenguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 330));

        jPanel2.setBackground(new java.awt.Color(149, 250, 185));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seleccionarfile.setText("Seleccionar archivo");
        seleccionarfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarfileActionPerformed(evt);
            }
        });
        jPanel2.add(seleccionarfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(sheets, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        aceptar.setText("aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        jPanel2.add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));
        jPanel2.add(filedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 180, 330, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 130, 310));

        jPanel3.setBackground(new java.awt.Color(244, 250, 180));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 150, 310));

        jPanel4.setBackground(new java.awt.Color(225, 177, 188));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 150, 310));

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
                sheetNames x = new sheetNames(fileName, sheets);
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
        ProcessExcel x = new ProcessExcel(fileName, sheets.getSelectedIndex(), ls);
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
    private javax.swing.JLabel imagenguia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton seleccionarfile;
    private javax.swing.JComboBox<String> sheets;
    // End of variables declaration//GEN-END:variables
}
