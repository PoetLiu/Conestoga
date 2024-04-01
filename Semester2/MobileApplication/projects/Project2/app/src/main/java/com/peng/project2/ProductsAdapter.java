package com.peng.project2;

import static com.peng.project2.Common.addToCart;
import static com.peng.project2.Common.downloadImage;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;
import com.peng.project2.dao.AppDatabase;
import com.peng.project2.entity.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private final List<Product> productList;
    private final StorageReference storageRef;
    private final AppDatabase db;
    private final FirebaseAuth mAuth;

    public ProductsAdapter(List<Product> productList, StorageReference storageRef, AppDatabase db,
                           FirebaseAuth mAuth) {
        this.productList = productList;
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = productList.get(position);
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
        view.setOnClickListener(v -> {
            Intent myIntent = new Intent(view.getContext(), ProductDetailActivity.class);
            myIntent.putExtra("productId", product.getId());
            myIntent.putExtra("imageURI", product.getTmpImageFile().toURI().toString());
            view.getContext().startActivity(myIntent);
        });

        holder.addToCart.setOnClickListener(v ->
                addToCart(view.getContext(), db, mAuth.getCurrentUser(), product, 1)
        );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImage;
        private final TextView productTitleText;
        private final TextView productSubtitleText;
        private final TextView productColorText;
        private final TextView productPrice;
        private final ImageView addToCart;
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super((inflater.inflate(R.layout.activity_product_listing_item, parent, false)));

            productImage = itemView.findViewById(R.id.productImageView);
            productTitleText = itemView.findViewById(R.id.productTitleTextView);
            productSubtitleText = itemView.findViewById(R.id.productSubtitleTextView);
            productColorText = itemView.findViewById(R.id.productColorsTextView);
            productPrice = itemView.findViewById(R.id.productPriceTextView);
            addToCart = itemView.findViewById(R.id.addToCartImageView);
        }
    }
}
