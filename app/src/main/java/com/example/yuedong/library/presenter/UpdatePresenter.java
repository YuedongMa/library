package com.example.yuedong.library.presenter;

import android.content.Context;

import com.example.yuedong.library.base.basepresenter.JDPresenter;
import com.example.yuedong.library.models.UpdVersionModule;
import com.example.yuedong.library.presenter.contract.UpdateContract;
import com.example.yuedong.library.presenter.interactor.AsyncManagerResult;
import com.example.yuedong.library.presenter.interactor.location.IPresenterLocationInteractor;
import com.example.yuedong.library.presenter.interactor.location.PresenterLocationInteractorImpl;
import com.example.yuedong.library.presenter.interactor.remote.IPresenterRemoteInteractor;
import com.example.yuedong.library.presenter.interactor.remote.PresenterRemoteInteractorImpl;
import com.example.yuedong.library.utils.RxUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by YuedongMa on 2018/2/22.
 */

public class UpdatePresenter extends JDPresenter<UpdateContract.View> implements UpdateContract.Presenter, IPresenterRemoteInteractor.onRemoteEventFinishListener, IPresenterLocationInteractor.onLocaionEventFinishListener {
    private IPresenterRemoteInteractor remoteInteractor;
    private IPresenterLocationInteractor locationInteractor;
    private List<Disposable> mDisposables;

    public UpdatePresenter() {
        remoteInteractor = new PresenterRemoteInteractorImpl();
        locationInteractor = new PresenterLocationInteractorImpl();
    }

    @Override
    public void updVersion() {
        remoteInteractor.updVersion(this);
    }

    @Override
    public void getDisposable(Disposable d) {
        if (mDisposables == null) {
            mDisposables = new ArrayList<>();
        }
        mDisposables.add(d);
    }

    @Override
    public void clearDisposable() {
        RxUtils.clearDisposables(mDisposables);
    }

    @Override
    public void onCitySelected(Context context) {
        locationInteractor.onCitySelected(context, this);
    }

    @Override
    public void onTimeSeleced(Context context) {
        locationInteractor.onTimeSelected(context, this);
    }

    /**
     * 远程数据统一分发
     *
     * @param result
     */
    @Override
    public void onInerractorFinish(AsyncManagerResult result) {
        if (result.isSuccess()) {
            if (result.getMethod().equals("checkClientUpdate")) {
                mView.updVersionSuccess(result.getResult(UpdVersionModule.class));
            }
        } else {
            mView.updVersionFail(result.getMessage(), false);
        }
    }

    /**
     * 本地事件统一分发
     *
     * @param result
     */
    @Override
    public void onLocationEventFinish(AsyncManagerResult result, int eventType) {
        if (eventType == result.KEY_ONCITYSELECTED) {
            mView.onCitySelectedResult(result.getResult(String.class));
        } else if (eventType == result.KEY_ONTIMESELECTED) {
            mView.onTimeSelecedResult(result.getResult(Date.class));
        }

        //  mView.onCitySelectedResult(result.getMessage());
    }
}
