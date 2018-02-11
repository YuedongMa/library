package com.example.yuedong.library;

import android.app.Application;
import android.content.Context;

import io.paperdb.Paper;

/**
 * Created by mayuedong on 2017/10/25.
 */

public class MainApplication extends Application {
    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Paper.init(this);
    }
}
