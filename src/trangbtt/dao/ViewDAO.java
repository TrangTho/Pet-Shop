
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import trangbtt.db.MyConnection;

/**
 *
 * @author ASUS
 */
public class ViewDAO {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

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

    public Vector view(String id) {
        Vector v = null;
        try {
            String sql = "select p.Name Pet, pr.Name Product, pr.Price from Invoice_Pro_Detail id, Invoice_Pro ip, Product pr, Pet p where id.ProductID = pr.ProductID and p.PetID = id.PetID and id.InvoiceIDP = ip.InvoiceIDP and id.InvoiceIDP = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            v = new Vector();
            while (rs.next()) {
                String petname = rs.getString("Pet");
                String proname = rs.getString("Product");
                int price = rs.getInt("Price");
                Vector row = new Vector();
                row.add(petname);
                row.add(proname);
                row.add(price);
                v.add(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Id not found");
        } finally {
            closeConnection();
        }

        return v;
    }

    public String dateInv(String id) {
        String v = null;

        try {
            String sql = "select ip.Date from Invoice_Pro_Detail ip, Invoice_Pro p where ip.InvoiceIDP =  p.InvoiceIDP and ip.InvoiceIDP = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                v = rs.getString("Date");
            
            }
            
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            closeConnection();
        }

        return v;
    }

    public String phone(String id) {
        String v = null;

        try {

            String sql = "select i.Owner  from Invoice_Pro i where i.InvoiceIDP = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                v = rs.getString("Owner");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not found phone");
        } finally {
            closeConnection();
        }

        return v;
    }

    public String name(String id) {
        String v = null;

        try {

            String sql = "select o.Name Owner from Owner o, Invoice_Pro ip where ip.InvoiceIDP = ? and ip.Owner = o.Phone";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                v = rs.getString("Owner");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not found Owner");
        } finally {
            closeConnection();
        }

        return v;
    }

}
