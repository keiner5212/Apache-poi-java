package GUI;

import Proc.ProcessExcel;
import Proc.sheetNames;
import java.awt.Color;
import java.io.File;
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
        seleccionarfile.setIcon(new ImageIcon("src\\main\\java\\res\\subir.png"));
        export.setVisible(false);
        analizar.setVisible(false);
        limites.setVisible(false);
        aceptar.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu = new javax.swing.JList<>();
        imagenguia = new javax.swing.JLabel();
        analizar = new javax.swing.JPanel();
        seleccionarfile = new javax.swing.JButton();
        sheets = new javax.swing.JComboBox<>();
        aceptar = new javax.swing.JButton();
        dir = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lab = new javax.swing.JTextField();
        limites = new javax.swing.JPanel();
        export = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(173, 213, 250));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        screen.setBackground(new java.awt.Color(173, 213, 250));
        screen.setForeground(new java.awt.Color(173, 213, 250));
        screen.setToolTipText("");
        screen.setFocusable(false);
        screen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(149, 184, 246));
        menu.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        menu.setForeground(new java.awt.Color(0, 0, 0));
        menu.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Exportar historial", "Eliminar historial", "Analizar excel", "Gestionar limites" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        menu.setSelectionBackground(new java.awt.Color(202, 172, 249));
        menu.setSelectionForeground(new java.awt.Color(0, 0, 0));
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(menu);

        screen.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 110));

        imagenguia.setFocusable(false);
        screen.add(imagenguia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        analizar.setBackground(new java.awt.Color(149, 250, 185));
        analizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seleccionarfile.setBackground(new java.awt.Color(255, 255, 255));
        seleccionarfile.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarfile.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        seleccionarfile.setBorderPainted(false);
        seleccionarfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seleccionarfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarfileActionPerformed(evt);
            }
        });
        analizar.add(seleccionarfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 30, 30));

        sheets.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        analizar.add(sheets, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        aceptar.setBackground(new java.awt.Color(149, 184, 246));
        aceptar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        aceptar.setForeground(new java.awt.Color(0, 0, 0));
        aceptar.setText("Aceptar");
        aceptar.setToolTipText("Aceptar");
        aceptar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        aceptar.setBorderPainted(false);
        aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        analizar.add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 60, 20));

        dir.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        dir.setForeground(new java.awt.Color(0, 0, 0));
        dir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        analizar.add(dir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 480, 30));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Selecciona el archivo que quieres analizar:");
        analizar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Ingresa el nombre del laboratorio:");
        analizar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Selecciona la pagina que quieres analizar:");
        analizar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        lab.setForeground(new java.awt.Color(153, 153, 153));
        lab.setText("Escribe aqui...");
        lab.setToolTipText("Escribe aqui...");
        lab.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                labFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                labFocusLost(evt);
            }
        });
        analizar.add(lab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, -1));

        screen.add(analizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 580, 380));

        getContentPane().add(screen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 400));

        limites.setBackground(new java.awt.Color(177, 134, 241));
        limites.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(limites, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 560, 320));

        export.setBackground(new java.awt.Color(225, 177, 188));
        export.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(export, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 560, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarfileActionPerformed
        int result = fc.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {
            LoadScreen ls = new LoadScreen(false);
            ls.start();
            String fileName = fc.getSelectedFile().getAbsolutePath();
            dir.setText(fileName);
            aceptar.setVisible(true);
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
        ProcessExcel x = new ProcessExcel(fileName, sheets.getSelectedIndex(), ls, lab.getText());
        x.start();
    }//GEN-LAST:event_aceptarActionPerformed

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        export.setVisible(false);
        analizar.setVisible(false);
        limites.setVisible(false);
        imagenguia.setVisible(false);
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

    private void labFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_labFocusGained
        if (lab.getText().equalsIgnoreCase("Escribe aqui...")) {
            lab.setText("");
            lab.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_labFocusGained

    private void labFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_labFocusLost
        if (lab.getText().trim().equalsIgnoreCase("")) {
            lab.setText("Escribe aqui...");
            lab.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_labFocusLost

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
    private javax.swing.JLabel dir;
    private javax.swing.JPanel export;
    private javax.swing.JLabel imagenguia;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lab;
    private javax.swing.JPanel limites;
    private javax.swing.JList<String> menu;
    private javax.swing.JPanel screen;
    private javax.swing.JButton seleccionarfile;
    private javax.swing.JComboBox<String> sheets;
    // End of variables declaration//GEN-END:variables
}
