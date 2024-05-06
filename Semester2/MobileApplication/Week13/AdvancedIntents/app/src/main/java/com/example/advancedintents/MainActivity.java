package com.example.advancedintents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    Button btnMessage;
    Button btnCalendar;
    EditText txtBody;
    CalendarView cView;
    ImageView imgCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);
        }
        txtBody = findViewById(R.id.txtMessage);
        btnCamera = findViewById(R.id.btnCamera);
        btnMessage = findViewById(R.id.btnText);
        btnCalendar = findViewById(R.id.btnCalendar);
        cView = findViewById(R.id.calendarView);
        imgCamera = findViewById(R.id.imgCamera);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, txtBody.getText());
                startActivity(i);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = cView.getDate();
                Intent i = new Intent(Intent.ACTION_INSERT);
                i.setData(CalendarContract.Events.CONTENT_URI);
                i.putExtra(CalendarContract.Events.TITLE, "MY EVENT");
                i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time);
                startActivity(i);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgCamera.setImageBitmap(imageBitmap);
        }
    }
}