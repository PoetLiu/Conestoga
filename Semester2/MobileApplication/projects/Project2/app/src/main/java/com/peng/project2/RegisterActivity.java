package com.peng.project2;

import static com.peng.project2.Common.clearErrorsWhenChanged;
import static com.peng.project2.Common.validEmail;
import static com.peng.project2.Common.validPassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Cart;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getName();
    private TextView goLoginTxt;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;
    private CheckBox agreePolicyCk;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = AppDatabase.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        goLoginTxt = findViewById(R.id.goLoginTxt);
        goLoginTxt.setOnClickListener(v -> goToLogin());

        emailLayout = findViewById(R.id.emailLayout);
        clearErrorsWhenChanged(emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        clearErrorsWhenChanged(passwordLayout);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        clearErrorsWhenChanged(confirmPasswordLayout);
        agreePolicyCk = findViewById(R.id.agreeCheckBox);
        agreePolicyCk.setOnClickListener(v -> agreePolicyCk.setError(null));
        registerBtn = findViewById(R.id.goRegisterBtn);

        registerBtn.setOnClickListener(v -> {
            boolean valid = validateForm();
            if (!valid) {
                Log.d(TAG, "validate register form failed.");
                return;
            }

            String email = emailLayout.getEditText().getText().toString();
            String pwd = passwordLayout.getEditText().getText().toString();
            mAuth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            initUser();
                            showSuccessDialog();
                            mAuth.signOut();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            String cause = task.getException().getLocalizedMessage();
                            Toast.makeText(RegisterActivity.this,
                                    "Register failed: " + cause,
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });
        });
    }

    private void initUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        Cart cart = new Cart(user.getUid());
        db.cartDao().insert(cart);
    }

    private void goToLogin() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    private void showSuccessDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Register Successfully")
                .setMessage("Your account has been created successfully!")
                .setPositiveButton("Login Now", (dialog, id) -> {
                    // User taps OK button.
                    goToLogin();
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // User cancels the dialog.
                    dialog.cancel();
                })
                .create()
                .show();
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailLayout.getEditText().getText().toString();
        String pwd = passwordLayout.getEditText().getText().toString();
        String confirmPwd = confirmPasswordLayout.getEditText().getText().toString();
        Boolean agreePolicy = agreePolicyCk.isChecked();

        Log.d(TAG, String.format("Got form data: email:%s pwd:%s confirmPwd:%s checked:%b",
                email, pwd, confirmPwd, agreePolicy)
        );

        if (!validEmail(emailLayout)) {
            valid = false;
        }
        if (!validPassword(passwordLayout)) {
            valid = false;
        }
        if (!validPassword(confirmPasswordLayout)) {
            valid = false;
        }
        if (!Objects.equals(pwd, confirmPwd)) {
            confirmPasswordLayout.setError("The password doesn't match the previous one.");
            valid = false;
        }
        if (!agreePolicy) {
            valid = false;
            agreePolicyCk.setError("Please check agree.");
        }

        return valid;
    }
}