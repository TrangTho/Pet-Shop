/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import trangbtt.dao.InvoiceSerDAO;
import trangbtt.db.MyConnection;
import trangbtt.db.Valid;
import trangbtt.dto.InvoiceDetail;

/**
 *
 * @author ASUS
 */
public class BuySerJframe extends javax.swing.JFrame {

    

    public BuySerJframe() {
        initComponents();
        loadTable();
        loadOwner();
        DefaultTableModel model = new DefaultTableModel(data, header);
        tblBuySerView.setModel(model);
    }
    Vector header = null;
    Vector data = new Vector<>();
    JComboBox list1 = new JComboBox();
    JComboBox list2 = new JComboBox();

    void loadTable() {
        header = new Vector();
        header.add("Service");
        header.add("Pet");
    }

    public void loadOwner() {
        Connection conn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            String sql = "select Phone From Owner";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("Phone");
                cbxOwner.addItem(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (preStm != null) {
                        preStm.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void uploadPet(String Owner) {
        list1 = new JComboBox();
        Connection cn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {

            String sql = "select PetID, Name from Pet where Owner LIKE ?";
            cn = MyConnection.getMyConnection();
            pre = cn.prepareStatement(sql);
            pre.setString(1, "%" + Owner + "%");
            rs = pre.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String id = rs.getString("PetID");
                list1.addItem(id + "-" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pre != null) {
                        pre.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void uploadSer() {
        list2 = new JComboBox();
        Connection cn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            String sql = "select ServiceID, Name, Price, TypeID from Service";
            cn = MyConnection.getMyConnection();
            pre = cn.prepareStatement(sql);
            rs = pre.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String id = rs.getString("ServiceID");
                int price = rs.getInt("Price");
                String typeID = rs.getString("TypeID");
                list2.addItem(id + "-" + name + "-" + price + "-" + typeID);
            }

        } catch (Exception e) {
            // JOptionPane.showMessageDialog(this, "Error");
            e.printStackTrace();
        } finally {
            try {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pre != null) {
                        pre.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
        tblBuyView = new javax.swing.JTable();
        lblBuySer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBuySerView = new javax.swing.JTable();
        cbxOwner = new javax.swing.JComboBox<>();

        tblBuyView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tt", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBuyView);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBuySer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBuySer.setForeground(new java.awt.Color(0, 51, 204));
        lblBuySer.setText("BUY SERVICE");

        jLabel1.setText("ID");

        jLabel2.setText("Date");

        jLabel3.setText("Owner");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        tblBuySerView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblBuySerView);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addGap(55, 55, 55)
                        .addComponent(btnSave)
                        .addGap(72, 72, 72)
                        .addComponent(btnDelete)
                        .addGap(77, 77, 77)
                        .addComponent(btnView))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(109, 109, 109))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnView))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(lblBuySer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblBuySer)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        String s1 = (String) cbxOwner.getSelectedItem();
        uploadSer();
        uploadPet(s1);
        Vector row = new Vector();
        data.add(row);
        DefaultTableModel model = new DefaultTableModel(data, header) ;
        tblBuySerView.setModel(model);
        DefaultCellEditor cell2 = new DefaultCellEditor(list1);
        tblBuySerView.getColumnModel().getColumn(1).setCellEditor(cell2);
        DefaultCellEditor cell3 = new DefaultCellEditor(list2);
        tblBuySerView.getColumnModel().getColumn(0).setCellEditor(cell3);

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String date = txtDate.getText();
        String owner = (String) cbxOwner.getSelectedItem();
        boolean date1 = Valid.isValidDate(date);
        boolean valid = true;

        if (txtID.getText().length() == 0) {
            valid = false;
            JOptionPane.showMessageDialog(this, "ID can't be blank");
        }
        if (!txtID.getText().matches("^Is[0-9]*$")) {
            valid = false;
            JOptionPane.showMessageDialog(this, "Input Is + digit in id");
        }
        if (!txtID.getText().matches("^[a-zA-Z-0-9]*$")) {
            valid = false;
            JOptionPane.showMessageDialog(this, "ID does not contain special characters\n");
        }
        if(date1 == false){
            valid = false;
            JOptionPane.showMessageDialog(this, "Input date must be greater than or equal today and fomat MM/dd/yyyy");
        }
        if(txtDate.getText().length() == 0){
            valid = false;
            JOptionPane.showMessageDialog(this, "Date can't be blank");
        }
        InvoiceSerDAO dao = new InvoiceSerDAO();
        int check = dao.checkDate(date);
        if(check > 9){
            valid = false;
            JOptionPane.showMessageDialog(this, "1 date order 10 person");
        }
        if (valid) {

            InvoiceDetail dt = new InvoiceDetail();

            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please new row in table ");
                return;
            } else {
                int result = dao.insertInvoice(txtID.getText(), date, owner);
                if (result == 0) {
                    JOptionPane.showMessageDialog(this, "Duplicate can't save");
                    return;
                }
                for (int i = 0; i < data.size(); i++) {
                    Vector row = (Vector) data.get(i);
                    String ser = (String) row.get(0);
                    String pet = (String) row.get(1);
                    if (pet == null || ser == null) {
                        JOptionPane.showMessageDialog(this, "Do not empty");
                        dao.deleteInvoide(txtID.getText());
                        return;
                    }
                }
                for (int i = 0; i < data.size(); i++) {
                    Vector row = (Vector) data.get(i);
                    String ser = (String) row.get(0);
                    String pet = (String) row.get(1);

                    if (pet != null && ser != null) {
                        int result1 = dao.insertDetail(txtID.getText(), row, date);
                        if (result1 == 0) {
                            JOptionPane.showMessageDialog(this, "Dulicate id");
                            dao.deleteInvoide(txtID.getText());
                            dao.deleteDetail(txtID.getText());
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Save sucessfull");

            }

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (tblBuySerView.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Empty data in table");
        } else {
            int index = tblBuySerView.getSelectedRow();
            if (index >= 0) {
                data.removeElementAt(index);
                DefaultTableModel model = new DefaultTableModel(data, header);
                tblBuySerView.setModel(model);
                DefaultCellEditor cell2 = new DefaultCellEditor(list1);
                tblBuySerView.getColumnModel().getColumn(1).setCellEditor(cell2);

                DefaultCellEditor cell3 = new DefaultCellEditor(list2);
                tblBuySerView.getColumnModel().getColumn(0).setCellEditor(cell3);

            } else {
                JOptionPane.showMessageDialog(this, "Choose row to delete");
                return;
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        new ViewService().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewActionPerformed

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
            java.util.logging.Logger.getLogger(BuySerJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuySerJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuySerJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuySerJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuySerJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cbxOwner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBuySer;
    private javax.swing.JTable tblBuySerView;
    private javax.swing.JTable tblBuyView;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
