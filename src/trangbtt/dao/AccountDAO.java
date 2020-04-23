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
import trangbtt.dto.AccountDTO;

/**
 *
 * @author ASUS
 */
public class AccountDAO implements Serializable{
     private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public AccountDAO() {
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
    public List<AccountDTO> findByLikeName(String search) {
        List<AccountDTO> result = null;
        String username, fullname;
        AccountDTO dto = null;
        try {
            //cau truy van
            String sql = "Select Username, Fullname From Account Where Fullname LIKE ? and Role = ?";
            //1 & 2
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, "user");
            rs = preStm.executeQuery();
            result = new ArrayList<>(); // nếu tạo luôn bên trên lệnh getMyConnection nếu k kết nối được né ngốn ô nhớ
            //4.
            while (rs.next()) {
                username = rs.getString("Username");
                fullname = rs.getString("Fullname");
                dto = new AccountDTO(username, fullname);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    public boolean insert(AccountDTO dto) throws Exception {
        boolean check = false;

        try {
            String slq = "Insert Into Account(Username, Password, Fullname, Role) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(slq);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, dto.getRole());
            //Inset/Delect/update k thực thi ngay mà vào database xem điều kiện có hay k .v.v. thì mới quay lại làm
            check = preStm.executeUpdate() > 0; // trả về kiểu int, số lượng record
            //excuteQuery(trả về resultSet-con trỏ  ) dùng cho select
//            int i = preStm.executeUpdate();
//            if (i > 0) {
//                check = true;
//            }
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean update(AccountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Account Set Password = ?, Fullname = ?, Role = ? Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());
            preStm.setString(2, dto.getFullname());
            preStm.setString(3, dto.getRole());
            preStm.setString(4, dto.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
   
    public AccountDTO findByPrimaryKey(String id) throws Exception {
        AccountDTO dto = null;
        try {
            String sql = "Select Fullname, Role From Account Where Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                dto = new AccountDTO(id, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    
}
