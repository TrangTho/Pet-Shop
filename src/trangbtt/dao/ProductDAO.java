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
import javax.swing.JOptionPane;
import trangbtt.db.MyConnection;
import trangbtt.dto.ProductDTO;

/**
 *
 * @author ASUS
 */
public class ProductDAO {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ProductDAO() {
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

    public List<ProductDTO> findByName(String search) {
        List<ProductDTO> result = null;
        String productID, name;
        int price;
         String typeID;
        ProductDTO dto = null;
        try {
            String sql = "Select ProductID, Name, Price, TypeID From Product Where Name LIKE ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");

            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                productID = rs.getString("ProductID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID =rs.getString("TypeID");
                dto = new ProductDTO(productID, name, price, typeID);
                result.add(dto);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean insert(ProductDTO dto){
        boolean check = false;
        try {
            String slq = "Insert Into Product(ProductID, Name, Price, TypeID) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getProductID());
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

    public boolean update(ProductDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "Update Product Set Name = ?, Price = ?, TypeID = ? Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setInt(2, dto.getPrice());
            preStm.setString(3, dto.getTypeID());
            preStm.setString(4, dto.getProductID());
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
    public List<ProductDTO> findAll() throws Exception{
        List<ProductDTO> result = null;
        ProductDTO dto = null;
        String id, name;
        int price;
        String typeID;
        try {
            String slq = "Select ProductID, Name, Price, TypeID From Product";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                id = rs.getString("ProductID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID = rs.getString("TypeID");
                dto = new ProductDTO(id, name, price, typeID);
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
            String sql = "Delete From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return check;
    }
   public List<ProductDTO> findProduct(String ID) throws Exception{
        List<ProductDTO> result = null;
        String productID;
        String name;
        int price;
        String typeID;
        ProductDTO dto = null;
        try {
            String slq = "Select Name, Price, TypeID From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getProductID());
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                productID = rs.getString("ProductID");
                name = rs.getString("Name");
                price = rs.getInt("Price");
                typeID =rs.getString("Type");
                dto = new ProductDTO(productID, name, price, typeID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
   public ProductDTO findByPrimaryKey(String id) throws Exception {
        ProductDTO dto = null;
        try {
            String sql = "Select Name, Price, TypeID From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                int price = rs.getInt("Price");
                String typeID = rs.getString("TypeID");
                dto = new ProductDTO(id, name, price, typeID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}

