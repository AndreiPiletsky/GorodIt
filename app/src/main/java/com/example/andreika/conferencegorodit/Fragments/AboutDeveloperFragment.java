package com.example.andreika.conferencegorodit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutDeveloperFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.about_developer, container, false);


        return view;
    }


}
