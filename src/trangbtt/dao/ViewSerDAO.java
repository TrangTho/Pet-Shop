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
import java.util.Vector;
import javax.swing.JOptionPane;
import trangbtt.db.MyConnection;

/**
 *
 * @author ASUS
 */
public class ViewSerDAO implements Serializable{
    private  Connection conn = null;
    private  PreparedStatement preStm = null;
    private  ResultSet rs = null;

    private  void closeConnection() {
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

    public  Vector view(String id) {
        Vector v = null;
        try {
            String sql = "select p.Name Pet, se.Name Service, se.Price from Invoice_Ser_Detail id, Service se, Pet p , Invoice_Ser ins "
                    + "where id.ServiceID = se.ServiceID and p.PetID = id.PetID and id.InvoiceIDS = ins.InvoiceIDS and id.InvoiceIDS = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            v = new Vector();
            while (rs.next()) {
                String petname = rs.getString("Pet");
                String proname = rs.getString("Service");
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


    public  String date(String id) {
        String v = null;

        try {
            String sql = "select ip.date from Invoice_Ser_Detail ip, Invoice_Ser s "
                    + "where ip.InvoiceIDS =  s.InvoiceIDS and ip.InvoiceIDS = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                v = rs.getString("Date");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not found date");
        } finally {
            closeConnection();
        }

        return v;
    }

    public  String phone(String id) {
        String v = null;

        try {

            String sql = "select i.Owner  from Invoice_Ser i where i.InvoiceIDS = ? ";
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
    public  String name(String id) {
        String v = null;

        try {

            String sql = "select o.Name Owner from Owner o, Invoice_Ser ip where ip.InvoiceIDS = ? and ip.Owner = o.Phone";
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
