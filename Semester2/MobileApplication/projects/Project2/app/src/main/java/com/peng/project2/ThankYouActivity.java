package com.peng.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ThankYouActivity extends AppCompatActivity {
    private Button goShoppingBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        mAuth = FirebaseAuth.getInstance();

        goShoppingBtn = findViewById(R.id.goShoppingButton);
        goShoppingBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, ProductListActivity.class);
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