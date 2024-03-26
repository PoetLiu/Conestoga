package com.peng.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG_NAME = "Lab2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int myNumber = 889;
        isEvenOrOdd(myNumber);

        int numberRows = 10;
        printPyramid(numberRows);

        double max = max(4.12, 6.12);
        Log.i(LOG_TAG_NAME, "the max is " + max);
    }

    public static void isEvenOrOdd(int number) {
        if (0 == number % 2) {
            Log.i(LOG_TAG_NAME, number + " is even");
        } else {
            Log.i(LOG_TAG_NAME, number + " is odd");
        }
    }

    public static void printPyramid(int numberRows) {
        for (int i = numberRows; i > 0; i--) {
            StringBuilder stars = new StringBuilder();
            for (int j = 0; j < i; j++) {
                stars.append("*");
            }
            Log.i(LOG_TAG_NAME, stars.toString());
        }
    }

    public double max(double num1, double num2) {
        return num1 >= num2 ? num1 : num2;
    }
}