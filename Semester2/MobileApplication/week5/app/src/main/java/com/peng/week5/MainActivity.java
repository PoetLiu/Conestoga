package com.peng.week5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private Button firstBtn;
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstBtn = findViewById(R.id.firstButton);
        firstBtn.setText("HelloWorld");

        firstBtn.setOnClickListener(v -> {
            Log.i("INFO", "the button is clicked, id:" + v.getId());
            firstBtn.setText("Clicked");
            CharSequence text = "You have clicked " + counter.incrementAndGet() + " times.";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        });

    }
}