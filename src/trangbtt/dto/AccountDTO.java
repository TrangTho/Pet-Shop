/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dto;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class AccountDTO implements Serializable{
    private String username, password, fullname, role;

    public AccountDTO() {
    }

    public AccountDTO(String username, String fullname) {
        this.username = username;
        this.fullname = fullname;
    }

    public AccountDTO(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }

    public AccountDTO(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Vector toVector(){
        Vector v = new Vector();
        v.add(username);
        v.add(fullname);
        return v;
    } 
}
