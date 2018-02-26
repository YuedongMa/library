package com.example.yuedong.library.presenter.interactor.remote;

import com.example.yuedong.library.presenter.interactor.AsyncManagerResult;

/**
 * Created by mayuedong on 2017/3/13.
 */
public class BaseRemoteInteractorCallBack {

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
        callBack(_onResult, AsyncManagerResult.createSuccessResult(result, getMethod()));
    }

    public void callBackErrorResult(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncManagerResult.createErrorResult(result, getMethod()));
    }

    public void callBackSuccess(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncManagerResult.createSuccess(getMethod()));
    }

    public void callBackSuccess(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createSuccess(msg, getMethod()));
    }

    public void callBackError(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createError(msg, getMethod()));
    }

    public void callBackError(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncManagerResult.createError(getMethod()));
    }

    public void callBackWarning(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createWarning(msg, getMethod()));
    }

    public void callBackWarningResult(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, Object result) {
        callBack(_onResult, AsyncManagerResult.createWarningResult(result, getMethod()));
    }

    public void callBackWarning(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult) {
        callBack(_onResult, AsyncManagerResult.createWarning(getMethod()));
    }

    @Deprecated
    public void callBack(IPresenterRemoteInteractor.onRemoteEventFinishListener _onResult, AsyncManagerResult _result) {
        if (_onResult != null) {
            _onResult.onInerractorFinish(_result);
        }

    }


}
