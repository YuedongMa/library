package com.example.yuedong.library.presenter.interactor.location;

import com.example.yuedong.library.config.LocationConfig;

/**
 * Created by mayuedong on 2017/3/13.
 */
public class BaseLocationInteractorCallBack extends LocationConfig {

    public BaseLocationInteractorCallBack() {

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;
    private int type = -1;

    public void callBackSuccessResult(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, Object result, int type) {
        callBack(_onResult, AsyncLocationManagerResult.createSuccessResult(result, getMethod()), type);
    }

    public void callBackErrorResult(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncLocationManagerResult.createErrorResult(result, getMethod()), type);
    }

    public void callBackSuccess(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult) {
        callBack(_onResult, AsyncLocationManagerResult.createSuccess(getMethod()), type);
    }

    public void callBackSuccess(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncLocationManagerResult.createSuccess(msg, getMethod()), type);
    }

    public void callBackError(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncLocationManagerResult.createError(msg, getMethod()), type);
    }

    public void callBackError(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult) {
        callBack(_onResult, AsyncLocationManagerResult.createError(getMethod()), type);
    }

    public void callBackWarning(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncLocationManagerResult.createWarning(msg, getMethod()), type);
    }

    public void callBackWarningResult(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncLocationManagerResult.createWarningResult(result, getMethod()), type);
    }

    public void callBackWarning(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult) {
        callBack(_onResult, AsyncLocationManagerResult.createWarning(getMethod()), type);
    }

    @Deprecated
    public void callBack(IPresenterLocationInteractor.onLocaionEventFinishListener _onResult, AsyncLocationManagerResult _result, int type) {
        if (_onResult != null) {
            _onResult.onLocationEventFinish(_result, type);
        }

    }


}
