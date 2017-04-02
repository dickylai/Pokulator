package com.dicky.pokulator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dicky on 2/4/2017.
 */

public class SwipeAdaptor extends FragmentStatePagerAdapter {
    public SwipeAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new RoundInputFragment();
        } else {
            fragment = new RecordsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
