/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author Евгения
 */
public class InsertPanel extends javax.swing.JPanel {
String path=null;
 Connection conn = null;
 Statement stat = null;
 ResultSet res = null;
 String userName = "root";
 String password = "Password";
 String url = "jdbc:mysql://localhost:3306/testdb";
 File f = null;
    /**
     * Creates new form InsertPanel
     */
    public InsertPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("Выбрать документ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jButton2.setText("Копировать");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("HashTest");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Вставка нового препарата в базу
//         Connection conn = null;
//    try 
//    {
//    String userName = "root";
//    String password = "Password";
//    String url = "jdbc:mysql://localhost:3306/testdb";
//    Class.forName ("com.mysql.jdbc.Driver").newInstance();
//    conn = DriverManager.getConnection (url, userName, password);
//    Statement stat = conn.createStatement();
//    String SQL =(" INSERT INTO Cures VALUES (8, '" +jTextField1.getText()+ "', '"+jTextField2.getText()+ "') ");
//    
//    stat.executeQuery(SQL);
//
//    stat.close();
//    conn.close();
//    }catch (Exception e )
//    {
//    System.err.println (e.getMessage());
//    e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Запись добавлена успешно");
//    
//    } 
         // Выбрать файл и копировать его 
        JFileChooser fileChooser = new JFileChooser();
         fileChooser.showOpenDialog(this);
         f = fileChooser.getSelectedFile();
//--- проверить, что это файл и он доступен для чтения
          if ( !f.isFile() || !f.canRead() ) 
          {
              System.out.println("Файл "+f.getName()+" проблемы какие-то.");
              return;
          }
          
           path = f.getAbsolutePath();
           path = path.replace("\\", "/");
           jTextField2.setText(path);
    

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Копирование файла
           try {
    //РАБОТАЕТ!
         CopyFiles.copy(f, new File("C:\\Users\\Евгения\\Desktop\\Диплом\\trial\\" + f.getName()));
     } catch (IOException ex) {
         Logger.getLogger(ButtonTest.class.getName()).log(Level.SEVERE, null, ex);
     }
     try 
    {
    Class.forName ("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection (url, userName, password);
    stat = conn.createStatement();  
    String SQL =(" INSERT INTO documentsinfo (DocumentID, CureID, CategoryID, OriginalPath, TranslatedPath, Status, TranslatorID, InsertDate, TranslationDate)\n" +
                 " VALUES (NULL, 1, 1, '"+ path +"', NULL, 2, NULL, NOW(), NULL)");
    stat.executeUpdate(SQL);
    stat.close();
    }catch (Exception e )
    {
    System.err.println (e.getMessage());
        }
     finally {
        try { if ( conn != null ) { conn.close(); } } catch (Exception ignore) {}
    }   JOptionPane.showMessageDialog(null, "Запись добавлена успешно");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // Проверка на написание символов. Валидация.
        char a = evt.getKeyChar();
          if (!Character.isDigit(a)
              && (a != '\b')) {
            evt.consume(); 
          }

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // jcombo
        HashMap <String, Integer > tap = new HashMap <>();
        tap.put("lol", 1);
        tap.put("lol2", 2);
        tap.put("tomat",3);
        System.out.println(tap.keySet());
        for (Map.Entry<String, Integer> entry : tap.entrySet())
        {
            System.out.println(entry.getKey()+""+entry.getValue());
            jComboBox1.addItem(entry.getKey());
        }
    
       
       //System.out.println(tap.get("lol"));
    }//GEN-LAST:event_jButton3ActionPerformed
 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ButtonTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ButtonTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ButtonTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ButtonTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
