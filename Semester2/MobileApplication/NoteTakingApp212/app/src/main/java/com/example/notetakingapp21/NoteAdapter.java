package com.example.notetakingapp21;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{

    private List<Note> mNotes;

    public NoteAdapter(List<Note> notes) {
        mNotes = notes;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new MyViewHolder(layoutInflater, parent);

    }

    public void onBindViewHolder(MyViewHolder holder, int position) {

        Note note = mNotes.get(position);
        holder.txtSeeName.setText(note.getTitle());
        holder.txtSeeDescription.setText(note.getDescription());


        if (note.isImportant()){
            holder.txtSeeImportant.setText("Important Note!");
        }
        else {
            holder.txtSeeImportant.setText("Not important!");
        }


    }

    public int getItemCount(){

        return mNotes.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtSeeName;
        TextView txtSeeImportant;
        TextView txtSeeDescription;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.row_layout, parent, false));

            txtSeeName = itemView.findViewById(R.id.txtSeeName);
            txtSeeImportant = itemView.findViewById(R.id.txtSeeImportant);
            txtSeeDescription = itemView.findViewById(R.id.txtSeeDescription);

        }

    }



}
