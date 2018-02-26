package com.example.yuedong.library.views.home.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bigkoo.pickerview.TimePickerView;
import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment;
import com.example.yuedong.library.models.UpdVersionModule;
import com.example.yuedong.library.presenter.UpdatePresenter;
import com.example.yuedong.library.presenter.contract.UpdateContract;
import com.example.yuedong.library.utils.SelectUtils;
import com.example.yuedong.library.utils.ToastUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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


    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_two;
    }

    @Override
    protected void initData() {

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
    public void updVersionSuccess(UpdVersionModule versionModule) {
        disLoading();
        $.showSuccess("最新版本：" + versionModule.getLatestVersion());
    }

    @Override
    public void updVersionFail(String errorMsg, boolean isTokenEP) {
        disLoading();
        ToastUtil.showLong(getActivity(), errorMsg);
    }

    @Override
    public void onCitySelectedResult(String msg) {
        $.showToast(msg);
    }

    @Override
    public void onTimeSelecedResult(Date date) {
        $.showToast(date.getTime() + "");
    }

    @OnClick({R.id.button, R.id.bttime, R.id.btclTest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                mPresenter.onCitySelected(getActivity());
                break;
            case R.id.bttime:
                mPresenter.onTimeSeleced(getActivity());
                break;
            case R.id.btclTest:
                showLoading();
                mPresenter.updVersion();
                break;
        }
    }
}
