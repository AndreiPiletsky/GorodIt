package com.example.andreika.conferencegorodit.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.TransferUnixTime;
import com.squareup.picasso.Picasso;


/**
 * Created by andreika on 20.07.2016.
 */
public class AboutSpeakerListReportCursorAdapter extends CursorAdapter {


    public AboutSpeakerListReportCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.about_speaker_list_reports, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView startTime = (TextView) view.findViewById(R.id.startTime);
        TextView titleReport = (TextView) view.findViewById(R.id.titleReport);


        long strStartTime = cursor.getLong(cursor.getColumnIndexOrThrow("timestart"));
        String strTitleReport = cursor.getString(cursor.getColumnIndexOrThrow("presentation_title"));

        startTime.setText(new TransferUnixTime().convertTime(strStartTime));
        titleReport.setText(strTitleReport);

    }
}