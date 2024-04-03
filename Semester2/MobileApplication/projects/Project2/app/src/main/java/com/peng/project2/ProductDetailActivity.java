package com.peng.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productTitleText;
    private TextView productSubtitleText;
    private TextView productColorText;
    private TextView productPrice;
    private TextView productStock;
    private Button plusButton;
    private TextView quantityTextView;
    private Button minusButton;
    private Button addToCart;
    private Button goToCart;
    private TextView productDetailTextView;
    private AppDatabase db;
    private FirebaseAuth mAuth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        db = AppDatabase.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        productImage = findViewById(R.id.productImageView);
        productTitleText = findViewById(R.id.productTitleTextView);
        productSubtitleText = findViewById(R.id.productSubtitleTextView);
        productColorText = findViewById(R.id.productColorsTextView);
        productPrice = findViewById(R.id.productPriceTextView);
        productStock = findViewById(R.id.productStockTextView);
        plusButton = findViewById(R.id.plusButton);
        quantityTextView = findViewById(R.id.quantityTextView);
        minusButton = findViewById(R.id.minusButton);
        addToCart = findViewById(R.id.addToCartBtn);
        productDetailTextView = findViewById(R.id.productDetailTextView);
        goToCart = findViewById(R.id.goToCartButton);

        Intent intent = getIntent();
        long productId = intent.getLongExtra("productId", -1L);
        Product entity = db.productDao().selectOne(productId);
        productTitleText.setText(entity.getTitle());
        productSubtitleText.setText(entity.getSubTitle());
        productColorText.setText(entity.getColorInfo());
        productStock.setText(entity.getStock().toString());
        productPrice.setText(entity.getPrice().toString());
        productDetailTextView.setText(entity.getDetails());

        String imageURIStr = intent.getStringExtra("imageURI");
        productImage.setImageURI(Uri.parse(imageURIStr));

        minusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            if (quantity > 1) {
                quantityTextView.setText(Integer.toString(quantity-1));
            } else {
                Toast.makeText(ProductDetailActivity.this, "The minimal quantity is 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        plusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            if (quantity < entity.getStock()) {
                quantityTextView.setText(Integer.toString(quantity+1));
            } else {
                Toast.makeText(ProductDetailActivity.this,
                        "The maximum quantity is " + entity.getStock(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        addToCart.setOnClickListener(v -> {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            Common.addToCart(ProductDetailActivity.this, db, mAuth.getCurrentUser(), entity,
                    quantity
            );
        });

        goToCart.setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(myIntent);
        });

        Common.initToolBar(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Common.checkLogin(mAuth, this);
    }
}