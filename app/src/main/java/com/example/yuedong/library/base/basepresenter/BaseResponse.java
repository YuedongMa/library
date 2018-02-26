package com.example.yuedong.library.base.basepresenter;

import android.support.annotation.Keep;

import com.example.yuedong.library.models.response.ResponseData;

/**
 * 后台返回数据统一封装
 *
 * @author EHN
 * @param <T>
 */
@Keep
public class BaseResponse<T> {
	/**
	 * {"code": 200,
	 "cause": null,
	 "msg": "请求成功",
	 "version": "1.0",
	 "result" : {
	 "code" : 0,  //业务状态码
	 "cause": null,
	 "msg": "请求成功",
	 "data" : {
	 "token" : "用户通过浏览器生成标识"
	 }
	 }
	 */

	/**
	 * SUCCESS              200   操作成功、校验成功（常用）
	   FAILED	            400   操作失败、校验失败（常用）
	   BUSI_EXCEPTION	    404	  业务异常，比如需要删除一条数据数据库没有
	   OVERALL_EXCEPTION	405	  全局异常，比如找不到资源，没有权限
	   PARAMETER_MISSING	410	  必填参数为空
	   PARAMETER_WRONG	    411	  参数格式问题
	   ERROR_INFO	            500	  服务器内部错误
	 */

	private int code;
	private String cause;
	private String msg;
	private String version;

	public ResponseData<T> getResult() {
		return result;
	}

	public void setResult(ResponseData<T> result) {
		this.result = result;
	}

	private ResponseData<T> result;
	public BaseResponse() {
	}

	public BaseResponse(int code, String cause, String msg, String version) {
		this.code = code;
		this.cause = cause;
		this.msg = msg;
		this.version = version;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	/**
	 * 判断请求是否成功
	 *
	 * @return
	 */
	public boolean isSuccess(){
		return 200 == getCode();
	}

	@Override
	public String toString() {
		return "BaseResponse{" +
				"code=" + code +
				", cause='" + cause + '\'' +
				", msg='" + msg + '\'' +
				", version='" + version + '\'' +

				'}';
	}
}
