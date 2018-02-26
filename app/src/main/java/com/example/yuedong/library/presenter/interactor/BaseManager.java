package com.example.yuedong.library.presenter.interactor;

import android.util.Log;

import com.example.yuedong.library.presenter.interactor.location.IPresenterLocationInteractor;
import com.example.yuedong.library.presenter.interactor.remote.IPresenterRemoteInteractor;

/**
 * Created by mayuedong on 2017/3/13.
 */
public class BaseManager<T> {

    public BaseManager( ) {

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;

    public void callBackSuccessResult(T _onResult, Object result) {
        callBack(_onResult, AsyncManagerResult.createSuccessResult(result,getMethod()));
    }

    public void callBackErrorResult(T _onResult, Object result) {
        callBack(_onResult, AsyncManagerResult.createErrorResult(result,getMethod()));
    }

    public void callBackSuccess(T _onResult) {
        callBack(_onResult, AsyncManagerResult.createSuccess(getMethod()));
    }

    public void callBackSuccess(T _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createSuccess(msg,getMethod()));
    }

    public void callBackError(T _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createError(msg,getMethod()));
    }

    public void callBackError(T _onResult) {
        callBack(_onResult, AsyncManagerResult.createError(getMethod()));
    }

    public void callBackWarning(T _onResult, String msg) {
        callBack(_onResult, AsyncManagerResult.createWarning(msg,getMethod()));
    }

    public void callBackWarningResult(T _onResult, Object result) {
        callBack(_onResult, AsyncManagerResult.createWarningResult(result,getMethod()));
    }

    public void callBackWarning(T _onResult) {
        callBack(_onResult, AsyncManagerResult.createWarning(getMethod()));
    }

    @Deprecated
    public void callBack(T _onResult, AsyncManagerResult _result) {
        if (_onResult != null){

//            if(_onResult instanceof IPresenterRemoteInteractor.onEventFinishListener){
//                ( (IPresenterRemoteInteractor.onEventFinishListener) _onResult).onInerractorFinish(_result);
//            }else if(_onResult instanceof IPresenterLocationInteractor.onLocaionEventFinishListener){
//                ( (IPresenterLocationInteractor.onLocaionEventFinishListener) _onResult).onLocationEventFinish(_result);
//            }
        }
            
    }


}
