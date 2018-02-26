package com.example.yuedong.library.http;

import android.content.Context;

import com.example.yuedong.library.http.interceptor.HttpLogInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 *
 */

public class JDHttp {

	private static Context sContext;
	private static Retrofit.Builder sRetrofitBuilder;
	private static OkHttpClient.Builder sOkHttpBuilder;

	private static final JDHttpConfig sJDHttpConfig = JDHttpConfig.getInstance();

	public static void init(Context context, boolean isDebug) {
		if (sContext == null && context != null) {
			sContext = context.getApplicationContext();
			sOkHttpBuilder = new OkHttpClient.Builder();
			if (isDebug){
				sOkHttpBuilder.addInterceptor(new HttpLogInterceptor()
						.setLevel(HttpLogInterceptor.Level.BODY));
			}
			sRetrofitBuilder = new Retrofit.Builder();
		}
	}

	public static JDHttpConfig config() {
		return sJDHttpConfig;
	}

	public static Retrofit.Builder getRetrofitBuilder() {
		if (sRetrofitBuilder == null) {
			throw new IllegalStateException("Please call ViseHttp.init(this) in Application to initialize!");
		}
		return sRetrofitBuilder;
	}

	public static OkHttpClient.Builder getOkHttpBuilder() {
		if (sOkHttpBuilder == null) {
			throw new IllegalStateException("Please call JDHttp.init(this) in Application to initialize!");
		}
		return sOkHttpBuilder;
	}


	public static void client() {
		sRetrofitBuilder.client(sOkHttpBuilder.build());
	}


	public static <K> K createApi(Class<K> cls) {
		return getRetrofitBuilder().build().create(cls);
	}

	/**
	 * 版本更新下载安装包
	 *
	 * @param fileUrl
	 * @return
	 */
	/*public static Observable<ResponseBody> downloadFile(String fileUrl) {
		return DownloadRetrofit.downloadFile(fileUrl);
	}*/

}
