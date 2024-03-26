package com.peng.lab3;

import static com.peng.lab3.Constants.EMAIL_FIELD_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtName;
    private EditText txtEmail;
    private CheckBox chkSubscribe;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.editName);
        txtEmail = findViewById(R.id.editEmail);
        chkSubscribe = findViewById(R.id.chkSubscribe);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            Account acc = new Account();
            acc.setName(txtName.getText().toString());
            acc.setEmail(txtEmail.getText().toString());
            acc.setSubscribed(chkSubscribe.isChecked());

            Intent intent = new Intent(this, DisplayActivity.class);
            intent.putExtra(EMAIL_FIELD_NAME, acc.getEmail());
            startActivity(intent);
        });
    }
}