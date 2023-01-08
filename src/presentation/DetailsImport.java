/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentation;

import business.ImportBookBU;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pojo.BookImportPOJO;
import pojo.ImportDetails;

/**
 *
 * @author TIN
 */
public class DetailsImport extends javax.swing.JFrame {

    ImportBookBU import_bsn = new ImportBookBU();

    /**
     * Creates new form DetailsImport
     */
    public DetailsImport() {
        initComponents();
    }

    public void Refresh() {
        List<ImportDetails> books_dt = import_bsn.getDetails();
        Object obj[][] = new Object[books_dt.size()][6];
        String[] columnNames = {"Id", "Book Id", "Book", "Quantity", "Price of each book", "Total"};
        for (int i = 0; i < books_dt.size(); i++) {

            int id = books_dt.get(i).getImportId();
            int book_id = books_dt.get(i).getBookId();
            String book = books_dt.get(i).getBook();
            int quantity = books_dt.get(i).getQuantity();
            double price = books_dt.get(i).getPrice();
            double total = books_dt.get(i).getTotal();
            obj[i][0] = id;
            obj[i][1] = book_id;
            obj[i][2] = book;
            obj[i][3] = quantity;
            obj[i][4] = price;
            obj[i][5] = total;
        }
        DefaultTableModel tableModel = new DefaultTableModel(obj, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 ->
                        Integer.class;
                    case 1 ->
                        Integer.class;
                    case 2 ->
                        String.class;
                    case 3 ->
                        Integer.class;
                    case 4 ->
                        Double.class;
                    case 5 ->
                        Double.class;
                    default ->
                        String.class;
                };
            }
        };
        table.setModel(tableModel);
        jScrollPane1.setViewportView(table);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        List<ImportDetails> books_dt =  import_bsn.getDetails();
        Object obj[][] = new Object[books_dt.size()][6];
        String[] columnNames = {"Id", "Book Id", "Book", "Quantity", "Price of each book", "Total"};
        for (int i = 0; i < books_dt.size(); i++) {

            int id = books_dt.get(i).getImportId();
            int book_id = books_dt.get(i).getBookId();
            String book = books_dt.get(i).getBook();
            int quantity = books_dt.get(i).getQuantity();
            double price = books_dt.get(i).getPrice();
            double total = books_dt.get(i).getTotal();
            obj[i][0] = id;
            obj[i][1] = book_id;
            obj[i][2] = book;
            obj[i][3] = quantity;
            obj[i][4] = price;
            obj[i][5] = total;
        }
        DefaultTableModel tableModel = new DefaultTableModel(obj, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> Integer.class;
                    case 1 -> Integer.class;
                    case 2 -> String.class;
                    case 3 -> Integer.class;
                    case 4 -> Double.class;
                    case 5 -> Double.class;
                    default -> String.class;
                };
            }
        };
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Details");

        table.setModel(tableModel);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DetailsImport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailsImport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailsImport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailsImport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailsImport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}