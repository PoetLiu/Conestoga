package com.example.notetakingapp21;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DialogShowNote extends DialogFragment {


    private RecyclerView rView;
    private RecyclerView.Adapter adapter;
    private List<Note> myList;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show, null);


        MainActivity ma = (MainActivity)getActivity();
        myList = ma.getNoteList();

        rView = dialogView.findViewById(R.id.rView);
        adapter = new NoteAdapter(myList);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        rView.setAdapter(adapter);



        builder.setView(dialogView).setMessage("Show your notes!");
        return builder.create();

    }


}
