package com.peng.project2;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Cart;
import com.peng.project2.entity.CartItem;
import com.peng.project2.entity.Product;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Common {
    private static final String TAG = Common.class.getName();
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

    public static boolean validField(TextInputLayout layout, String prefix, Pattern pattern,
                                     String description) {
        if (description == null) {
            description = "";
        }

        String name = layout.getEditText().getText().toString();
        if (Strings.isNullOrEmpty(name)) {
            layout.setError(prefix + " is required.");
            return false;
        }
        if (pattern != null && !pattern.matcher(name).matches()) {
            layout.setError(prefix + " is not valid. " + description);
            return false;
        }
        return true;
    }


    public static final Pattern NAME = Pattern.compile("^[a-zA-Z ,.'-]{2,128}$");
    public static boolean validName(TextInputLayout layout, String prefix) {
        return validField(layout, prefix, NAME, null);
    }

    public static boolean validPhone(TextInputLayout layout) {
        return validField(layout, "Phone", Patterns.PHONE, null);
    }

    public static boolean validEmail(TextInputLayout layout) {
        return validField(layout, "Email", Patterns.EMAIL_ADDRESS, null);
    }

    public static final Pattern PASSWORD = Pattern.compile("^[a-zA-Z0-9]{6,32}$");
    public static boolean validPassword(TextInputLayout layout) {
        return validField(layout, "Password", PASSWORD,
                "It consists only alphanumeric characters and at least 6 long.");
    }

    public static final Pattern ADDRESS = Pattern.compile("^[a-zA-Z0-9 -]{16,128}$");
    public static boolean validAddress(TextInputLayout layout) {
        return validField(layout, "Address", ADDRESS, null);
    }

    public static final Pattern CITY = Pattern.compile("^[a-zA-Z]{4,32}$");
    public static boolean validCity(TextInputLayout layout) {
        return validField(layout, "City", CITY,
                "It consists only alpha characters and at least 4 long.");
    }

    public static final Pattern POSTCODE = Pattern.compile("^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$");
    public static boolean validPostcode(TextInputLayout layout) {
        return validField(layout, "PostCode", POSTCODE, null);
    }

    public static void downloadImage(StorageReference storageRef, String url, Consumer<File> onSuccess,
                              Consumer<Exception> onFailure) {
        StorageReference imgRef = storageRef.child(url);
        String suffix = FilenameUtils.getExtension(url);
        File localFile = null;
        try {
            localFile = File.createTempFile("images", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File finalLocalFile = localFile;
        imgRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            // Local temp file has been created
            Log.d(TAG, "Download img succeed, file:" + finalLocalFile.getAbsolutePath());
            if (onSuccess != null) {
                onSuccess.accept(finalLocalFile);
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
            Log.w(TAG, "Download img failed", exception);
            if (onFailure != null) {
                onFailure.accept(exception);
            }
        });
    }

    public static void addToCart(Context context, AppDatabase db, FirebaseUser user, Product product,
                                 int quantity) {
        if (product.getStock() < quantity) {
            Toast.makeText(context,
                    "Add to cart failed! This product is out of stock.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        Cart cart = db.cartDao().selectOne(user.getUid());
        CartItem item = db.cartItemDao().selectOne(cart.getId(), product.getId());
        if (item == null) {
            item = new CartItem(cart.getId(), product.getId(), (short) 0);
        }
        item.setQuantity((short) (item.getQuantity() + quantity));
        db.cartItemDao().insetOrUpdate(item);

        Toast.makeText(context,
                "Add to cart successfully!",
                Toast.LENGTH_SHORT
        ).show();
    }

    public static void initToolBar(AppCompatActivity activity) {
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(v -> activity.getOnBackPressedDispatcher().onBackPressed());
        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.shopping_cart) {
                Intent myIntent = new Intent(activity, CartActivity.class);
                activity.startActivity(myIntent);
                return true;
            }
            return false;
        });
    }
}
