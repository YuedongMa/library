package com.example.yuedong.library.http.download;

import android.content.Context;

import com.example.yuedong.library.http.listener.ISubDownload;
import com.example.yuedong.library.http.listener.ProgressListener;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/9/21.
 */

public abstract class DownloadObserver implements Observer<ResponseBody>, ISubDownload {

    private String mFileName;
    private Context mContext;

    public DownloadObserver(Context context, String fileName){
        mFileName = fileName;
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        getDisposable(d);
    }

    @Override
    public void onNext(@NonNull ResponseBody responseBody) {
        Observable.just(responseBody)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        try {
                            new DownloadManager().saveFile(mContext, responseBody, mFileName, new ProgressListener() {
                                @Override
                                public void onResponseProgress(final long bytesRead, final long contentLength, final int progress, final boolean done, final String filePath) {
                                    Observable
                                            .just(progress)
                                            .distinctUntilChanged()
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Integer>() {
                                                @Override
                                                public void accept(@NonNull Integer integer) throws Exception {
                                                    onSuccess(bytesRead, contentLength, progress, done, filePath);
                                                }
                                            });
                                }

                            });

                        } catch (IOException e) {
                            onError(e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
