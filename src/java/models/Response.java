/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author bayan
 */
public class Response {
    private int id;
    private int orderId;
    private int personId;
    private double price;
   private String text;

    public Response(int id, int orderId, int personId, double price, String text) {
        this.id = id;
        this.orderId = orderId;
        this.personId = personId;
        this.price = price;
        this.text = text;
    }
   
   public Response(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
   
   
}
