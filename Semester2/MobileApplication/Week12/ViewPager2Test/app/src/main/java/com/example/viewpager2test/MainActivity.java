package com.example.viewpager2test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 vPager;
    private RecyclerView.Adapter adapter;
    ArrayList<Person> peopleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person p1 = new Person("billgates", "Bill Gates");
        Person p2 = new Person("stevejobs", "Steve Jobs");

        peopleList.add(p1);
        peopleList.add(p2);

        vPager = findViewById(R.id.vPager);

        adapter = new PersonAdapter(peopleList);

        vPager.setAdapter(adapter);
    }
}