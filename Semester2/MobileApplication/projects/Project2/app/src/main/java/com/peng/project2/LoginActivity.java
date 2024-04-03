package com.peng.project2;

import static com.peng.project2.Common.clearErrorsWhenChanged;
import static com.peng.project2.Common.validEmail;
import static com.peng.project2.Common.validPassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getName();
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private Button loginBtn;
    private Button goRegisterBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLayout = findViewById(R.id.emailLayout);
        clearErrorsWhenChanged(emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        clearErrorsWhenChanged(passwordLayout);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> doLogin());

        goRegisterBtn = findViewById((R.id.goBackBtn));
        goRegisterBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, RegisterActivity.class);
            startActivity(myIntent);
        });

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToProducts();
        }
    }

    private boolean validateForm() {
        boolean valid = validEmail(emailLayout);

        if (!validPassword(passwordLayout)) {
            valid = false;
        }

        return valid;
    }

    private void doLogin() {
        boolean valid = validateForm();
        if (!valid) {
            Log.d(TAG, "validate login form failed.");
            return;
        }

        String email = emailLayout.getEditText().getText().toString();
        String pwd = passwordLayout.getEditText().getText().toString();
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        goToProducts();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Login failed, please check " +
                                        "your email and password then try again.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToProducts() {
        Intent myIntent = new Intent(this, ProductListActivity.class);
        startActivity(myIntent);
    }
}