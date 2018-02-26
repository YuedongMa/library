package com.example.yuedong.library.http.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 下载文件
 *
 * Created by Administrator on 2017/9/21.
 */

public class DownloadRetrofit {

    private static DownloadRetrofit instance;

    private Retrofit mRetrofit;
    private static String baseUrl = "http://118.190.162.225:8080/";

    public DownloadRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static DownloadRetrofit getInstance() {
        if (instance == null) {
            synchronized (DownloadRetrofitClient.class) {
                if (instance == null) {
                    instance = new DownloadRetrofit();
                }
            }

        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        /*return DownloadRetrofit
                .getInstance()
                .getRetrofit()
                .create(MainApi.class)
                .downloadFile(fileUrl)
                .compose(JDTransformer.<ResponseBody>switchSchedulers());*/
        return null;
    }

}
