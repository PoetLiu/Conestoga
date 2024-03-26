package com.peng.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CellphoneAdapter extends RecyclerView.Adapter<CellphoneAdapter.MyViewHolder> {
    private final List<Cellphone> cellphoneList;

    public CellphoneAdapter(List<Cellphone> cellphoneList) {
        this.cellphoneList = cellphoneList;
    }

    @NonNull
    @Override
    public CellphoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CellphoneAdapter.MyViewHolder holder, int position) {
        Cellphone cellphone = cellphoneList.get(position);

        Context context = holder.itemView.getContext();
        int resId = context.getResources().getIdentifier(
                cellphone.getImage(), "drawable", context.getPackageName()
        );
        holder.img.setImageResource(resId);
        holder.brandText.setText(cellphone.getModel());
        holder.priceText.setText(cellphone.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return cellphoneList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView brandText;
        private final TextView priceText;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
           super(inflater.inflate(R.layout.pager_layout, parent, false));
           img = itemView.findViewById(R.id.imgPhone);
           brandText = itemView.findViewById(R.id.txtBrand);
           priceText = itemView.findViewById(R.id.txtPrice);
        }
    }
}
