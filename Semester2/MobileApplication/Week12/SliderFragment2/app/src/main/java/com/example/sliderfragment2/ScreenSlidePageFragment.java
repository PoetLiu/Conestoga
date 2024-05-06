package com.example.sliderfragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {

    private TextView txtTitle;

    public static ScreenSlidePageFragment newInstance(String title){

        Bundle args = new Bundle();
        args.putString("FRAG_TITLE", title);
        ScreenSlidePageFragment frag = new ScreenSlidePageFragment();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_screen_slide, container, false);
        txtTitle = v.findViewById(R.id.txtTitle);
        txtTitle.setText(getArguments().getString("FRAG_TITLE"));
        return v;
    }
}
