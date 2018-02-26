package com.example.yuedong.library.exception;

/**
 * 错误信息
 *
 * Created by Administrator on 2017/9/12.
 */

public enum ERROR_INFO {

	/**
	 * 1. 操作失败、校验失败（常用）
	 * 2. 业务异常，比如需要删除一条数据数据库没有
	 * 3. 全局异常，比如找不到资源，没有权限
	 * 4. 必填参数为空
	 * 5. 参数格式问题
	 * 6. 服务器内部错误
	 * 7. token 失效
	 * 8. HTTP 错误
	 * 9. 解析错误
	 * 10.未知错误
	 */

	FAILED,
	BUSI_EXCEPTION,
	OVERALL_EXCEPTION,
	PARAMETER_MISSING,
	PARAMETER_WRONG,
	ERROR,
	TOKEN_INVALID,
	HTTP_ERROR ,
	PARSE_ERROR ,
	UNKNOWN

}
