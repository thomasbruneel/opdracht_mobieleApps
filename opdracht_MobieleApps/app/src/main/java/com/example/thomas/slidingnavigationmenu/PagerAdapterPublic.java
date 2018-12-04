package com.example.thomas.slidingnavigationmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterPublic extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterPublic(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentInfo tab1 = new TabFragmentInfo();
                return tab1;
            case 1:
                TabFragmentBiedingenPublic tab2 = new TabFragmentBiedingenPublic();
                return tab2;

            case 2:
                TabFragmentProfiel tab3 = new TabFragmentProfiel();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}