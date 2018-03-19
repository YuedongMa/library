package com.example.yuedong.library.presenter.interactor.remote;

import com.example.yuedong.library.config.RemoteConfig;

/**
 * Created by mayuedong on 2017/3/13.
 */
public class BaseRemoteInteractorCallBack extends RemoteConfig {

    public BaseRemoteInteractorCallBack() {

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;

    public void callBackSuccessResult(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncRemoteManagerResult.createSuccessResult(result, getMethod()));
    }

    public void callBackErrorResult(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncRemoteManagerResult.createErrorResult(result, getMethod()));
    }

    public void callBackSuccess(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncRemoteManagerResult.createSuccess(getMethod()));
    }

    public void callBackSuccess(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncRemoteManagerResult.createSuccess(msg, getMethod()));
    }

    public void callBackError(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncRemoteManagerResult.createError(msg, getMethod()));
    }

    public void callBackError(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncRemoteManagerResult.createError(getMethod()));
    }

    public void callBackWarning(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncRemoteManagerResult.createWarning(msg, getMethod()));
    }

    public void callBackWarningResult(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncRemoteManagerResult.createWarningResult(result, getMethod()));
    }

    public void callBackWarning(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncRemoteManagerResult.createWarning(getMethod()));
    }

    @Deprecated
    public void callBack(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, AsyncRemoteManagerResult _result) {
        if (_onResult != null) {
            _onResult.onInerractorFinish(_result);
        }

    }


}
