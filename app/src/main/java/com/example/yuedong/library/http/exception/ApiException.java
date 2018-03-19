package com.example.yuedong.library.http.exception;

/**
 * API 异常处理
 *
 * Created by Administrator on 2017/9/12.
 */

public class ApiException extends Exception {

    int code;
    String msg;

    public ApiException(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
