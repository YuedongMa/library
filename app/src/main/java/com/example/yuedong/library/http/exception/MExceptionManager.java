package com.example.yuedong.library.http.exception;

import com.example.yuedong.library.MainApplication;
import com.example.yuedong.library.utils.NetworkUtil;
import com.google.gson.JsonParseException;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * 异常信息管理类
 * <p>
 *
 *
 */

public class MExceptionManager {

    public static MException handleException(Throwable throwable) {

        MException jdException = null;

        boolean isUnknown;

        if (throwable instanceof HttpException) {
            isUnknown = false;
            HttpException httpException = (HttpException) throwable;
            jdException = new MException(ERROR_INFO.HTTP_ERROR);
            if (NetworkUtil.isNetworkConnected(MainApplication.getContext())) {
                jdException.setErrorMsg("服务器连接异常");
            } else {
                jdException.setErrorMsg("请检查您的网络配置");
            }
        } else if (throwable instanceof ApiException) {
            isUnknown = false;
            jdException = new MException(ERROR_INFO.ERROR);
            if (500 == ((ApiException) throwable).getCode()) {
                jdException.setErrorMsg("服务器异常");
            } else {
                jdException.setErrorMsg(((ApiException) throwable).getMsg());
            }
        } else if (throwable instanceof JSONException
                || throwable instanceof JsonParseException
                || throwable instanceof ParseException) {
            isUnknown = false;
            jdException = new MException(ERROR_INFO.PARSE_ERROR);
            jdException.setErrorMsg("数据处理异常");
        } else if (throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException) {
            isUnknown = false;
            jdException = new MException(ERROR_INFO.HTTP_ERROR);
            if (NetworkUtil.isNetworkConnected(MainApplication.getContext())) {
                jdException.setErrorMsg("连接超时");
            } else {
                jdException.setErrorMsg("请检查您的网络配置");
            }
        } else {
            isUnknown = true;
            jdException = new MException(ERROR_INFO.UNKNOWN);
            jdException.setErrorMsg("未知错误");
        }
        if (!isUnknown){
            RxToast.normal(MainApplication.getContext(), jdException.getErrorMsg()).show();
        }
        return jdException;
    }

}
