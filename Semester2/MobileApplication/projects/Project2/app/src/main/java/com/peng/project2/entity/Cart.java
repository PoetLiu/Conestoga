package com.peng.project2.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey
    private Long id;
    private String userUid;
    private Double subTotal = 0.0;
    private Double taxes = 0.0;
    private Double totalPrice = 0.0;

    public Cart() {
    }

    public Cart(String userUid) {
        this.userUid = userUid;
    }

    public Cart(String userUid, Double subTotal, Double taxes, Double totalPrice) {
        this.userUid = userUid;
        this.subTotal = subTotal;
        this.taxes = taxes;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public void updatePrice() {
        this.taxes = this.subTotal * 0.13;
        this.totalPrice = this.subTotal + this.taxes;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
