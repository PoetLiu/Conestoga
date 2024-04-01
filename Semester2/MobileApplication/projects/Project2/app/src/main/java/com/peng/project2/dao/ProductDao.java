package com.peng.project2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.peng.project2.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE id = :productId")
    Product selectOne(long productId);

    @Insert
    void insert(Product product);
}
