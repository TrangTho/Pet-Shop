/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import trangbtt.db.MyConnection;
import trangbtt.dto.InvoiceDetail;

/**
 *
 * @author ASUS
 */
public class InvoiceSerDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public InvoiceSerDAO() {
    }

    private void closeConnection() {
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

    public int insertInvoice(String id, String date, String owner) {
        int result = 0;

        try {
            String sql = "Insert Into Invoice_Ser(InvoiceIDS, DateOrder, Owner) values (?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, date);
            preStm.setString(3, owner);
            result = preStm.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Duplicated ID !");
        } finally {

            closeConnection();

        }

        return result;
    }

    public int insertDetail(String ID, Vector row, String date) {
        // InvoiceDetail dto = new InvoiceDetail();
        int result = 0;

        try {
            String sql = "Insert Into Invoice_Ser_Detail(InvoiceIDS, ServiceID, Date, Price, PetID) values (?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, ID);
            String s1 = (String) row.get(0);
            String[] s2 = s1.split("-");
            preStm.setString(2, s2[0].trim());
            preStm.setString(3, date);
            String s3 = (String) row.get(0);
            String[] s4 = s3.split("-");
            preStm.setString(4, s4[2].trim());
            String s5 = (String) row.get(1);
            String[] s6 = s5.split("-");
            preStm.setString(5, s6[0].trim());

            result = preStm.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Duplicated ID !");
        } finally {
            closeConnection();
        }

        return result;
    }

    public void deleteInvoide(String ID) {

        try {
            String sql = "delete from Invoice_Ser where InvoiceIDS = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, ID);
            preStm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public void deleteDetail(String ID) {

        try {
            String sql = "Delete From Invoice_Ser_Detail Where InvoiceIDS = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, ID);
            preStm.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public int checkDate(String date) {
        int result = 0;
        try {
            String sql = "select DateOrder from Invoice_Ser where DateOrder = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, date);
            rs = preStm.executeQuery();
            while (rs.next()) {
                rs.getString("DateOrder");
                result++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
