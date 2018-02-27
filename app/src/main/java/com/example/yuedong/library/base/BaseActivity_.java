package com.example.yuedong.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.example.yuedong.library.utils.UtilManager;
import com.vondear.rxtools.SLoadingTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mayuedong on 2017/10/25.
 */

public abstract class BaseActivity_ extends SupperActivity {


    @Override
    public void initSupperData(Bundle bundle) {
        initData(bundle);
    }

    @Override
    public void onActivityDestroy() {

    }

    public abstract void initData(Bundle bundle);


}
