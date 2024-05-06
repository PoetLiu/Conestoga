package com.example.notetakingapp21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    Button btnNew;
    Note mTempNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.btnShow);
        btnNew = findViewById(R.id.btnCreate);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogShowNote dialog = new DialogShowNote();
                dialog.show(getSupportFragmentManager(), "dialogshow");



            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(), "dialognew");

            }
        });


    }
}