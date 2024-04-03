package com.peng.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = UserActivity.class.getName();
    private TextView emailTextView;
    private Button logoutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();
        Log.i(TAG, "Got user:" + mAuth.getCurrentUser());

        emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText(mAuth.getCurrentUser().getEmail());

        logoutBtn = findViewById(R.id.logOutButton);
        logoutBtn.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Logout", (dialog, id) -> {
                        mAuth.signOut();
                        Intent myIntent = new Intent(this, LoginActivity.class);
                        startActivity(myIntent);
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> {
                        // User cancels the dialog.
                        dialog.cancel();
                    })
                    .create()
                    .show();
        });

        Common.initToolBar(this);
    }
}