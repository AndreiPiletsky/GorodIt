package com.example.andreika.conferencegorodit.Database;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.Toast;


import com.example.andreika.conferencegorodit.Fragments.AboutConferenceFragment;
import com.example.andreika.conferencegorodit.POJO.Gorod_it;
import com.example.andreika.conferencegorodit.POJO.Options;
import com.example.andreika.conferencegorodit.POJO.PartnerAndSponsor;
import com.example.andreika.conferencegorodit.POJO.Schedule.Schedule;
import com.example.andreika.conferencegorodit.POJO.Speaker;
import com.example.andreika.conferencegorodit.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by andreika on 21.07.2016.
 */
public class DataFromFirebase {
    private SQLiteDatabase mSqLiteDatabase;
    private DatabaseHelper mDatabaseHelper;
    Context context;
    private ProgressDialog nDialog;

    public DataFromFirebase(Context context) {
        this.context = context;
        readAndWriteData();

    }


    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    void fragmentToAbout() {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new AboutConferenceFragment());
        fragmentTransaction.commit();
    }

    void readAndWriteData() {

        SpannableString spannableString = new SpannableString("Загрузка данных...");
        spannableString.setSpan(new RelativeSizeSpan(1.5f), 0, spannableString.length(), 0);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.primaryColor)), 0, spannableString.length(), 0);

        nDialog = new ProgressDialog(context);
        nDialog.setMessage(spannableString);
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();

        com.firebase.client.Firebase.setAndroidContext(context);
        final com.firebase.client.Firebase ref = new com.firebase.client.Firebase("https://proekt-e870e.firebaseio.com/");

        if (isOnline()) {

            ref.child("options").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Options options = dataSnapshot.getValue(Options.class);

                    if (options.getVersion() > new CheckVersion(context).currentVersion()) {


                        mDatabaseHelper = new DatabaseHelper(context);

                        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                        mDatabaseHelper.onUpgrade(mSqLiteDatabase, 1, 1);

                        ContentValues values = new ContentValues();
                        values.put(mDatabaseHelper.OPTIONS_COLUMN_MAP, options.getMap());
                        values.put(mDatabaseHelper.OPTIONS_COLUMN_VERSION, options.getVersion());
                        mSqLiteDatabase.insert(mDatabaseHelper.TABLE_OPTIONS, null, values);

                        ref.child("gorod_it").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Gorod_it gorod_it = dataSnapshot.getValue(Gorod_it.class);


                                ContentValues values = new ContentValues();

                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_ABOUT, gorod_it.getAbout());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_ADDRESS, gorod_it.getAddress());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_BUS, gorod_it.getBus());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_CONTACT, gorod_it.getContact());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_EMAIL, gorod_it.getEmail());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_MOBILE_PHONE, gorod_it.getMobilePhone());
                                values.put(mDatabaseHelper.GOROD_IT_COLUMN_TROLLEY, gorod_it.getTrolley());


                                mSqLiteDatabase.insert(mDatabaseHelper.TABLE_GOROD_IT, null, values);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }


                        });

                        ref.child("partnersandsponsors").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    PartnerAndSponsor partnerAndSponsor = postSnapshot.getValue(PartnerAndSponsor.class);

                                    ContentValues values = new ContentValues();

                                    values.put(mDatabaseHelper.PARTNERS_COLUMN_ABOUT, partnerAndSponsor.getAbout());
                                    values.put(mDatabaseHelper.PARTNERS_COLUMN_COMPANY, partnerAndSponsor.getCompany());
                                    values.put(mDatabaseHelper.PARTNERS_COLUMN_ISPARTNER, partnerAndSponsor.getIsPartner());
                                    values.put(mDatabaseHelper.PARTNERS_COLUMN_LOGO, partnerAndSponsor.getLogo());
                                    values.put(mDatabaseHelper.PARTNERS_COLUMN_SITE, partnerAndSponsor.getSite());


                                    mSqLiteDatabase.insert(mDatabaseHelper.TABLE_PARTNERS, null, values);
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }


                        });

                        ref.child("schedule").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    Schedule schedule = postSnapshot.getValue(Schedule.class);

                                    ContentValues values = new ContentValues();

                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_HALL_ID, schedule.getHall().getId());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_HALL, schedule.getHall().getName());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_PRESENTATION_ABOUT, schedule.getPresentation().getAbout());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_PRESENTATION_ID, schedule.getPresentation().getId());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_PRESENTATION, schedule.getPresentation().getTitle());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_SECTION_ID, schedule.getSection().getId());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_SECTION, schedule.getSection().getName());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_SPEAKER_ID, schedule.getSpeakers().get(0));
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_TIMEEND, schedule.getTimeEnd());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_TIMESTART, schedule.getTimeStart());
                                    values.put(mDatabaseHelper.SCHEDULE_COLUMN_URL, schedule.getUrl());


                                    mSqLiteDatabase.insert(mDatabaseHelper.TABLE_SCHEDULE, null, values);

                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }


                        });

                        ref.child("speakers").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    Speaker speaker = postSnapshot.getValue(Speaker.class);
                                    ContentValues values = new ContentValues();
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_ID, speaker.getId());
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_ABOUT, speaker.getAbout());
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_COMPANY, speaker.getCompany());
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_NAME, speaker.getName());
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_PHOTO, speaker.getPhoto());
                                    values.put(mDatabaseHelper.SPEAKERS_COLUMN_POSITION, speaker.getPosition());

                                    mSqLiteDatabase.insert(mDatabaseHelper.TABLE_SPEAKERS, null, values);


                                }

                                fragmentToAbout();
                                nDialog.dismiss();

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }


                        });

                    } else {
                        fragmentToAbout();
                        nDialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }

            });


        } else {
            fragmentToAbout();
            nDialog.dismiss();
        }


    }

}


