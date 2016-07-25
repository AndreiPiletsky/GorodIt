package com.example.andreika.conferencegorodit.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by andreika on 23.07.2016.
 */
public class CheckVersion {

    Context context;
    private SQLiteDatabase mSqLiteDatabase;
    private DatabaseHelper mDatabaseHelper;

    public CheckVersion(Context context) {
        this.context = context;
    }

    public int currentVersion() {
        mDatabaseHelper = new DatabaseHelper(context);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        int version = 0;

        Cursor cursor = mSqLiteDatabase.rawQuery("SELECT id as _id, version FROM options", null);

        if (cursor.moveToFirst()) {
            do {

                version = cursor.getInt(cursor.getColumnIndexOrThrow("version"));


            } while (cursor.moveToNext());


        }

        return version;
    }
}
