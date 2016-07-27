package com.example.andreika.conferencegorodit.Fragments;


import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.andreika.conferencegorodit.CursorAdapter.AboutSpeakerListReportCursorAdapter;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.CircleTransform;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutSpeakerFragment extends Fragment {
    ListView listView;
    ImageView photo;
    TextView name, company, position;
    int idSpeaker;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.about_speaker, container, false);

        photo = (ImageView) view.findViewById(R.id.imageView9);
        name = (TextView) view.findViewById(R.id.nameSpeaker);
        company = (TextView) view.findViewById(R.id.titleCompany);
        position = (TextView) view.findViewById(R.id.titlePosition);

        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        String strPhoto = null, strName = null, strCompany = null, strPosition = null;

        Bundle bundle = getArguments();
        idSpeaker = bundle.getInt("idSpeaker");


        cursor = mSqLiteDatabase.rawQuery("SELECT id as _id, company, name, photo, position FROM speakers where _id = ?", new String[]{String.valueOf(idSpeaker)});

        if (cursor.moveToFirst()) {
            do {

                strPhoto = cursor.getString(cursor.getColumnIndexOrThrow("photo"));
                strName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                strCompany = cursor.getString(cursor.getColumnIndexOrThrow("company"));
                strPosition = cursor.getString(cursor.getColumnIndexOrThrow("position"));

            } while (cursor.moveToNext());
        }

        try {
            Picasso.with(getActivity()).load(strPhoto).transform(new CircleTransform()).resize(150, 0).into(photo);
        } catch (Exception e) {
            Picasso.with(getActivity()).load(R.drawable.cover).transform(new CircleTransform()).resize(150, 0).into(photo);
        }
        name.setText(strName);
        company.setText(strCompany);
        position.setText(strPosition);

        cursor = mSqLiteDatabase.rawQuery("SELECT schedule_id as _id, presentation_title, timestart, presentation_id FROM schedule where speaker_id = ?", new String[]{String.valueOf(idSpeaker)});

        listView = (ListView) view.findViewById(R.id.listView2);

        AboutSpeakerListReportCursorAdapter aboutSpeakerListReportCursorAdapter = new AboutSpeakerListReportCursorAdapter(getActivity(), cursor);

        listView.setAdapter(aboutSpeakerListReportCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AboutReportFragment aboutReportFragment = new AboutReportFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idPresentation", cursor.getInt(cursor.getColumnIndexOrThrow("presentation_id")));
                bundle.putInt("idSpeaker", idSpeaker);
                aboutReportFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, aboutReportFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.about_speaker));
    }
}
