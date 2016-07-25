package com.example.andreika.conferencegorodit.Fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andreika.conferencegorodit.CursorAdapter.PartnerCursorAdapter;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorFragment extends Fragment {


    Cursor todoCursor;
    ListView lvItems;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.sponsor_layout, container, false);

        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        todoCursor = mSqLiteDatabase.rawQuery("SELECT id as _id, company,logo,about FROM partnersandsponsors", null);


        lvItems = (ListView) view.findViewById(R.id.listView);

        PartnerCursorAdapter partnerCursorAdapter = new PartnerCursorAdapter(getActivity(), todoCursor);

        lvItems.setAdapter(partnerCursorAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = mSqLiteDatabase.
                        rawQuery("SELECT id as _id, site FROM partnersandsponsors where _id = ?", new String[]{String.valueOf(id)});


                if (cursor.moveToFirst()) {
                    do {

                        String site = cursor.getString(cursor.getColumnIndexOrThrow("site"));
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
                        startActivity(browserIntent);
                    } while (cursor.moveToNext());
                }

            }
        });

        return view;
    }


}
