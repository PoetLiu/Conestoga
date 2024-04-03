package com.peng.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThankYouActivity extends AppCompatActivity {
    private Button goShoppingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        goShoppingBtn = findViewById(R.id.goShoppingButton);
        goShoppingBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, ProductListActivity.class);
            startActivity(myIntent);
        });
    }
}