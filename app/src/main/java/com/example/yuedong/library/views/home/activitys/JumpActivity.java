package com.example.yuedong.library.views.home.activitys;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by mayuedong on 2017/10/20.
 */

public class JumpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        Log.e("dsfefsefs","uri="+uri.getPath());
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
