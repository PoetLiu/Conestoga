package com.peng.project2;

import static com.peng.project2.Common.downloadImage;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.dao.CartFull;
import com.peng.project2.dao.CartItemFull;
import com.peng.project2.entity.CartItem;
import com.peng.project2.entity.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private final CartFull cart;
    private final StorageReference storageRef;
    private final AppDatabase db;
    private final FirebaseAuth mAuth;

    public CartAdapter(CartFull cart, StorageReference storageRef, AppDatabase db,
                       FirebaseAuth mAuth) {
        this.cart = cart;
        this.storageRef = storageRef;
        this.db = db;
        this.mAuth = mAuth;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater, parent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartItemFull itemFull = cart.getItems().get(position);
        Product product = itemFull.getProduct();
        CartItem item = itemFull.getEntity();

        downloadImage(storageRef, product.getImageUrl(),
                (file) -> {
                    product.setTmpImageFile(file);
                    holder.productImage.setImageURI(Uri.fromFile(file));
                },
                null
        );

        holder.productTitleText.setText(product.getTitle());
        holder.productSubtitleText.setText(product.getSubTitle());
        holder.productColorText.setText(product.getColorInfo());
        holder.productPrice.setText(product.getPrice().toString());

        View view = holder.itemView;
        holder.minusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
            if (quantity > 1) {
                holder.quantityTextView.setText(Integer.toString(quantity-1));
                changeQuantity(position, -1);
            } else {
                Toast.makeText(view.getContext(), "The minimal quantity is 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.quantityTextView.setText(item.getQuantity().toString());

        holder.plusButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
            if (quantity < product.getStock()) {
                holder.quantityTextView.setText(Integer.toString(quantity+1));
                changeQuantity(position, 1);
            } else {
                Toast.makeText(view.getContext(),
                        "The maximum quantity is " + product.getStock(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        holder.removeButton.setOnClickListener(v -> {
                new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle("Remove Product")
                        .setMessage("Are you sure you want to remove it?")
                        .setPositiveButton("Remove", (dialog, id) -> {
                            removeAt(position);
                        })
                        .setNegativeButton("Cancel", (dialog, id) -> {
                            // User cancels the dialog.
                            dialog.cancel();
                        })
                        .create()
                        .show();
        });
    }

    private void changeQuantity(int position, int delta) {
        CartItem item = cart.updateQuantity(position, delta);
        db.cartItemDao().insetOrUpdate(item);

        notifyDataSetChanged();
    }
    private void removeAt(int position) {
        CartItem item = cart.removeItem(position);
        db.cartItemDao().delete(item);

        notifyDataSetChanged();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, 1);
    }

    @Override
    public int getItemCount() {
        return cart.getItems().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImage;
        private final TextView productTitleText;
        private final TextView productSubtitleText;
        private final TextView productColorText;
        private final TextView productPrice;
        private final Button plusButton;
        private final TextView quantityTextView;
        private final Button minusButton;
        private final Button removeButton;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super((inflater.inflate(R.layout.activity_cart_item, parent, false)));

            productImage = itemView.findViewById(R.id.productImageView);
            productTitleText = itemView.findViewById(R.id.productTitleTextView);
            productSubtitleText = itemView.findViewById(R.id.productSubtitleTextView);
            productColorText = itemView.findViewById(R.id.productColorsTextView);
            productPrice = itemView.findViewById(R.id.productPriceTextView);
            plusButton = itemView.findViewById(R.id.plusButton);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            minusButton = itemView.findViewById(R.id.minusButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}
