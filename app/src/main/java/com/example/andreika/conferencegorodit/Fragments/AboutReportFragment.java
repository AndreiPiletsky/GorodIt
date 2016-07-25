package com.example.andreika.conferencegorodit.Fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.Database.CheckStatus;
import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.CircleTransform;
import com.example.andreika.conferencegorodit.Transform.TransferUnixTime;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutReportFragment extends Fragment {
    TextView textViewName, textViewCompany, textViewTitle, textViewDate, textViewAboutReport;
    private int idSpeaker, idPresentation;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;
    LinearLayout linearLayout;

    ImageView imageViewStatus;
    boolean isLikedReport = false;
    private Cursor cursor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.about_report, container, false);
        imageViewStatus = (ImageView) view.findViewById(R.id.imageViewStatus);
        linearLayout = (LinearLayout) view.findViewById(R.id.reportToSpeaker);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewCompany = (TextView) view.findViewById(R.id.textViewCompany);
        textViewAboutReport = (TextView) view.findViewById((R.id.textViewAbout));
        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDate = (TextView) view.findViewById(R.id.textViewTime);

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        String strTitle = null;
        String strAboutReport = null;
        String strName = null;
        String strCompany = null;
        long strStartTime = 0;

        Bundle bundle = getArguments();
        idSpeaker = bundle.getInt("idSpeaker");
        idPresentation = bundle.getInt("idPresentation");

        cursor = mSqLiteDatabase.rawQuery("SELECT schedule_id as _id, presentation_title, presentation_about, timestart, id as _id, company, name, photo FROM schedule, speakers " +
                "where speaker_id = id and presentation_id = ?", new String[]{String.valueOf(idPresentation)});

        if (cursor.moveToFirst()) {
            do {

                strTitle = cursor.getString(cursor.getColumnIndexOrThrow("presentation_title"));
                strAboutReport = cursor.getString(cursor.getColumnIndexOrThrow("presentation_about"));
                strStartTime = cursor.getLong(cursor.getColumnIndexOrThrow("timestart"));
                strName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                strCompany = cursor.getString(cursor.getColumnIndexOrThrow("company"));


            } while (cursor.moveToNext());


        }

        textViewTitle.setText(strTitle);
        textViewAboutReport.setText(strAboutReport);
        textViewDate.setText(new TransferUnixTime().convertTime(strStartTime));
        textViewName.setText(strName);
        textViewCompany.setText(strCompany);

        if (new CheckStatus().isStatus(getActivity(), idPresentation)) {
            isLikedReport=true;
            Picasso.with(getActivity()).load(R.drawable.del).into(imageViewStatus);
        } else {
            Picasso.with(getActivity()).load(R.drawable.add).into(imageViewStatus);
            isLikedReport=false;
        }


        imageViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isLikedReport) {
                    Picasso.with(getActivity()).load(R.drawable.del).into(imageViewStatus);
                    isLikedReport = true;

                    ContentValues values = new ContentValues();

                    values.put(mDatabaseHelper.LIKED_COLUMN_PRESENTATION_ID, idPresentation);

                    mSqLiteDatabase.insert(mDatabaseHelper.TABLE_LIKED, null, values);


                } else if (isLikedReport) {
                    Picasso.with(getActivity()).load(R.drawable.add).into(imageViewStatus);
                    isLikedReport = false;

                    mSqLiteDatabase.delete(mDatabaseHelper.TABLE_LIKED,
                            "liked_presentation_id = ?",
                            new String[]{String.valueOf(idPresentation)});

                }


            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AboutSpeakerFragment aboutSpeakerFragment = new AboutSpeakerFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idSpeaker", idSpeaker);
                aboutSpeakerFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, aboutSpeakerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("О докладчике");
            }
        });


        return view;
    }

}
