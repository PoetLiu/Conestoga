package com.peng.project2;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Product;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase db;
    private FirebaseAuth mAuth;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        db = AppDatabase.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.productsRV);
        layoutManager = getLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        storageRef = FirebaseStorage.getInstance().getReference().child("images");
        List<Product> productList = db.productDao().getAll();
        mAdapter = new ProductsAdapter(productList, storageRef, db, mAuth);
        recyclerView.setAdapter(mAdapter);

        Common.initToolBar(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Common.checkLogin(mAuth, this);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return new GridLayoutManager(this, 2);
        } else {
            return new LinearLayoutManager(this);
        }
    }
}