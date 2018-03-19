package com.example.yuedong.library.views.home.adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mayuedong on 2017/10/20.
 */

public class SlideFragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;

    /**
     *
     * @param fm   fragment事务管理
     * @param fragments fragment数组
     */
    public SlideFragmentAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position] ;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

}
