package com.peng.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);
        Log.i("info", "our first message");
        Toast.makeText(this, "Best prof", Toast.LENGTH_LONG).show();
        logActivity("Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logActivity("Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logActivity("Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logActivity("Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logActivity("Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logActivity("Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logActivity("Restart");
    }

    private static void logActivity(String type) {
        Log.i(type, "This is the on " + type + " method.");
    }
}