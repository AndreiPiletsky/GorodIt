package com.example.andreika.conferencegorodit.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.Fragments.AboutReportFragment;
import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.TransferUnixTime;

/**
 * Created by andreika on 20.07.2016.
 */
public class LikedListCursorAdapter extends CursorAdapter {

    private SQLiteDatabase mSqLiteDatabase;
    private TextView textViewName, textViewCompany, textViewTitle, textViewDate, textViewAboutReport;
    private LinearLayout linearLayout;


    private ImageView imageViewStatus;
    private DatabaseHelper mDatabaseHelper;

    public LikedListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.liked_list, parent, false);
    }


    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {



        imageViewStatus = (ImageView) view.findViewById(R.id.imageViewStatus);
        linearLayout = (LinearLayout) view.findViewById(R.id.reportToSpeaker);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewCompany = (TextView) view.findViewById(R.id.textViewCompany);

        textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        textViewDate = (TextView) view.findViewById(R.id.textViewTime);

        final int position = cursor.getPosition();

        imageViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cursor.moveToPosition(position);
                int likedID = cursor.getInt(cursor.getColumnIndex("liked_id"));


                new DeleteLikedItem().delete(context,likedID);
                cursor.requery();
                notifyDataSetChanged();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor.moveToPosition(position);

                int idSpeaker = cursor.getInt(cursor.getColumnIndex("id"));
                int idPresentation = cursor.getInt(cursor.getColumnIndex("liked_presentation_id"));

                AboutReportFragment aboutReportFragment = new AboutReportFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idPresentation", idPresentation);
                bundle.putInt("idSpeaker", idSpeaker);
                aboutReportFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, aboutReportFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });


        String strTitle = cursor.getString(cursor.getColumnIndexOrThrow("presentation_title"));
        long strStartTime = cursor.getLong(cursor.getColumnIndexOrThrow("timestart"));
        String strName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String strCompany = cursor.getString(cursor.getColumnIndexOrThrow("company"));

        textViewTitle.setText(strTitle);

        textViewDate.setText(new TransferUnixTime().convertTime(strStartTime));
        textViewName.setText(strName);
        textViewCompany.setText(strCompany);
    }
}