package com.example.viewpager2test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private ArrayList<Person> mPerson;

    public PersonAdapter(ArrayList<Person> person) {
        mPerson = person;
    }

    @NonNull
    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        Person p = mPerson.get(position);

        Context actContext = holder.itemView.getContext();
        int resid = actContext.getResources().getIdentifier(p.getImg(), "drawable", actContext.getPackageName());

        holder.txtName.setText(p.getName());
        holder.imgPic.setImageResource(resid);

    }

    @Override
    public int getItemCount() {
        return mPerson.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgPic;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.pager_layout, parent, false));
            txtName = itemView.findViewById(R.id.txtPersonName);
            imgPic = itemView.findViewById(R.id.imgPerson);
        }



    }
}
