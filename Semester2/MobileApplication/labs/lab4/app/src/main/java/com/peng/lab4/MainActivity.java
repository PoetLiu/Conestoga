package com.peng.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<String> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataset.add("Elon Musk");
        myDataset.add("Steve Jobs");
        myDataset.add("Michael Jackson");
        myDataset.add("Michael Jordan");
        myDataset.add("Lionel Messi");
        myDataset.add("Cristiano Ronaldo");
        myDataset.add("Andres Iniesta");
        myDataset.add("Cristiano Ronaldo");
        myDataset.add("Zlatan Ibrahimovic");
        myDataset.add("Radamel Falcao");
        myDataset.add("Robin van Persie");
        myDataset.add("Andrea Pirlo");
        myDataset.add("Yaya Toure");
        myDataset.add("Edinson Cavani");

        recyclerView = findViewById(R.id.rView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
}