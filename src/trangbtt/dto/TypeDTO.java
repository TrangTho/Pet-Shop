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
public class TypeDTO implements Serializable{
    private String typeID, name;

    public TypeDTO() {
    }

    public TypeDTO(String typeID, String name) {
        this.typeID = typeID;
        this.name = name;
    }
    public Vector toVector(){
        Vector v= new Vector();
        v.add(typeID);
        v.add(name);
        return v;
    }
    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
