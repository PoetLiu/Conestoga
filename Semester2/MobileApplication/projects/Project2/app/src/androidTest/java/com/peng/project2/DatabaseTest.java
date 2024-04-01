package com.peng.project2;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Product;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    @Test
    public void initDb() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Product product = new Product("Nike Air Force 1 07 SE", "men's shoes",
                "1 color", "a long long details", "Nike", 89.99,
                120, "images/custom_nike_air_force_1_low_by_you.jpeg");
        db.productDao().insert(product);
        List<Product> products = db.productDao().getAll();
        Log.i("TEST", String.format("Got %d products", products.size()));
    }

    @Test
    public void selectDb() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<Product> products = db.productDao().getAll();
        Log.i("TEST", String.format("Got %d products", products.size()));
        products.forEach(product -> Log.i("Product", product.toString()));
    }
}
