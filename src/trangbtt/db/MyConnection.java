/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class MyConnection implements Serializable {
    // static tren stack, dùng chung
    public static Connection getMyConnection() {
        Connection conn = null;
        try {
        
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1456;databaseName=Pet_Shop","sa", "123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
