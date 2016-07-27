package com.example.andreika.conferencegorodit.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.andreika.conferencegorodit.CursorAdapter.LikedListCursorAdapter;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LikedFragment extends Fragment {

    Cursor cursor;
    ListView lvItems;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.liked_layout, container, false);

        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        cursor = mSqLiteDatabase.rawQuery("SELECT schedule_id as _id, presentation_title, timestart, id, company, name, liked_id, liked_presentation_id FROM schedule, speakers, liked " +
                "where speaker_id = id and presentation_id = liked_presentation_id", null);


        lvItems = (ListView) view.findViewById(R.id.listView4);
        LikedListCursorAdapter likedListCursorAdapter = new LikedListCursorAdapter(getActivity(), cursor);

        lvItems.setAdapter(likedListCursorAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.liked));
    }


}
