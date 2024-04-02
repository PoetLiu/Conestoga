package com.peng.project2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.peng.project2.entity.Cart;

@Dao
public interface CartDao {
    @Transaction
    @Query("SELECT * FROM Cart WHERE userUid = :userUid")
    CartFull getCartByUserUid(String userUid);

    @Query("SELECT * FROM Cart WHERE userUid = :userUid")
    Cart selectOne(String userUid);
    @Insert
    void insert(Cart cart);

    @Update
    void update(Cart cart);
}
