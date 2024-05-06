package com.example.advancedintents1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editName;
    EditText editCity;
    EditText editEmail;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editCity = findViewById(R.id.editCity);
        editEmail = findViewById(R.id.editEmail);
        btnSend = findViewById(R.id.btnSubmit);



        btnSend.setOnClickListener(view -> {

            Person p = new Person();
            p.setCity(editCity.getText().toString());
            p.setEmail(editEmail.getText().toString());
            p.setName(editName.getText().toString());

            Intent i = new Intent(this, DisplayActivity.class);
            i.putExtra("personPass", p);
            startActivity(i);

        });



    }
}