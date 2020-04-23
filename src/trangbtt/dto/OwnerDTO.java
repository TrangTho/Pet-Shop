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
public class OwnerDTO implements Serializable {

    private String phone;
    private String ownerName;
    private String address;

    public OwnerDTO(String phone, String ownerName, String address) {
        this.phone = phone;
        this.ownerName = ownerName;
        this.address = address;
    }

    
    public OwnerDTO() {
    }

    public Vector toVector() {
        Vector v = new Vector();
        v.add(phone);
        v.add(ownerName);
        v.add(address);
        return v;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
