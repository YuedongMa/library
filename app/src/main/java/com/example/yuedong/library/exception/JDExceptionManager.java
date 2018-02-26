package com.example.yuedong.library.exception;

import com.example.yuedong.library.MainApplication;
import com.example.yuedong.library.utils.NetworkUtil;
import com.example.yuedong.library.utils.ToastUtil;
import com.google.gson.JsonParseException;

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

public class JDExceptionManager {

    public static JDException handleException(Throwable throwable) {

        JDException jdException = null;

        boolean isUnknown;

        if (throwable instanceof HttpException) {
            isUnknown = false;
            HttpException httpException = (HttpException) throwable;
            jdException = new JDException(ERROR_INFO.HTTP_ERROR);
            if (NetworkUtil.isNetworkConnected(MainApplication.getContext())) {
                jdException.setErrorMsg("服务器连接异常");
            } else {
                jdException.setErrorMsg("请检查您的网络配置");
            }
        } else if (throwable instanceof ApiException) {
            isUnknown = false;
            jdException = new JDException(ERROR_INFO.ERROR);
            if (500 == ((ApiException) throwable).getCode()) {
                jdException.setErrorMsg("服务器异常");
            } else {
                jdException.setErrorMsg(((ApiException) throwable).getMsg());
            }
        } else if (throwable instanceof JSONException
                || throwable instanceof JsonParseException
                || throwable instanceof ParseException) {
            isUnknown = false;
            jdException = new JDException(ERROR_INFO.PARSE_ERROR);
            jdException.setErrorMsg("数据处理异常");
        } else if (throwable instanceof ConnectException
                || throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException) {
            isUnknown = false;
            jdException = new JDException(ERROR_INFO.HTTP_ERROR);
            if (NetworkUtil.isNetworkConnected(MainApplication.getContext())) {
                jdException.setErrorMsg("连接超时");
            } else {
                jdException.setErrorMsg("请检查您的网络配置");
            }
        } else {
            isUnknown = true;
            jdException = new JDException(ERROR_INFO.UNKNOWN);
            jdException.setErrorMsg("未知错误");
        }
        if (!isUnknown){
            ToastUtil.showShort(MainApplication.getContext(), jdException.getErrorMsg());
        }
        return jdException;
    }

}
