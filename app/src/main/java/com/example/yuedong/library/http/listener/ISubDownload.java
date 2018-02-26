package com.example.yuedong.library.http.listener;

import io.reactivex.disposables.Disposable;

/**
 *
 *
 */

public interface ISubDownload {

    /**
     * 获取 Disposable
     *
     * @param d
     */
    void getDisposable(Disposable d);

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param bytesRead
     * @param contentLength
     * @param progress
     * @param done
     * @param filePath
     */
    void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath);

}
