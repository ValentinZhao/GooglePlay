package com.test.zhaoziliang.googleplay.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.zhaoziliang.googleplay.Fragment.FragmentFactory;

/**
 * Created by zhaoziliang on 16/11/23.
 */

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {
    String[] tabNames;

    public MainFragmentPagerAdapter(FragmentManager fm, String[] tabNames) {
        super(fm);
        this.tabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
