/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class History {

    private int id;
    private String date;
    private String username;
    private Product product;
    private int quantity;
    private long price;
    private boolean nodication;

    public History() {
    }

    public History(int id, String date, String username, Product product, int quantity, long price, boolean nodication) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.nodication = nodication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isNodication() {
        return nodication;
    }

    public void setNodication(boolean nodication) {
        this.nodication = nodication;
    }

}
