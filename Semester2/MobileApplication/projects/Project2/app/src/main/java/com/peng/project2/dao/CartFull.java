package com.peng.project2.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.peng.project2.entity.Cart;
import com.peng.project2.entity.CartItem;

import java.util.List;

/*
* This class connects cart and cartItem entities with one-many relations.
* */
public class CartFull {
    @Embedded
    private Cart entity;

    private Double subTotal = 0.0;
    private Double taxes = 0.0;
    private Double delivery = 0.0;
    private Double totalPrice = 0.0;

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getDelivery() {
        return delivery;
    }

    public void setDelivery(Double delivery) {
        this.delivery = delivery;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart getEntity() {
        return entity;
    }

    public void setEntity(Cart entity) {
        this.entity = entity;
    }

    public List<CartItemFull> getItems() {
        return items;
    }

    public void setItems(List<CartItemFull> items) {
        this.items = items;
    }

    @Relation(
            entity = CartItem.class,
            parentColumn = "id",
            entityColumn = "cartId"
    )
    private List<CartItemFull> items;

    public CartItem updateQuantity(int position, int delta) {
        CartItem item = items.get(position).getEntity();
        item.setQuantity((short) (item.getQuantity() + delta));
        updatePrice();
        return item;
    }
    public CartItem removeItem(int position) {
        CartItemFull itemFull = items.remove(position);
        updatePrice();
        return itemFull.getEntity();
    }

    public void updatePrice() {
        this.subTotal = items.stream()
                .map(item -> item.getEntity().getQuantity() * item.getProduct().getPrice())
                .reduce(0.0, Double::sum);
        this.delivery = this.subTotal * 0.1;
        this.taxes = this.subTotal * 0.13;
        this.totalPrice = this.subTotal + this.delivery + this.taxes;
    }
}
