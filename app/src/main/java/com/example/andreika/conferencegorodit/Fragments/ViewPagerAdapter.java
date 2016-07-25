package com.example.andreika.conferencegorodit.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by andreika on 07.07.2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();

    public void AddFragments(Fragment fragments, String tabTitles) {
        this.fragments.add(fragments);
        this.tabTitles.add(tabTitles);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
