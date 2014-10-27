package com.chuross.weathernews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import com.google.common.collect.Maps;

import java.util.Map;

public class TitleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Map<String, Fragment> fragments = Maps.newLinkedHashMap();
    private FragmentManager fragmentManager;

    public TitleFragmentPagerAdapter(final FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return fragments.keySet().toArray(new String[fragments.keySet().size()])[position];
    }

    @Override
    public Fragment getItem(final int position) {
        return fragments.values().toArray(new Fragment[fragments.values().size()])[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void put(String title, Fragment fragment) {
        fragments.put(title, fragment);
        notifyDataSetChanged();
    }

    public void clear() {
        if(fragmentManager.isDestroyed()) {
            return;
        }
        for(Fragment fragment : fragments.values()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        fragments.clear();
        notifyDataSetChanged();
    }
}
