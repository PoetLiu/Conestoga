package com.example.advancedfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class OutputFragment extends Fragment {

    TextView txtOutput;

    private static final String PASSED_STRING_KEY = "output";

    private String outputString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_output, container, false);
        txtOutput = v.findViewById(R.id.txtOutput);


        if (getArguments() != null) {
            outputString = getArguments().getString(PASSED_STRING_KEY);
            txtOutput.setText(outputString);
        }

        getParentFragmentManager().setFragmentResultListener("request", this,
                (requestKey, result) -> {
            outputString = result.getString(PASSED_STRING_KEY);
            txtOutput.setText(outputString);
        });

        return v;
    }
}
