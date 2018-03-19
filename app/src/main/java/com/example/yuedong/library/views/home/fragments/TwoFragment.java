package com.example.yuedong.library.views.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment;
import com.example.yuedong.library.presenter.UpdatePresenter;
import com.example.yuedong.library.presenter.contract.UpdateContract;
import com.example.yuedong.library.views.home.DAndPActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment<UpdatePresenter> implements UpdateContract.View {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.bttime)
    Button bttime;
    @BindView(R.id.btclTest)
    Button btclTest;

    public TwoFragment() {

    }


    @Override
    protected int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_two;
    }


    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new UpdatePresenter();
    }

    @Override
    protected void clearDisposable() {
        mPresenter.clearDisposable();
    }

    @Override
    public void getVersionSuccess() {
        disLoading();
        mPresenter.checkAndUpdateApk(getActivity());
    }

    @Override
    public void getVersionFail(String errorMsg, boolean isTokenEP) {
        disLoading();
        $.showToast(errorMsg);
    }

    @Override
    public void onCitySelectedResult(String msg) {
        $.showToast(msg);
    }

    @Override
    public void onTimeSelecedResult(Date date) {
        $.showToast(date.getTime() + "");
    }

    @Override
    public void showCommentDialogFinish() {
        $.showToast("dialog Finish");
    }

    @OnClick({R.id.button, R.id.bttime, R.id.btclTest, R.id.btUpdate, R.id.btDialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                mPresenter.onCitySelected(getActivity());
                break;
            case R.id.bttime:
                mPresenter.onTimeSeleced(getActivity());
                break;
            case R.id.btclTest:
                startActivity(new Intent(getActivity(), DAndPActivity.class));
                break;
            case R.id.btUpdate:
                showLoading();
                mPresenter.updVersion();
                break;
            case R.id.btDialog:
             //  $.showCommentDilog(getActivity(),"neirng","tishi");
                mPresenter.showCommentDialog(getActivity());
                break;
        }
    }
}
