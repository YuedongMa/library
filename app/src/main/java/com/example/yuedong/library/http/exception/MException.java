package com.example.yuedong.library.http.exception;

public class MException {

	ERROR_INFO error;
	String errorMsg;

	public MException(ERROR_INFO code){
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
