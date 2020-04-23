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
import trangbtt.dto.RegistrationDTO;

/**
 *
 * @author ASUS
 */
public class RegistrationDAO implements Serializable{
    private Connection conn= null;
    private PreparedStatement  preStm = null;
    private ResultSet  rs= null;

    public RegistrationDAO() {
    }
    private void closeConnection(){
        try {
            try {
                if(rs!= null){
                rs.close();
            }
                if(preStm!= null){
                    preStm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String checkLogin(String username, String password){
        String role = "failed";
        try {
            String sql = "Select Role From Account Where Username = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while(rs.next()){
                role = rs.getString("Role");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return role;
    }
//    public List<RegistrationDTO> findByName(String search){
//        List<RegistrationDTO> result = null;
//        String username, fullname, gender;
//        RegistrationDTO dto = null;
//        try {
//            String sql ="Select Username, Fullname, Gender From Account Where Fullname LIKE ?";
//            conn = MyConnection.getMyConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setString(1, "%" + search +"");
//            rs = preStm.executeQuery();
//            result = new ArrayList<>();
//            while(rs.next()){
//                username = rs.getString("Username");
//                fullname = rs.getString("Fullname");
//                gender =rs.getString("Gender");
//                dto = new RegistrationDTO(username, fullname, gender);
//                result.add(dto);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            closeConnection();
//        }
//        
//        return result;
//    }
}
