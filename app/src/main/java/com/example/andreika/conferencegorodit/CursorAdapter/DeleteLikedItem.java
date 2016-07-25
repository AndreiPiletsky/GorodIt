package com.example.andreika.conferencegorodit.CursorAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.andreika.conferencegorodit.Database.DatabaseHelper;

/**
 * Created by andreika on 23.07.2016.
 */
public class DeleteLikedItem {

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mSqLiteDatabase;


    public void delete(Context context, int id) {
        mDatabaseHelper = new DatabaseHelper(context);

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        mSqLiteDatabase.delete(mDatabaseHelper.TABLE_LIKED,
                "liked_id = ?",
                new String[]{String.valueOf(id)});


    }

}

