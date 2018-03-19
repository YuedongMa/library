package com.example.yuedong.library.http;



import com.example.yuedong.library.utils.SSLUtil;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 *
 */

public abstract class MBaseRequest {

	private static MHttpConfig jdHttpConfig;

	/**
	 * 生成全局配置
	 */
	public static void generateJDHttpConfig() {
		jdHttpConfig = MHttp.config();

		if (jdHttpConfig.getBaseUrl() == null) {
			jdHttpConfig.baseUrl("");
		}

		MHttp.getRetrofitBuilder().baseUrl(jdHttpConfig.getBaseUrl());

		if (jdHttpConfig.getConverterFactory() != null) {
			MHttp.getRetrofitBuilder().addConverterFactory(jdHttpConfig.getConverterFactory());
		}

		if (jdHttpConfig.getCallAdapterFactory() == null) {
			jdHttpConfig.callAdapterFactory(RxJava2CallAdapterFactory.create());
		}

		if (jdHttpConfig.getHostnameVerifier() == null){
			jdHttpConfig.hostnameVerifier(new SSLUtil.UnSafeHostnameVerifier(jdHttpConfig.getBaseUrl()));
		}
		MHttp.getOkHttpBuilder().hostnameVerifier(jdHttpConfig.getHostnameVerifier());

		if (jdHttpConfig.getSslSocketFactory() == null){
			jdHttpConfig.sslSocketFactory(SSLUtil.getSslSocketFactory(null, null, null));
		}
		MHttp.getOkHttpBuilder().sslSocketFactory(jdHttpConfig.getSslSocketFactory());

		MHttp.getRetrofitBuilder().addCallAdapterFactory(jdHttpConfig.getCallAdapterFactory());
		MHttp.client();
	}

}
