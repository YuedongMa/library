package com.example.yuedong.library.views.home.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.example.yuedong.library.views.home.adapters.FragmentAdapter;
import com.vondear.rxtools.SLogTool;

import butterknife.BindView;
public class OneFragment extends BaseFragment_ {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
     ViewPager viewPager;
    private String[] tabs = {"全部", "消息"};
    private Fragment[] fragments = {ChildOneFragment.newInstance(), ChildTwoFragment.newInstance()};

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        
    }

    @Override
    protected int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_one;
    }

    @Override
    protected void initData(Bundle bundle) {
        
        viewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), tabs, fragments,tab));
         tab.setupWithViewPager(viewPager);
    }



}
