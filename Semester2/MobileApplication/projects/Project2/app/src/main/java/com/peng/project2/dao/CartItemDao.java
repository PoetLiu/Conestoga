package com.peng.project2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.peng.project2.entity.CartItem;

@Dao
public interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetOrUpdate(CartItem item);

    @Query("SELECT * FROM CartItem WHERE cartId = :cartId AND productId = :productId")
    CartItem selectOne(Long cartId, Long productId);

    @Delete
    void delete(CartItem item);

    @Query("DELETE FROM CartItem WHERE cartId = :cartId")
    void deleteByCartId(Long cartId);
}
