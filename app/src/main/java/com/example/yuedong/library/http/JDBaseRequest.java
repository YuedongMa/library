package com.example.yuedong.library.http;



import com.example.yuedong.library.utils.SSLUtil;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 *
 */

public abstract class JDBaseRequest {

	private static JDHttpConfig jdHttpConfig;

	/**
	 * 生成全局配置
	 */
	public static void generateJDHttpConfig() {
		jdHttpConfig = JDHttp.config();

		if (jdHttpConfig.getBaseUrl() == null) {
			jdHttpConfig.baseUrl("");
		}

		JDHttp.getRetrofitBuilder().baseUrl(jdHttpConfig.getBaseUrl());

		if (jdHttpConfig.getConverterFactory() != null) {
			JDHttp.getRetrofitBuilder().addConverterFactory(jdHttpConfig.getConverterFactory());
		}

		if (jdHttpConfig.getCallAdapterFactory() == null) {
			jdHttpConfig.callAdapterFactory(RxJava2CallAdapterFactory.create());
		}

		if (jdHttpConfig.getHostnameVerifier() == null){
			jdHttpConfig.hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier(jdHttpConfig.getBaseUrl()));
		}
		JDHttp.getOkHttpBuilder().hostnameVerifier(jdHttpConfig.getHostnameVerifier());

		if (jdHttpConfig.getSslSocketFactory() == null){
			jdHttpConfig.sslSocketFactory(SSLUtil.getSslSocketFactory(null, null, null));
		}
		JDHttp.getOkHttpBuilder().sslSocketFactory(jdHttpConfig.getSslSocketFactory());

		JDHttp.getRetrofitBuilder().addCallAdapterFactory(jdHttpConfig.getCallAdapterFactory());
		JDHttp.client();
	}

}
