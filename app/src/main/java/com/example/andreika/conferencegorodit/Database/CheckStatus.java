package com.example.andreika.conferencegorodit.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by andreika on 24.07.2016.
 */
public class CheckStatus {

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;


    public boolean isStatus(Context context,int idPresentation) {

        boolean isLiked = false;

        mDatabaseHelper = new DatabaseHelper(context);

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        Cursor cursor = mSqLiteDatabase.rawQuery("SELECT liked_presentation_id FROM liked ", null);

        if (cursor.moveToFirst()) {
            do {

                if (idPresentation == cursor.getInt(cursor.getColumnIndexOrThrow("liked_presentation_id"))) {
                    isLiked = true;

                }


            } while (cursor.moveToNext());


        }


        return isLiked;
    }
}
