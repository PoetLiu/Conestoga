package com.peng.project2.dao;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.peng.project2.entity.CartItem;
import com.peng.project2.entity.Product;

public class CartItemFull {
    @Embedded
    private CartItem entity;

    @Relation(
            parentColumn = "productId",
            entityColumn = "id"
    )
    private Product product;

    public CartItem getEntity() {
        return entity;
    }

    public void setEntity(CartItem entity) {
        this.entity = entity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
