package com.example.advancedfragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class InputFragment extends Fragment {

    EditText txtInput;
    Button btnInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_input, container, false);

        txtInput = v.findViewById(R.id.editInput);
        btnInput = v.findViewById(R.id.btnInput);


        btnInput.setOnClickListener(view -> {



            Bundle result = new Bundle();
            String inputText = txtInput.getText().toString();
            result.putString("output", inputText);

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getParentFragmentManager()
                        .setFragmentResult("request", result);
            }
            else {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.inputFrag, OutputFragment.class, result)
                        .commit();
            }
        });


        return v;
    }

}
