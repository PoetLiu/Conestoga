package com.example.notetakingapp21;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogNewNote extends DialogFragment {

    Button btnSave;
    Button btnClose;
    EditText editTitle;
    EditText editDescription;
    CheckBox chkImportant;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new, null);

        btnSave = dialogView.findViewById(R.id.btnSave);
        btnClose = dialogView.findViewById(R.id.btnClose);
        editTitle = dialogView.findViewById(R.id.editTitle);
        editDescription = dialogView.findViewById(R.id.editDescription);
        chkImportant = dialogView.findViewById(R.id.chkImportant);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTitle = editTitle.getText().toString();
                String mDescript = editDescription.getText().toString();
                boolean mImport = chkImportant.isChecked();

                Note n = new Note(mTitle, mDescript, mImport);

                //pass the note and put in array

                dismiss();
            }
        });

        builder.setView(dialogView).setMessage("Create a note!");

        return builder.create();




    }



}
