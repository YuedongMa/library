package com.example.yuedong.library.http;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 *
 *
 */

public class JDHttpConfig {

	private static JDHttpConfig sInstance;

	private String baseUrl;
	private int retryCount;
	private int retryDelayMillis;

	/**
	 * 主机域名验证
	 */
	private HostnameVerifier hostnameVerifier;

	/**
	 * SSL 工厂
	 */
	private SSLSocketFactory sslSocketFactory;

	private Converter.Factory converterFactory;
	private CallAdapter.Factory callAdapterFactory;

	public static JDHttpConfig getInstance() {
		if (sInstance == null) {
			synchronized (JDHttpConfig.class) {
				if (sInstance == null) {
					sInstance = new JDHttpConfig();
				}
			}
		}
		return sInstance;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Converter.Factory getConverterFactory() {
		return converterFactory;
	}

	public CallAdapter.Factory getCallAdapterFactory() {
		return callAdapterFactory;
	}

	/**
	 * 设置请求 baseUrl
	 *
	 * @param baseUrl
	 * @return
	 */
	public JDHttpConfig baseUrl(String baseUrl) {
		this.baseUrl = notNull(baseUrl, "baseUrl == null");
		return this;
	}

	/**
	 * 设置转换工厂
	 *
	 * @param factory
	 * @return
	 */
	public JDHttpConfig converterFactory(Converter.Factory factory) {
		this.converterFactory = factory;
		return this;
	}

	/**
	 * 设置 CallAdapter 工厂
	 *
	 * @param factory
	 * @return
	 */
	public JDHttpConfig callAdapterFactory(CallAdapter.Factory factory) {
		this.callAdapterFactory = factory;
		return this;
	}

	public JDHttpConfig hostnameVerifier(HostnameVerifier hostnameVerifier){
		this.hostnameVerifier = hostnameVerifier;
		return this;
	}

	public JDHttpConfig sslSocketFactory(SSLSocketFactory sslSocketFactory){
		this.sslSocketFactory = sslSocketFactory;
		return this;
	}

	/**
	 * 设置请求失败重试次数
	 *
	 * @param retryCount
	 * @return
	 */
	public JDHttpConfig retryCount(int retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	/**
	 * 设置请求失败重试间隔时间
	 *
	 * @param retryDelayMillis
	 * @return
	 */
	public JDHttpConfig retryDelayMillis(int retryDelayMillis) {
		this.retryDelayMillis = retryDelayMillis;
		return this;
	}

	/**
	 * 设置连接超时时间（秒）
	 *
	 * @param timeout
	 * @return
	 */
	public JDHttpConfig connectTimeout(int timeout) {
		return connectTimeout(timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置读取超时时间（秒）
	 *
	 * @param timeout
	 * @return
	 */
	public JDHttpConfig readTimeout(int timeout) {
		return readTimeout(timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置写入超时时间（秒）
	 *
	 * @param timeout
	 * @return
	 */
	public JDHttpConfig writeTimeout(int timeout) {
		return writeTimeout(timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置连接超时时间
	 *
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public JDHttpConfig connectTimeout(int timeout, TimeUnit unit) {
		if (timeout > -1) {
			JDHttp.getOkHttpBuilder().connectTimeout(timeout, unit);
		} else {
			JDHttp.getOkHttpBuilder().connectTimeout(30, TimeUnit.SECONDS);
		}
		return this;
	}

	/**
	 * 设置写入超时时间
	 *
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public JDHttpConfig writeTimeout(int timeout, TimeUnit unit) {
		if (timeout > -1) {
			JDHttp.getOkHttpBuilder().writeTimeout(timeout, unit);
		} else {
			JDHttp.getOkHttpBuilder().writeTimeout(30, TimeUnit.SECONDS);
		}
		return this;
	}

	/**
	 * 设置读取超时时间
	 *
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public JDHttpConfig readTimeout(int timeout, TimeUnit unit) {
		if (timeout > -1) {
			JDHttp.getOkHttpBuilder().readTimeout(timeout, unit);
		} else {
			JDHttp.getOkHttpBuilder().readTimeout(30, TimeUnit.SECONDS);
		}
		return this;
	}

	private String notNull(String baseUrl, String message) {
		if (baseUrl == null) {
			throw new NullPointerException(message);
		}
		return baseUrl;
	}

	public HostnameVerifier getHostnameVerifier() {
		return hostnameVerifier;
	}

	public SSLSocketFactory getSslSocketFactory() {
		return sslSocketFactory;
	}
}
