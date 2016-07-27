package com.example.andreika.conferencegorodit.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreika.conferencegorodit.Database.CheckStatus;
import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.CircleTransform;
import com.example.andreika.conferencegorodit.Transform.TransferUnixTime;
import com.squareup.picasso.Picasso;

/**
 * Created by andreika on 20.07.2016.
 */
public class ProgramListCursorAdapter extends CursorAdapter {

    ImageView status;
    ImageView photo;
    TextView hallName, titleReport, nameSpeaker, titleCompany, titlePosition, timeStart;
    private String image, strHallName, strTitleReport, strName, strCompany, strPosition;
    long strTime;
    int idPresentation = 0;
    int position = 0;

    public ProgramListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.hall_list, parent, false);
    }


    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        photo = (ImageView) view.findViewById(R.id.imageViewPhoto);
        status = (ImageView) view.findViewById(R.id.imageViewStatus);
        timeStart = (TextView) view.findViewById(R.id.textViewTime);
        hallName = (TextView) view.findViewById(R.id.textViewHall);
        titleReport = (TextView) view.findViewById(R.id.textViewTitle);
        nameSpeaker = (TextView) view.findViewById(R.id.textViewName);
        titleCompany = (TextView) view.findViewById(R.id.textViewCompany);
        titlePosition = (TextView) view.findViewById(R.id.textViewPosition);

        position = cursor.getPosition();
        cursor.moveToPosition(position);
        image = cursor.getString(cursor.getColumnIndexOrThrow("photo"));
        idPresentation = cursor.getInt(cursor.getColumnIndexOrThrow("presentation_id"));
        strHallName = cursor.getString(cursor.getColumnIndexOrThrow("hall_name"));
        strTime = cursor.getLong(cursor.getColumnIndexOrThrow("timestart"));
        strTitleReport = cursor.getString(cursor.getColumnIndexOrThrow("presentation_title"));
        strName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        strCompany = cursor.getString(cursor.getColumnIndexOrThrow("company"));
        strPosition = cursor.getString(cursor.getColumnIndexOrThrow("position"));


        if (new CheckStatus().isStatus(context,idPresentation)) {
            Picasso.with(context).load(R.drawable.isliked).into(status);
        }

        else {Picasso.with(context).load(android.R.color.transparent).into(status);}


        try {
            Picasso.with(context).load(image).transform(new CircleTransform()).resize(150, 0).into(photo);

        } catch (Exception e) {
            Picasso.with(context).load(R.drawable.cover).transform(new CircleTransform()).resize(150, 0).into(photo);
        }
        hallName.setText(strHallName);
        timeStart.setText(new TransferUnixTime().convertTime(strTime));
        nameSpeaker.setText(strName);
        titleReport.setText(strTitleReport);
        titleCompany.setText(strCompany);
        titlePosition.setText(strPosition);

    }


}