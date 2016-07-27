package com.example.andreika.conferencegorodit.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.andreika.conferencegorodit.CursorAdapter.ProgramListCursorAdapter;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;


/**
 * Created by andreika on 07.07.2016.
 */
public class FirstHallFragment extends Fragment {
    Cursor cursor;
    ListView lvItems;
    ImageView status;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.hall, container, false);

        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        cursor = mSqLiteDatabase.rawQuery("SELECT schedule_id as _id, presentation_title, hall_name, timestart, presentation_id, id , company, name, photo, position FROM schedule, speakers " +
                "where speaker_id = id and hall_id = ?", new String[]{String.valueOf(1)});

        lvItems = (ListView) view.findViewById(R.id.listView3);


        ProgramListCursorAdapter programListCursorAdapter = new ProgramListCursorAdapter(getActivity(), cursor);

        lvItems.setAdapter(programListCursorAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AboutReportFragment aboutReportFragment = new AboutReportFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idPresentation", cursor.getInt(cursor.getColumnIndexOrThrow("presentation_id")));
                bundle.putInt("idSpeaker", cursor.getInt(cursor.getColumnIndexOrThrow("id")));
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.programm));
    }
}
