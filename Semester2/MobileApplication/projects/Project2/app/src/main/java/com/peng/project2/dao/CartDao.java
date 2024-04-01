package com.peng.project2.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface CartDao {
    @Transaction
    @Query("SELECT * FROM cart WHERE userUid = :userUid")
    CartWithItems getCartByUserUid(String userUid);
}
