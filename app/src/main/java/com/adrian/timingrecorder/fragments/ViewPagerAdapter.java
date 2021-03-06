package com.adrian.timingrecorder.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ranqing on 2017/5/3.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments != null ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null ? titles.get(position % titles.size()) : "";
    }
}
