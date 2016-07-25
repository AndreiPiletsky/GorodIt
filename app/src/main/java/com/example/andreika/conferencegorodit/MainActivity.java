package com.example.andreika.conferencegorodit;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;



import com.crashlytics.android.Crashlytics;
import com.example.andreika.conferencegorodit.Database.DataFromFirebase;

import com.example.andreika.conferencegorodit.Fragments.AboutConferenceFragment;
import com.example.andreika.conferencegorodit.Fragments.AboutDeveloperFragment;
import com.example.andreika.conferencegorodit.Fragments.LikedFragment;
import com.example.andreika.conferencegorodit.Fragments.LocationFragment;
import com.example.andreika.conferencegorodit.Fragments.ProgramFragment;
import com.example.andreika.conferencegorodit.Fragments.SpeakerFragment;
import com.example.andreika.conferencegorodit.Fragments.SponsorFragment;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView mDrawer;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());


        setContentView(R.layout.activity_main);

        DataFromFirebase dataFromFirebase = new DataFromFirebase(MainActivity.this);

        setToolbar();
        initView();

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


        getSupportActionBar().setTitle(R.string.aboutConference);


    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void itemSelection(int mSelectedId) {


        switch (mSelectedId) {

            case R.id.liked:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new LikedFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.liked);
                break;

            case R.id.programm:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new ProgramFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.programm);
                break;

            case R.id.location:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new LocationFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.location);
                break;

            case R.id.speekers:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new SpeakerFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.speakers);
                break;

            case R.id.partners:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new SponsorFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.partners);
                break;
            case R.id.aboutConference:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new AboutConferenceFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.aboutConference);
                break;


            case R.id.aboutDeveloper:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new AboutDeveloperFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.aboutDeveloper);
                break;

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mSelectedId = item.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("SELECTED_ID", mSelectedId);
    }
}
