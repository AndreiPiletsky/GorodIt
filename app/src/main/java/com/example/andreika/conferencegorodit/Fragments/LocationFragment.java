package com.example.andreika.conferencegorodit.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.andreika.conferencegorodit.Database.DatabaseHelper;
import com.example.andreika.conferencegorodit.R;
import com.example.andreika.conferencegorodit.Transform.ScaleImageView;


import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {


    ImageView mImageView;
    PhotoViewAttacher mAttacher;
    private SQLiteDatabase mSqLiteDatabase;
    private DatabaseHelper mDatabaseHelper;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.location_layout, container, false);

        mImageView = (ImageView) view.findViewById(R.id.imageView6);

        Drawable bitmap = getResources().getDrawable(R.drawable.map_tv3);
        mImageView.setImageDrawable(bitmap);

        mAttacher = new PhotoViewAttacher(mImageView);




        mDatabaseHelper = new DatabaseHelper(getActivity());

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        TextView textViewContact = (TextView) view.findViewById(R.id.textViewContact);
        TextView textViewLocation = (TextView) view.findViewById(R.id.textViewLocation);
        TextView textViewBus = (TextView) view.findViewById(R.id.textViewBus);
        TextView textViewTrolley = (TextView) view.findViewById(R.id.textViewTrolley);
        TextView textViewPhone = (TextView) view.findViewById(R.id.textViewMobilePhone);
        TextView textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);


        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM gorodIt", null);
        if (c.moveToFirst()) {
            textViewContact.setText(c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_CONTACT)));
            textViewLocation.setText(c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_ADDRESS)));
            textViewBus.setText("Автобус №:" + c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_BUS)));
            textViewTrolley.setText("Троллейбус №:" + c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_TROLLEY)));
            textViewPhone.setText(c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_MOBILE_PHONE)));
            textViewEmail.setText(c.getString(c.getColumnIndex(mDatabaseHelper.GOROD_IT_COLUMN_EMAIL)));
        } else {
            Toast.makeText(getActivity(), "There was an error in compiling", Toast.LENGTH_SHORT).show();
        }


        return view;
    }


}
