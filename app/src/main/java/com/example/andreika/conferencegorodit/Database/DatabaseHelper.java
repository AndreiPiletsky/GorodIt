package com.example.andreika.conferencegorodit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by andreika on 18.07.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydatabase.db";
    public static int DATABASE_VERSION = 1;


    public static final String TABLE_GOROD_IT = "gorodIt";
    public static final String GOROD_IT_COLUMN_ID = "id";
    public static final String GOROD_IT_COLUMN_ABOUT = "about";
    public static final String GOROD_IT_COLUMN_ADDRESS = "address";
    public static final String GOROD_IT_COLUMN_BUS = "bus";
    public static final String GOROD_IT_COLUMN_CONTACT = "contact";
    public static final String GOROD_IT_COLUMN_EMAIL = "email";
    public static final String GOROD_IT_COLUMN_MOBILE_PHONE = "mobile";
    public static final String GOROD_IT_COLUMN_TROLLEY = "trolley";
    private static final String DATABASE_CREATE_GOROD_IT = "create table "
            + TABLE_GOROD_IT + "(" + GOROD_IT_COLUMN_ID
            + " integer primary key autoincrement, " + GOROD_IT_COLUMN_ABOUT
            + " text, " + GOROD_IT_COLUMN_ADDRESS + " text, " + GOROD_IT_COLUMN_BUS
            + " text, " + GOROD_IT_COLUMN_CONTACT + " text, " + GOROD_IT_COLUMN_EMAIL
            + " text, " + GOROD_IT_COLUMN_MOBILE_PHONE + " text, " + GOROD_IT_COLUMN_TROLLEY
            + " text);";


    public static final String TABLE_PARTNERS = "partnersandsponsors";
    public static final String PARTNERS_COLUMN_ID = "id";
    public static final String PARTNERS_COLUMN_ABOUT = "about";
    public static final String PARTNERS_COLUMN_COMPANY = "company";
    public static final String PARTNERS_COLUMN_ISPARTNER = "ispartner";
    public static final String PARTNERS_COLUMN_LOGO = "logo";
    public static final String PARTNERS_COLUMN_SITE = "site";
    private static final String DATABASE_CREATE_PARTNERS = "create table "
            + TABLE_PARTNERS + "(" + PARTNERS_COLUMN_ID
            + " integer primary key autoincrement, " + PARTNERS_COLUMN_ABOUT
            + " text, " + PARTNERS_COLUMN_COMPANY + " text, " + PARTNERS_COLUMN_ISPARTNER
            + " integer, " + PARTNERS_COLUMN_LOGO + " text, " + PARTNERS_COLUMN_SITE
            + " text);";


    public static final String TABLE_SPEAKERS = "speakers";
    public static final String SPEAKERS_COLUMN_ID = "id";
    public static final String SPEAKERS_COLUMN_ABOUT = "about";
    public static final String SPEAKERS_COLUMN_COMPANY = "company";
    public static final String SPEAKERS_COLUMN_NAME = "name";
    public static final String SPEAKERS_COLUMN_PHOTO = "photo";
    public static final String SPEAKERS_COLUMN_POSITION = "position";
    private static final String DATABASE_CREATE_SPEAKERS = "create table "
            + TABLE_SPEAKERS + "(" + SPEAKERS_COLUMN_ID
            + " integer, " + SPEAKERS_COLUMN_ABOUT
            + " text, " + SPEAKERS_COLUMN_COMPANY + " text, " + SPEAKERS_COLUMN_NAME
            + " text, " + SPEAKERS_COLUMN_PHOTO + " text, " + SPEAKERS_COLUMN_POSITION
            + " text);";

    public static final String SCHEDULE_COLUMN_ID = "schedule_id";
    public static final String SCHEDULE_COLUMN_HALL_ID = "hall_id";
    public static final String SCHEDULE_COLUMN_HALL = "hall_name";
    public static final String SCHEDULE_COLUMN_PRESENTATION_ABOUT = "presentation_about";
    public static final String SCHEDULE_COLUMN_PRESENTATION = "presentation_title";
    public static final String SCHEDULE_COLUMN_PRESENTATION_ID = "presentation_id";
    public static final String SCHEDULE_COLUMN_SECTION_ID = "section_id";
    public static final String SCHEDULE_COLUMN_SECTION = "section_name";
    public static final String SCHEDULE_COLUMN_SPEAKER_ID = "speaker_id";
    public static final String SCHEDULE_COLUMN_TIMEEND = "timeend";
    public static final String SCHEDULE_COLUMN_TIMESTART = "timestart";
    public static final String SCHEDULE_COLUMN_URL = "url";
    public static final String TABLE_SCHEDULE = "schedule";

    private static final String DATABASE_CREATE_SCHEDULE = "create table "
            + TABLE_SCHEDULE + "(" + SCHEDULE_COLUMN_ID
            + " integer primary key autoincrement, " + SCHEDULE_COLUMN_HALL_ID
            + " integer, " + SCHEDULE_COLUMN_HALL + " text, " + SCHEDULE_COLUMN_PRESENTATION_ABOUT
            + " text, " + SCHEDULE_COLUMN_PRESENTATION_ID + " integer, " + SCHEDULE_COLUMN_PRESENTATION + " text, " + SCHEDULE_COLUMN_SECTION_ID
            + " integer, " + SCHEDULE_COLUMN_SECTION + " text, " + SCHEDULE_COLUMN_SPEAKER_ID
            + " integer, " + SCHEDULE_COLUMN_TIMEEND + " long, " + SCHEDULE_COLUMN_TIMESTART
            + " long, " + SCHEDULE_COLUMN_URL
            + " text);";

    public static final String TABLE_OPTIONS = "options";
    public static final String OPTIONS_COLUMN_ID = "id";
    public static final String OPTIONS_COLUMN_MAP = "map";
    public static final String OPTIONS_COLUMN_VERSION = "version";

    private static final String DATABASE_CREATE_OPTIONS = "create table "
            + TABLE_OPTIONS + "(" + OPTIONS_COLUMN_ID
            + " integer primary key autoincrement, " + OPTIONS_COLUMN_MAP
            + " integer, " + OPTIONS_COLUMN_VERSION
            + " integer);";

    public static final String TABLE_LIKED = "liked";
    public static final String LIKED_COLUMN_ID = "liked_id";
    public static final String LIKED_COLUMN_PRESENTATION_ID = "liked_presentation_id";

    private static final String DATABASE_CREATE_LIKED = "create table "
            + TABLE_LIKED + "(" + LIKED_COLUMN_ID
            + " integer primary key autoincrement, " + LIKED_COLUMN_PRESENTATION_ID
            + " integer);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL(DATABASE_CREATE_GOROD_IT);
        sqLiteDatabase.execSQL(DATABASE_CREATE_PARTNERS);
        sqLiteDatabase.execSQL(DATABASE_CREATE_SPEAKERS);
        sqLiteDatabase.execSQL(DATABASE_CREATE_SCHEDULE);
        sqLiteDatabase.execSQL(DATABASE_CREATE_OPTIONS);
        sqLiteDatabase.execSQL(DATABASE_CREATE_LIKED);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion == 1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GOROD_IT);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTNERS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKERS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTIONS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKED);
        }


        onCreate(sqLiteDatabase);
    }


}