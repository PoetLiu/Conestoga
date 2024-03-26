package com.example.simplefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {


    TextView txtDisplay;

    public SecondFragment() {
    }


/*
    You can add this code if you need to create instances
    of the fragment somewhere

    For this app we do not, we pass the class directly

    public static SecondFragment newInstance(String myData) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(PASSED_STRING_KEY, myData);
        fragment.setArguments(args);
        return fragment;
    }
*/

    private static final String PASSED_STRING_KEY = "sendString";
    private String passedString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            passedString = getArguments().getString(PASSED_STRING_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        txtDisplay = v.findViewById(R.id.txtDisplay);
        txtDisplay.setText(passedString);
        return v;
    }
}