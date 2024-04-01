package com.peng.project2.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.peng.project2.entity.Cart;
import com.peng.project2.entity.CartItem;

import java.util.List;

/*
* This class connects cart and cartItem entities with one-many relations.
* */
public class CartWithItems {
    @Embedded
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Relation(
            parentColumn = "id",
            entityColumn = "cartId"
    )
    private List<CartItem> items;
}
