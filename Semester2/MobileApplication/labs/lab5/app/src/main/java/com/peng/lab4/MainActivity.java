package com.peng.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 vPager;
    private RecyclerView.Adapter adapter;
    private List<Cellphone> cellphoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cellphoneList = new ArrayList<>();
        cellphoneList.add(new Cellphone("galaxy", "Galaxy", 299.99));
        cellphoneList.add(new Cellphone("iphone", "iPhone", 499.99));
        cellphoneList.add(new Cellphone("pixel", "Pixel", 199.99));

        vPager = findViewById(R.id.vPager);
        adapter = new CellphoneAdapter(cellphoneList);
        vPager.setAdapter(adapter);
    }
}