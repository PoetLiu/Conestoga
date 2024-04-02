package com.peng.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.dao.CartFull;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase db;
    private FirebaseAuth mAuth;
    private StorageReference storageRef;
    private TextView subTotalTextView;
    private TextView deliveryTextView;
    private TextView taxTextView;
    private TextView totalTextView;

    private ScrollView scrollView;
    private LinearLayout emptyLinearLayout;
    private Button goShoppingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = AppDatabase.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        initEmptyView();

        recyclerView = findViewById(R.id.cartRV);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        subTotalTextView = findViewById(R.id.subtotalTextView);
        deliveryTextView = findViewById(R.id.deliveryTextView);
        taxTextView = findViewById(R.id.taxTextView);
        totalTextView = findViewById(R.id.totalTextView);

        storageRef = FirebaseStorage.getInstance().getReference().child("images");
        scrollView = findViewById(R.id.cartScrollView);

        CartFull cart = db.cartDao().getCartByUserUid(mAuth.getCurrentUser().getUid());
        updateSummary(cart);
        updateView(cart.getItems().isEmpty());

        mAdapter = new CartAdapter(cart, storageRef, db, mAuth);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                updateSummary(cart);
                updateView(cart.getItems().isEmpty());
            }
        });
        recyclerView.setAdapter(mAdapter);

        Common.initToolBar(this);
    }

    private void updateSummary(CartFull cart) {
        subTotalTextView.setText(cart.getSubTotal().toString());
        taxTextView.setText(cart.getTaxes().toString());
        deliveryTextView.setText(cart.getDelivery().toString());
        totalTextView.setText(cart.getTotalPrice().toString());
    }

    private void updateView(boolean isEmpty) {
        if (isEmpty) {
            scrollView.setVisibility(View.GONE);
            emptyLinearLayout.setVisibility(View.VISIBLE);
        } else {
            emptyLinearLayout.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void initEmptyView() {
        emptyLinearLayout = findViewById(R.id.emptyLinearLayout);
        goShoppingBtn = findViewById(R.id.goShoppingButton);
        goShoppingBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(CartActivity.this, ProductListActivity.class);
            startActivity(myIntent);
        });
    }
}