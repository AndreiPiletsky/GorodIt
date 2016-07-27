package com.example.andreika.conferencegorodit.Fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andreika.conferencegorodit.CursorAdapter.SpeakerCursorAdapter;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeakerFragment extends Fragment {


    Cursor cursor;
    ListView lvItems;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.speaker_layout, container, false);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        cursor = mSqLiteDatabase.rawQuery("SELECT id as _id, company, name, photo, position FROM speakers ORDER BY name COLLATE LOCALIZED", null);


        lvItems = (ListView) view.findViewById(R.id.listView);

        SpeakerCursorAdapter speakerCursorAdapter = new SpeakerCursorAdapter(getActivity(), cursor);

        lvItems.setAdapter(speakerCursorAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idSpeaker = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));


                AboutSpeakerFragment aboutSpeakerFragment = new AboutSpeakerFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idSpeaker", idSpeaker);

                aboutSpeakerFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, aboutSpeakerFragment);
                fragmentTransaction.addToBackStack("speakers");
                fragmentTransaction.commit();



            }


        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.speakers));
    }

}
