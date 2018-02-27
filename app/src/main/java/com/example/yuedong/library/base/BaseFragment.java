package com.example.yuedong.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuedong.library.base.basepresenter.BasePresenter;
import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.utils.UtilManager;
import com.vondear.rxtools.SLoadingTool;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mayuedong on 2017/11/1.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupperFragment {
    protected T mPresenter;


    @Override
    protected void initSupperData(Bundle savedInstanceState) {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle bundle);

    protected abstract void initPresenter();

    protected abstract void clearDisposable();

    @Override
    protected void onDetachActivity() {
        clearDisposable();
    }
}
