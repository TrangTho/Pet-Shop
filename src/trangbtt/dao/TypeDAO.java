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
import trangbtt.db.MyConnection;
import trangbtt.dto.TypeDTO;

/**
 *
 * @author ASUS
 */
public class TypeDAO implements Serializable{
    private Connection conn= null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public TypeDAO() {
    }
    private void closeConnection(){
        try {
            try {
                if(rs != null){
                    rs.close();
                }
                if(preStm != null){
                    preStm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<TypeDTO> findByLikeName(String search) {
        List<TypeDTO> result = null;
        String id, name;
        TypeDTO dto = null;
        try {
            //cau truy van
            String sql = "Select TypeID, Name From Type Where Name LIKE ?";
            //1 & 2
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>(); // nếu tạo luôn bên trên lệnh getMyConnection nếu k kết nối được né ngốn ô nhớ
            //4.
            while (rs.next()) {
                id = rs.getString("TypeID");
                name = rs.getString("Name");
                dto = new TypeDTO(id, name);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean insert(TypeDTO dto) throws Exception {
        boolean check = false;

        try {
            String slq = "Insert Into Type(TypeID, Name) Values(?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getTypeID());
            preStm.setString(2, dto.getName());
            check = preStm.executeUpdate() > 0; // trả về kiểu int, số lượng record
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean update(TypeDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Type Set Name = ? Where TypeID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setString(2, dto.getTypeID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public TypeDTO findByPrimaryKey(String id) throws Exception {
        TypeDTO dto = null;
        try {
            String sql = "Select Name From Type Where TypeID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                dto = new TypeDTO(id, name);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
