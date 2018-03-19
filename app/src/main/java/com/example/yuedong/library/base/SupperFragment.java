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
import com.vondear.rxtools.SLogTool;

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
    private SupperActivity supperActivity;
    private boolean isFristVisble = true;
    private boolean hasInit = false;

    public SupperActivity getSupperActivity() {
        return supperActivity;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//适用于view pager与fragment结合的情况
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isFristVisble && hasInit) {
            isFristVisble = false;
            onLayLoad();
            onUserVisble();
        } else if (isVisibleToUser && hasInit) {
            onUserVisble();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {//适用于fragment的show()和hide()方法
        super.onHiddenChanged(hidden);
        if (!hidden && isFristVisble && hasInit) {
            isFristVisble = false;
            onLayLoad();
            onUserVisble();
        } else if (!hidden && hasInit) {
            onUserVisble();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(initLayout(savedInstanceState), null);
        unbinder = ButterKnife.bind(this, mView);
        if ($ == null) $ = UtilManager.getInstance(getActivity().getApplicationContext());
        if (loadingTool == null) loadingTool = new SLoadingTool(getActivity());
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSupperData(savedInstanceState);
        hasInit = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SupperActivity) {
            this.supperActivity = (SupperActivity) context;
        }
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

    protected abstract void onLayLoad();//仅第一次可见时加载

    protected abstract void onUserVisble();//用户每次可见时加载

    protected abstract void onDetachActivity();
}
