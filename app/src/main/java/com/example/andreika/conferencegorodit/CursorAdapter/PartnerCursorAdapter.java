package com.example.andreika.conferencegorodit.CursorAdapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;



import com.example.andreika.conferencegorodit.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by andreika on 20.07.2016.
 */
public class PartnerCursorAdapter extends CursorAdapter {


    public PartnerCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.partner_list, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView logo = (ImageView) view.findViewById(R.id.imageView);
        TextView titleCompany = (TextView) view.findViewById(R.id.titlePartner);
        TextView aboutCompany = (TextView) view.findViewById(R.id.aboutPartner);


        String title = cursor.getString(cursor.getColumnIndexOrThrow("company"));
        String image = cursor.getString(cursor.getColumnIndexOrThrow("logo"));
        String about = cursor.getString(cursor.getColumnIndexOrThrow("about"));





        titleCompany.setText(String.valueOf(title));
        aboutCompany.setText(String.valueOf(about));

        Picasso.with(context).load(image).resize(300,0).into(logo);

    }
}