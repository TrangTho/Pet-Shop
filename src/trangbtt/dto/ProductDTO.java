/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.dto;

import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class ProductDTO {
    private String productID, name;
    private int price;
    private String typeID;
    //private boolean delete = false;
    public ProductDTO() {
    }
//    public ProductDTO(String productID, String name, int price, boolean delete) {
//        this.productID = productID;
//        this.name = name;
//        this.price = price;
//        this.delete = delete;
//    }

    public ProductDTO(String productID, String name, int price, String typeID) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.typeID = typeID;
    }
    
//    public boolean isDelete() {
//        return delete;
//    }
//    
//    public void setDelete(boolean delete) {
//        this.delete = delete;
//    }
//    
    public ProductDTO(String productID, String name, int price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

//    public ProductDTO(String productID, String name, String price) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public Vector toVector(){
        Vector v = new Vector();
        v.add(productID);
        v.add(name);
        v.add(price);
        v.add(typeID);
        return v;
    }
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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
