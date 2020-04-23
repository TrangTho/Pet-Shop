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

import trangbtt.dto.ServiceDTO;

/**
 *
 * @author ASUS
 */
public class ServiceDAO implements Serializable{
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ServiceDAO() {
    }


    public void closeConnection() {
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

    public List<ServiceDTO> findByName(String search) {
        List<ServiceDTO> result = null;
        String serviceID, name;
        int price;
         String typeID;
        ServiceDTO dto = null;
        try {
            String sql = "Select ServiceID, Name, Price, TypeID From Service Where Name LIKE ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");

            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                serviceID = rs.getString("ServiceID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID =rs.getString("TypeID");
                dto = new ServiceDTO(serviceID, name, price, typeID);
                result.add(dto);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean insert(ServiceDTO dto){
        boolean check = false;
        try {
            String slq = "Insert Into Service(ServiceID, Name, Price, TypeID) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getServiceID());
            preStm.setString(2, dto.getName());
            preStm.setInt(3, dto.getPrice());
            preStm.setString(4, dto.getTypeID());
            //preStm.setBoolean(5, false);
            check = preStm.executeUpdate() > 0;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Dulicate ID");
        }finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(ServiceDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Service Set Name = ?, Price = ?, TypeID = ? Where ServiceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setInt(2, dto.getPrice());
            preStm.setString(3, dto.getTypeID());
            preStm.setString(4, dto.getServiceID());
            check = preStm.executeUpdate() > 0;
        }
            //catch(Exception e){
//            JOptionPane.showMessageDialog(null,"Error");
//        }
        finally {
            closeConnection();
        }
        return check;
    }
    public List<ServiceDTO> findAll() throws Exception{
        List<ServiceDTO> result = null;
        ServiceDTO dto = null;
        String id, name;
        int price;
        String typeID;
        try {
            String slq = "Select ServiceID, Name, Price, TypeID From Service";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("ServiceID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID = rs.getString("TypeID");
                dto = new ServiceDTO(id, name, price, typeID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
   public boolean delete(String id) throws Exception{
        boolean check = false;
        try {
            String sql = "Delete From Service Where ServiceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return check;
    }
   public List<ServiceDTO> findProduct(String ID) throws Exception{
        List<ServiceDTO> result = null;
        String serviceID;
        String name;
        int price;
        String typeID;
        ServiceDTO dto = null;
        try {
            String slq = "Select Name, Price, TypeID From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getServiceID());
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                serviceID = rs.getString("ServiceID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID =rs.getString("Type");
                dto = new ServiceDTO(serviceID, name, price, typeID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
   public ServiceDTO findByPrimaryKey(String id) throws Exception {
        ServiceDTO dto = null;
        try {
            String sql = "Select Name, Price, TypeID From Service Where ServiceID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                int price = rs.getInt("Price");
                String typeID = rs.getString("TypeID");
                dto = new ServiceDTO(id, name, price, typeID) ;
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
