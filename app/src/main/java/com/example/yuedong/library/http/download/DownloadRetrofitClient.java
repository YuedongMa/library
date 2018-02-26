package com.example.yuedong.library.http.download;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件下载 Retrofit
 */
public class DownloadRetrofitClient {

    private static DownloadRetrofitClient instance;

    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOkHttpBuilder;

    public DownloadRetrofitClient() {

        mOkHttpBuilder = new OkHttpClient.Builder();

        mRetrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpBuilder.build());
    }


    public static DownloadRetrofitClient getInstance() {

        if (instance == null) {
            synchronized (DownloadRetrofitClient.class) {
                if (instance == null) {
                    instance = new DownloadRetrofitClient();
                }
            }

        }
        return instance;
    }


    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder;
    }

    public Retrofit getRetrofit() {
        return mRetrofitBuilder.client(mOkHttpBuilder.build()).build();
    }

}
