package com.example.simplefragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtFragOutput;
    EditText editInput;
    Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFragOutput = findViewById(R.id.txtFragOutput);
        editInput = findViewById(R.id.editInput);

        getSupportFragmentManager().setFragmentResultListener("request", this,
                (requestKey, result) -> {
                    String output = result.getString("passedString");
                    txtFragOutput.setText(output);
                });

        //receive the data from the fragment
        getSupportFragmentManager().setFragmentResultListener("request", this,
                new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String output = result.getString("passedString");
                txtFragOutput.setText(output);

            }
        });




        btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(view ->{

            Bundle sendData = new Bundle();
            sendData.putString("sendString", editInput.getText().toString());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragHolder, SecondFragment.class, sendData)
                    .commit();

        });



    }
}