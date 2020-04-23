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
public class ServiceDTO implements Serializable{
    private String serviceID , name;
    private int price;
    private String typeID;

    public ServiceDTO() {
    }

    public ServiceDTO(String serviceID, String name, int price, String typeID) {
        this.serviceID = serviceID;
        this.name = name;
        this.price = price;
        this.typeID = typeID;
    }

    public ServiceDTO(String serviceID, String name, int price) {
        this.serviceID = serviceID;
        this.name = name;
        this.price = price;
    }
    public Vector toVector(){
        Vector v = new Vector();
        v.add(serviceID);
        v.add(name);
        v.add(price);
        v.add(typeID);
        return v;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
    
}
