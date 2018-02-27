package com.example.yuedong.library.views.home;

import android.app.Activity;
import android.os.Bundle;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseActivity_;
import com.vondear.rxtools.SBarTool;

public class DrawerActivity extends BaseActivity_ {


    @Override
    public int initLayout(Bundle savedInstanceState) {
        SBarTool.setNoTitle(this);
        return R.layout.activity_drawer;
    }

    @Override
    public void initData(Bundle bundle) {

    }
}
