package com.example.yuedong.library.exception;

/**
 * Created by Administrator on 2017/9/12.
 */

public class JDException {

	ERROR_INFO error;
	String errorMsg;

	public JDException(ERROR_INFO code){
		this.error=code;
	}

	public ERROR_INFO getError() {
		return error;
	}

	public void setError(ERROR_INFO error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
