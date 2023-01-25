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
import javax.swing.JOptionPane;
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
        setTitle("Analisis excels");
        setLocationRelativeTo(null);
        fc.setFileFilter(new FileNameExtensionFilter("xlsx & xlsm", "xlsm", "xlsx"));
        String usr = System.getenv("USERNAME");
        fc.setCurrentDirectory(new File("C:\\Users\\" + usr + "\\Downloads"));
        ImageIcon iconobtn = new ImageIcon("src\\main\\java\\res\\icon.png");
        setIconImage(iconobtn.getImage());
        imagenguia.setIcon(new ImageIcon("src\\main\\java\\res\\start.png"));                                 
        export.setVisible(false);                         
        analizar.setVisible(false);                         
        limites.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screen = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JList<>();
        imagenguia = new javax.swing.JLabel();
        analizar = new javax.swing.JPanel();
        seleccionarfile = new javax.swing.JButton();
        sheets = new javax.swing.JComboBox<>();
        aceptar = new javax.swing.JButton();
        filedir = new javax.swing.JLabel();
        limites = new javax.swing.JPanel();
        export = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(173, 213, 250));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        screen.setBackground(new java.awt.Color(173, 213, 250));
        screen.setForeground(new java.awt.Color(173, 213, 250));
        screen.setToolTipText("");
        screen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        screen.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        screen.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        menu.setBackground(new java.awt.Color(149, 184, 246));
        menu.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        menu.setForeground(new java.awt.Color(0, 0, 0));
        menu.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Exportar historial", "Eliminar historial", "Analizar excel", "Gestionar limites" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(menu);

        screen.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 110));

        imagenguia.setFocusable(false);
        screen.add(imagenguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        getContentPane().add(screen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 330));

        analizar.setBackground(new java.awt.Color(149, 250, 185));
        analizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seleccionarfile.setText("Seleccionar archivo");
        seleccionarfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarfileActionPerformed(evt);
            }
        });
        analizar.add(seleccionarfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        analizar.add(sheets, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        aceptar.setText("aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        analizar.add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));
        analizar.add(filedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 180, 330, 20));

        getContentPane().add(analizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 130, 310));

        limites.setBackground(new java.awt.Color(177, 134, 241));
        limites.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(limites, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 150, 310));

        export.setBackground(new java.awt.Color(225, 177, 188));
        export.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(export, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 150, 310));

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

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        export.setVisible(false);                         
        analizar.setVisible(false);                         
        limites.setVisible(false);
        switch (menu.getSelectedIndex()) {
            case 0:                       
                export.setVisible(true);                              
                JOptionPane.showMessageDialog(null, "guardar historial");   
                break;
            case 1:                       
                export.setVisible(true);                              
                JOptionPane.showMessageDialog(null, "eliminar historial");     
                break;
            case 2:                      
                analizar.setVisible(true); 
                break;
            case 3:                      
                limites.setVisible(true); 
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_menuMouseClicked
    
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
    private javax.swing.JPanel analizar;
    private javax.swing.JPanel export;
    private javax.swing.JLabel filedir;
    private javax.swing.JLabel imagenguia;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel limites;
    private javax.swing.JList<String> menu;
    private javax.swing.JPanel screen;
    private javax.swing.JButton seleccionarfile;
    private javax.swing.JComboBox<String> sheets;
    // End of variables declaration//GEN-END:variables
}
