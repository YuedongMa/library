package com.example.yuedong.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mayuedong on 2017/11/1.
 */

public abstract class BaseFragment_ extends Fragment {
    protected Unbinder unbinder;
    private View mView;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(initLayout(savedInstanceState), null);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          initData();
          onEvent();

    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    public abstract int initLayout(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void onEvent();

    @Override
    public void onDetach() {
        unbinder.unbind();
        super.onDetach();
    }


}
