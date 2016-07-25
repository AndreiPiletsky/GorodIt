package com.example.andreika.conferencegorodit.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andreika.conferencegorodit.R;


/**
 * Created by andreika on 07.07.2016.
 */
public class ProgramFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x =  inflater.inflate(R.layout.program_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));



        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new FirstHallFragment();
                case 1 : return new SecondHallFragment();
                case 2 : return new ThirdHallFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }



        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return getActivity().getResources().getString(R.string.hall_1);
                case 1 :
                    return getActivity().getResources().getString(R.string.hall_2);
                case 2 :
                    return getActivity().getResources().getString(R.string.hall_3);
            }
            return null;
        }
    }

}