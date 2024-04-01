package com.peng.project2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.peng.project2.entity.Cart;
import com.peng.project2.entity.CartItem;
import com.peng.project2.entity.Product;

@Database(entities = {Cart.class, CartItem.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract CartDao cartDao();
    public abstract CartItemDao cartItemDao();
    public abstract ProductDao productDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase.class, "store"
            ).allowMainThreadQueries()
             .build();
        }
        return INSTANCE;
    }
}
