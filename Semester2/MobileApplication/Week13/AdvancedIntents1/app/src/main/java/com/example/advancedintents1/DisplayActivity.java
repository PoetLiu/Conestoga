package com.example.advancedintents1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtEmail;
    TextView txtCity;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtCity = findViewById(R.id.txtCity);

        Intent data = getIntent();
        Person p = data.getParcelableExtra("personPass");
        txtName.setText(p.getName());
        txtEmail.setText(p.getEmail());
        txtCity.setText(p.getCity());

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener( view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}