package com.peng.project2;

import static com.peng.project2.Common.downloadImage;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.StorageReference;
import com.peng.project2.entity.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private final List<Product> productList;
    private final StorageReference storageRef;

    public ProductsAdapter(List<Product> productList, StorageReference storageRef) {
        this.productList = productList;
        this.storageRef = storageRef;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product entity = productList.get(position);
        downloadImage(storageRef, entity.getImageUrl(),
                (file) -> holder.productImage.setImageURI(Uri.fromFile(file)),
                null
        );
        holder.productTitleText.setText(entity.getTitle());
        holder.productSubtitleText.setText(entity.getSubTitle());
        holder.productColorText.setText(entity.getColorInfo());
        holder.productPrice.setText(entity.getPrice().toString());
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
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super((inflater.inflate(R.layout.activity_product_listing_item, parent, false)));

            productImage = itemView.findViewById(R.id.productImageView);
            productTitleText = itemView.findViewById(R.id.productTitleTextView);
            productSubtitleText = itemView.findViewById(R.id.productSubtitleTextView);
            productColorText = itemView.findViewById(R.id.productColorsTextView);
            productPrice = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}
