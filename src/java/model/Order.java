/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Order {
    private int id;
    private Date date;
    private String userName;
    private long totalMoney;
    private boolean nodication;

    public Order() {
    }

    public Order(int id, Date date, String userName, long totalMoney, boolean nodication) {
        this.id = id;
        this.date = date;
        this.userName = userName;
        this.totalMoney = totalMoney;
        this.nodication = nodication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isNodication() {
        return nodication;
    }

    public void setNodication(boolean nodication) {
        this.nodication = nodication;
    }
 
}
