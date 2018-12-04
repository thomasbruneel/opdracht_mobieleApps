package com.example.thomas.slidingnavigationmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterOwner extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterOwner(FragmentManager fm, int NumOfTabs) {
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
                TabFragmentBiedingenOwner tab2 = new TabFragmentBiedingenOwner();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}