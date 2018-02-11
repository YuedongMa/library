package com.example.yuedong.library.utils;

import android.util.Log;

import com.example.yuedong.library.BuildConfig;


/**
 *
 * @author Administrator
 * @date 2017/9/6
 */

public class LogUtils {

	private static final String V_TAG = "log_ver";
	private static final String I_TAG = "log_info";
	private static final String D_TAG = "log_dubug";
	private static final String E_TAG = "log_error";

	/**
	 * Verbose 打印
	 *
	 * @param verbose
	 */
	public static void v(String verbose) {
		if (BuildConfig.DEBUG){
			Log.v(V_TAG, StringUtils.isEmpty(verbose) ? "" : verbose);
		}
	}

	/**
	 * Infomation 打印
	 *
	 * @param infomation
	 */
	public static void i(String infomation) {
		if (BuildConfig.DEBUG){
			Log.i(I_TAG, StringUtils.isEmpty(infomation) ? "" : infomation);
		}
	}

	/**
	 * Debug 打印
	 *
	 * @param debug
	 */
	public static void d(String debug) {
		if (BuildConfig.DEBUG){
			Log.d(D_TAG, StringUtils.isEmpty(debug) ? "" : debug);
		}
	}

	/**
	 * Error 打印
	 *
	 * @param error
	 */
	public static void e(String error) {
		if (BuildConfig.DEBUG){
			Log.e(E_TAG, StringUtils.isEmpty(error) ? "" : error);
		}
	}

}
