/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dfs.springbitpay.dfsbitpay.model;

/**
 *
 * @author Yixiang
 */
public class User {

    private String username;
    private String invoiceId;
    private String status;
    private String date;
    private String url;
    private double amount;

    public User() {
    }

    public User(String username, String invoiceId, double amount, String date, String status) {
        this.username = username;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String date) {
        this.url = url;
    }
    
    
}
