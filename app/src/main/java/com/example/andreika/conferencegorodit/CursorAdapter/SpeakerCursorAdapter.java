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
import com.example.andreika.conferencegorodit.Transform.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 * Created by andreika on 20.07.2016.
 */
public class SpeakerCursorAdapter extends CursorAdapter {


    public SpeakerCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.speaker_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        ImageView photo = (ImageView) view.findViewById(R.id.imageViewSpeaker);

        TextView nameSpeaker = (TextView) view.findViewById(R.id.nameSpeaker);
        TextView titleCompany = (TextView) view.findViewById(R.id.titleCompany);
        TextView titlePosition = (TextView) view.findViewById(R.id.titlePosition);

        String image = cursor.getString(cursor.getColumnIndexOrThrow("photo"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
        String position = cursor.getString(cursor.getColumnIndexOrThrow("position"));


        try {
            Picasso.with(context).load(image).transform(new CircleTransform()).resize(150,0).into(photo);
        } catch (Exception e) {
            Picasso.with(context).load(R.drawable.cover).transform(new CircleTransform()).resize(150,0).into(photo);
        }
        nameSpeaker.setText(String.valueOf(name));
        titleCompany.setText(String.valueOf(company));
        titlePosition.setText(String.valueOf(position));
    }
}