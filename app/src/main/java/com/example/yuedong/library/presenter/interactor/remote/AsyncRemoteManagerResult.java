package com.example.yuedong.library.presenter.interactor.remote;

import com.example.yuedong.library.config.LocationConfig;
import com.example.yuedong.library.config.RemoteConfig;

/**
 * Created by mayuedong on 2017/3/13.
 */
public class AsyncRemoteManagerResult extends RemoteConfig{
    public static final int SERVICE_RESULT_WARNING = -1;
    public static final int SERVICE_RESULT_ERROR = 0;
    public static final int SERVICE_RESULT_SUCCESS = 1;
    private static AsyncRemoteManagerResult r;
    static final String WARNING_MESSAGE = "警告";
    static final String ERROR_MESSAGE = "网络错误，请稍后再试";
    static final String SUCCESS_MESSAGE = "请求成功";

  private   Object result;
  private   String message;
 private    int code;
  private String method;
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public <T> T getResult(Class<T> _class) {
        return (T) result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return code == SERVICE_RESULT_SUCCESS;
    }

    public boolean isError() {
        return code == SERVICE_RESULT_ERROR;
    }

    public boolean isWarning() {
        return code == SERVICE_RESULT_WARNING;
    }

    public static AsyncRemoteManagerResult create(Object _result, String _message, int _code, String _method) {
        if (r == null) {
            synchronized (AsyncRemoteManagerResult.class) {
                if (r == null) {
                    r = new AsyncRemoteManagerResult();
                }
            }
        }
        r.setResult(_result);
        r.setMessage(_message);
        r.setCode(_code);
        r.setMethod(_method);
        return r;
    }

    public static AsyncRemoteManagerResult createErrorResult(Object _result, String _method) {
        return AsyncRemoteManagerResult.create(_result, ERROR_MESSAGE, SERVICE_RESULT_ERROR,_method);
    }

    public static AsyncRemoteManagerResult createError(Object _result, String _message, String _method) {
        return AsyncRemoteManagerResult.create(_result, _message, SERVICE_RESULT_ERROR,_method);
    }

    public static AsyncRemoteManagerResult createError(String _message, String _method) {
        return AsyncRemoteManagerResult.create(ERROR_MESSAGE, _message, SERVICE_RESULT_ERROR,_method);
    }

    public static AsyncRemoteManagerResult createError(String _method) {
        return AsyncRemoteManagerResult.create(ERROR_MESSAGE, ERROR_MESSAGE, SERVICE_RESULT_ERROR,_method);
    }

    public static AsyncRemoteManagerResult createSuccessResult(Object _result, String _method) {
        return AsyncRemoteManagerResult.create(_result, SUCCESS_MESSAGE, SERVICE_RESULT_SUCCESS,_method);
    }

    public static AsyncRemoteManagerResult createSuccess(Object _result, String _message, String _method) {
        return AsyncRemoteManagerResult.create(_result, _message, SERVICE_RESULT_SUCCESS,_method);
    }

    public static AsyncRemoteManagerResult createSuccess(String _message, String _method) {
        return AsyncRemoteManagerResult.create(SUCCESS_MESSAGE, _message, SERVICE_RESULT_SUCCESS,_method);
    }

    public static AsyncRemoteManagerResult createSuccess(String _method) {
        return AsyncRemoteManagerResult.create(SUCCESS_MESSAGE, SUCCESS_MESSAGE, SERVICE_RESULT_SUCCESS,_method);
    }

    public static AsyncRemoteManagerResult createWarningResult(Object _result, String _method) {
        return AsyncRemoteManagerResult.create(_result, WARNING_MESSAGE, SERVICE_RESULT_WARNING,_method);
    }

    public static AsyncRemoteManagerResult createWarning(Object _result, String _message, String _method) {
        return AsyncRemoteManagerResult.create(_result, _message, SERVICE_RESULT_WARNING,_method);
    }

    public static AsyncRemoteManagerResult createWarning(String _message, String _method) {
        return AsyncRemoteManagerResult.create(WARNING_MESSAGE, _message, SERVICE_RESULT_WARNING,_method);
    }

    public static AsyncRemoteManagerResult createWarning(String _method) {
        return AsyncRemoteManagerResult.create(WARNING_MESSAGE, ERROR_MESSAGE, SERVICE_RESULT_WARNING,_method);
    }
}
