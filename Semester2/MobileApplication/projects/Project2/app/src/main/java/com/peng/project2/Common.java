package com.peng.project2;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.dao.CartWithItems;
import com.peng.project2.entity.Cart;
import com.peng.project2.entity.CartItem;
import com.peng.project2.entity.Product;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.io.FilenameUtils;
import android.content.Context;

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

        CartWithItems cartWithItems = db.cartDao().getCartByUserUid(user.getUid());
        Cart cart = cartWithItems.getCart();

        CartItem item = cartWithItems.getItems().stream().filter(
                        i -> Objects.equals(i.getProductId(), product.getId())
                ).findFirst()
                .orElse(new CartItem(cart.getId(), product.getId(), (short)0));

        item.setQuantity((short) (item.getQuantity() + quantity));

        cart.setSubTotal(cart.getSubTotal() + product.getPrice() * quantity);
        cart.updatePrice();
        db.cartDao().update(cart);

        db.cartItemDao().insetOrUpdate(item);

        Toast.makeText(context,
                "Add to cart successfully!",
                Toast.LENGTH_SHORT
        ).show();
    }
}
