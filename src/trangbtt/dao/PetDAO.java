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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import trangbtt.db.MyConnection;
import trangbtt.dto.PetDTO;

/**
 *
 * @author ASUS
 */
public class PetDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public PetDAO() {
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

    public List<PetDTO> findByName(String search) {
        List<PetDTO> result = null;
        String petID, name;
        float weight;
        String typeID;
        PetDTO dto = null;
        try {
            String sql = "Select PetID, Name, Weight, Owner, TypeID From Pet Where Name LIKE ?";

            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                petID = rs.getString("PetID");
                name = rs.getString("Name");
                weight = rs.getFloat("Weight");
                String owner = rs.getString("Owner");
                typeID = rs.getString("TypeID");
                dto = new PetDTO(petID, name, weight, owner,typeID);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean insert(PetDTO dto) {
        boolean check = false;
        try {
            String slq = "Insert Into Pet(PetID, Name, Weight, Owner, TypeID) Values(?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getPetID());
            preStm.setString(2, dto.getName());
            preStm.setFloat(3, dto.getWeight());
            preStm.setString(4, dto.getOwner());
            preStm.setString(5, dto.getTypeID());
            //preStm.setBoolean(5, false);
            check = preStm.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dulicate ID");
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(PetDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Pet Set Name = ?, Weight = ?, Owner =?, TypeID = ? Where PetID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setFloat(2, dto.getWeight());
            preStm.setString(3, dto.getOwner());
            preStm.setString(4, dto.getTypeID());
            preStm.setString(5,dto.getPetID());
            check = preStm.executeUpdate() > 0;
        } 
        finally {
            closeConnection();
        }
        return check;
    }
//   
   public PetDTO findByPrimaryKey(String id) throws Exception {
        PetDTO dto = null;
        try {
            String sql = "Select Name, Weight, Owner, TypeID From Pet Where PetID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                float weight = rs.getFloat("Weight");
                String owner = rs.getString("Owner");
                String typeID= rs.getString("TypeID");
                dto = new PetDTO(id, name, weight, owner, typeID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
   public String loadOwnerByName(String username) {
        try {
            String sql = "select p.Owner from Pet p, Owner ac where p.Owner = ac.Ownername and p.Name = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            while (rs.next()) {
                 username = rs.getString("Owner");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found");
        } finally {
           closeConnection();
        }
        return username;
    }
}
