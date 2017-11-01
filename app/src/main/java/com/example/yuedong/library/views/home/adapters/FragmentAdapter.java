package com.example.yuedong.library.views.home.adapters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mayuedong on 2017/10/20.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs;
    private Fragment[] fragments;

    /**
     *
     * @param fm   fragment事务管理
     * @param tabsTitle 显示标题
     * @param fragments fragment数组
     * @param tab tablayout对象
     */
    public FragmentAdapter(FragmentManager fm, String[] tabsTitle, Fragment[] fragments, TabLayout tab) {
        super(fm);
        this.tabs=tabsTitle;
        this.fragments=fragments;
        for (int i = 0; i < tabs.length; i++) {
            tab.addTab(tab.newTab().setText(tabs[i]));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position] ;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
