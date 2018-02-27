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

import com.example.yuedong.library.base.basepresenter.BasePresenter;
import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.vondear.rxtools.SLoadingTool;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mayuedong on 2017/10/25.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupperActivity {
    protected T mPresenter;

    @Override
    public void initSupperData(Bundle bundle) {
        unbinder = ButterKnife.bind(this);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        } else {
            throw new IllegalStateException("Please call mPresenter in AppCompatActivity(initPresenter) to initialize!");
        }
        initData();
    }

    protected abstract void initPresenter();

    public abstract void initData();

    protected abstract void clearDisposable();

    @Override
    public void onActivityDestroy() {
        if (unbinder != null) unbinder.unbind();
        mPresenter.detachView();
        clearDisposable();
    }
}
