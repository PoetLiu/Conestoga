package com.example.simplefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class MyFirstFragment extends Fragment {


    EditText txtFragInput;
    Button btnResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_layout, container, false);


        txtFragInput = v.findViewById(R.id.editFragInput);
        btnResult = v.findViewById(R.id.btnFrag);

        //Send the data from the Fragment when we click the button
        btnResult.setOnClickListener(view -> {
            Bundle result = new Bundle();
            String input = txtFragInput.getText().toString();
            result.putString("passedString", input);
            getParentFragmentManager().setFragmentResult("request", result);
        });
        return v;
    }
}
