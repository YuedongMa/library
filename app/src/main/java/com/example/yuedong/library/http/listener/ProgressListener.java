package com.example.yuedong.library.http.listener;

/**
 * 文件下载进度监听
 *
 */
public interface ProgressListener {

    /**
     * 下载进度监听
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param done          是否下载完成
     */
    void onResponseProgress(long bytesRead, long contentLength, int progress, boolean done, String filePath);

}
