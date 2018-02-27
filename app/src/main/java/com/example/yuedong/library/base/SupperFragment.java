package com.example.yuedong.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.utils.UtilManager;
import com.vondear.rxtools.SLoadingTool;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by YuedongMa on 2018/2/27.
 */

public abstract class SupperFragment extends Fragment implements BaseView {
    protected Unbinder unbinder;
    private View mView;
    protected UtilManager $;
    private SLoadingTool loadingTool;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(initLayout(savedInstanceState), null);
        unbinder = ButterKnife.bind(this, mView);
        if ($ == null) $ = UtilManager.getInstance(getActivity());
        if (loadingTool == null) loadingTool = new SLoadingTool(getActivity());
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSupperData(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        unbinder.unbind();
        if (loadingTool != null) loadingTool = null;
        onDetachActivity();
        super.onDetach();
    }

    @Override
    public void showLoading() {
        loadingTool.show();
    }

    @Override
    public void disLoading() {
        loadingTool.close();
    }

    protected abstract void initSupperData(Bundle savedInstanceState);

    protected abstract int initLayout(Bundle savedInstanceState);

    protected abstract void onDetachActivity();
}
