package com.peng.project2;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;
import com.google.common.base.Strings;

public class Common {
    public static void clearErrorsWhenChanged(TextInputLayout layout) {
        layout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                layout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static boolean validEmail(TextInputLayout layout) {
        boolean valid = true;
        String email = layout.getEditText().getText().toString();
        if (Strings.isNullOrEmpty(email)) {
            layout.setError("email is required.");
            valid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout.setError("email is not valid.");
            valid = false;
        }
        return valid;
    }

    public static boolean validPassword(TextInputLayout layout) {
        boolean valid = true;
        String pwd = layout.getEditText().getText().toString();

        if (Strings.isNullOrEmpty(pwd)) {
            layout.setError("password is required.");
            valid = false;
        }

        if (pwd.length() < 6) {
            layout.setError("password is at least 6 characters.");
            valid = false;
        }

        return valid;
    }
}
