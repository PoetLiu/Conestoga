package com.peng.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.peng.project2.dao.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private Button shopNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        shopNowBtn = findViewById(R.id.shopNowButton);
        shopNowBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        });
    }
}
