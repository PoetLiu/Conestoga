package com.peng.lab3;

import static com.peng.lab3.Constants.EMAIL_FIELD_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private TextView txtEmail;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtEmail = findViewById(R.id.txtEmail);
        btnReturn = findViewById(R.id.btnReturn);

        String passedEmail = getIntent().getStringExtra(EMAIL_FIELD_NAME);
        txtEmail.setText(passedEmail);

        btnReturn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}