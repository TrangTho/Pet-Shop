/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import trangbtt.db.MyConnection;
import trangbtt.dto.OwnerDTO;

/**
 *
 * @author ASUS
 */
public class OwnerDAO {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public OwnerDAO() {
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

    public List<OwnerDTO> findByName(String search) {
        List<OwnerDTO> result = null;
        String name;
        String phone;
        String address;
        OwnerDTO dto = null;
        try {
            String sql = "select Phone, Name, Address From Owner Where Name LIKE ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                phone = rs.getString("Phone");
                name = rs.getString("Name");
                address = rs.getString("Address");
                dto = new OwnerDTO(phone, name, address);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insert(OwnerDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Owner(Phone, Name, Address) Values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPhone());
            preStm.setString(2, dto.getOwnerName());
            preStm.setString(3, dto.getAddress());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(OwnerDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Owner Set Name = ?, Address = ? Where Phone = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getOwnerName());
            preStm.setString(2, dto.getAddress());
            preStm.setString(3, dto.getPhone());

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public OwnerDTO findByPrimaryKey(String id) throws Exception {
        OwnerDTO dto = null;
        try {
            String sql = "Select Name, Address From Owner Where Phone = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                dto = new OwnerDTO(id, name, address);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
