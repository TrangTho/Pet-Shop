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
public class PetDTO implements Serializable {

    private String petID, name;
    private float weight;
    private String owner, typeID;

    public Vector toVector() {
        Vector v = new Vector();
        v.add(petID);
        v.add(name);
        v.add(weight);
        v.add(owner);
        v.add(typeID);

        return v;
    }

    public PetDTO() {
    }

    public PetDTO(String petID, String name, float weight, String owner, String typeID) {
        this.petID = petID;
        this.name = name;
        this.weight = weight;
        this.owner = owner;
        this.typeID = typeID;
    }

    public PetDTO(String name, float weight, String owner, String typeID) {
        this.name = name;
        this.weight = weight;
        this.owner = owner;
        this.typeID = typeID;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

}
