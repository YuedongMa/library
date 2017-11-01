package com.example.yuedong.library.views.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildTwoFragment extends BaseFragment_ {

    public static ChildTwoFragment newInstance() {
        Bundle args = new Bundle();
        ChildTwoFragment fragment = new ChildTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_child_two;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onEvent() {

    }

}
