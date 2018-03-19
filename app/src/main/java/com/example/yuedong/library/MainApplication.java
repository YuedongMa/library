package com.example.yuedong.library;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.example.yuedong.library.config.ApiConfig;
import com.example.yuedong.library.http.MBaseRequest;
import com.example.yuedong.library.http.MHttp;
import com.example.yuedong.library.http.OriginJsonConverterFactory;
import com.example.yuedong.library.listener.activitymanager.MActivityLifecycleCallbacks;
import com.example.yuedong.library.utils.PaperUtils;
import com.example.yuedong.library.utils.SSLUtil;
import com.vondear.rxtools.RxTool;
import com.vondear.rxtools.SLogTool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.paperdb.Paper;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by mayuedong on 2017/10/25.
 */

public class MainApplication extends Application {
    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        String patchFileString = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + File.separator+"classes_dexs.dex";
//        File desFile = new File(patchFileString);
//        try {
//            if (!desFile.exists()) {
//                desFile.createNewFile();
//
//
//            }
//            FileUtils.copyFiles(getContext(), "classes.dex", desFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Paper.init(this);
        RxTool.init(this);
        SLogTool.init(this,true,"madong");
        if (PaperUtils.getCityOptionOne() == null) {
            new Init().execute();
        }


        httpConfig();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            this.registerActivityLifecycleCallbacks(new MActivityLifecycleCallbacks());
        }

    }

    private class Init extends AsyncTask<String, Void, Integer>

    {

        @Override
        protected Integer doInBackground(String... strings) {
            PaperUtils.initCity(mContext);
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {//任务执行完后执行
            super.onPostExecute(integer);

        }
    }

    /**
     * 网络请求基础配置
     */
    private void httpConfig() {
        MHttp.init(this, true);
        MHttp.config()
                .baseUrl(ApiConfig.BASE_URL)
                .readTimeout(30)
                .writeTimeout(30)
                .connectTimeout(30)
                .retryCount(3)
                .retryDelayMillis(1000)
                .converterFactory(OriginJsonConverterFactory.create())
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
                .sslSocketFactory(SSLUtil.createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier());
        MBaseRequest.generateJDHttpConfig();
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
