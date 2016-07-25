package com.example.andreika.conferencegorodit.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutConferenceFragment extends Fragment {
    private SQLiteDatabase mSqLiteDatabase;
    private DatabaseHelper mDatabaseHelper;
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseHelper = new DatabaseHelper(getActivity());


        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.about_conference, container, false);

        mDatabaseHelper = new DatabaseHelper(getActivity());


        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        textView = (TextView) view.findViewById(R.id.textView4);


        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM gorodIt", null);
        if (c.moveToFirst()) {
            textView.setText(c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_ABOUT)));
        }
        return view;


    }

}
