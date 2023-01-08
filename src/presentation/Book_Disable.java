/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentation;

import business.book_business;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pojo.book;

/**
 *
 * @author TIN
 */
public class Book_Disable extends javax.swing.JFrame {

    book_business book_bsn = new book_business();

    /**
     * Creates new form Book_Disable
     */
    public Book_Disable() {
        initComponents();
    }

    public void ShowBook() {
        List<book> books = book_bsn.getAll();

        Object obj[][] = new Object[books.size()][8];
        String[] columnNames = {"Id", "Book", "Price", "Quantity", "Author", "Publisher", "Category", "isActive"};
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            boolean isActive = books.get(i).isIsActive();
            if (isActive) {
                count++;
                continue;
            }
            int id = books.get(i).getId();
            String name = books.get(i).getName();
            Double price = books.get(i).getPrice();
            int quantity = books.get(i).getQuantity();
            String author = books.get(i).getAuthor();
            String publisher = books.get(i).getPublisher();
            String category = books.get(i).getCategory();

            obj[i][0] = id;
            obj[i][1] = name;
            obj[i][2] = price;
            obj[i][3] = quantity;
            obj[i][4] = author;
            obj[i][5] = publisher;
            obj[i][6] = category;
            obj[i][7] = isActive;
        }
        int size = books.size() - count;
        Object newObj[][] = new Object[size][8];
        int j = 0;
        for (int i = 0; i < books.size(); i++) {
            if (obj[i][0] != null) {
                System.out.println(obj[i][0]);
                newObj[j] = obj[i];
                j++;
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(newObj, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Double.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    case 6:
                        return String.class;
                    case 7:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        table.setAutoCreateRowSorter(true);
        table.setModel(tableModel);
        jScrollPane1.setViewportView(table);
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                if (column == 7) {
                    int data = Integer.parseInt(model.getValueAt(row, 0).toString());
                    book_bsn.Enable_book(data);

                    return;
                }
            }
        });
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
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
        List<book> books = book_bsn.getAll();
        books = book_bsn.getAll();
        Object obj[][] = new Object[books.size()][8];
        String[] columnNames = {"Id", "Book", "Price", "Quantity", "Author", "Publisher", "Category", "isActive"};
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            boolean isActive = books.get(i).isIsActive();
            if (isActive) {
                count++;
                continue;
            }
            int id = books.get(i).getId();
            String name = books.get(i).getName();
            Double price = books.get(i).getPrice();
            int quantity = books.get(i).getQuantity();
            String author = books.get(i).getAuthor();
            String publisher = books.get(i).getPublisher();
            String category = books.get(i).getCategory();

            obj[i][0] = id;
            obj[i][1] = name;
            obj[i][2] = price;
            obj[i][3] = quantity;
            obj[i][4] = author;
            obj[i][5] = publisher;
            obj[i][6] = category;
            obj[i][7] = isActive;
        }
        int size = books.size()-count;
        Object newObj[][] = new Object[size][8];
        int j = 0;
        for(int i = 0;i<books.size();i++){
            if(obj[i][0]!=null){

                newObj[j]=obj[i];
                j++;
            }

        }
        table = new javax.swing.JTable();
        back = new javax.swing.JButton();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Disable book");

        DefaultTableModel tableModel = new DefaultTableModel(newObj, columnNames){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                    return Integer.class;
                    case 1:
                    return String.class;
                    case 2:
                    return Double.class;
                    case 3:
                    return Integer.class;
                    case 4:
                    return String.class;
                    case 5:
                    return String.class;
                    case 6:
                    return String.class;
                    case 7:
                    return Boolean.class;
                    default:
                    return String.class;
                }
            }
        };
        table.setModel(tableModel);
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel)e.getSource();
                String columnName = model.getColumnName(column);
                if(column == 7){
                    int data =Integer.parseInt( model.getValueAt(row, 0).toString());
                    book_bsn.Enable_book(data);

                    return;
                }
            }
        });
        jScrollPane1.setViewportView(table);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back)
                    .addComponent(refresh))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        ShowBook();
    }//GEN-LAST:event_refreshActionPerformed

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
            java.util.logging.Logger.getLogger(Book_Disable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book_Disable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book_Disable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book_Disable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book_Disable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refresh;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}