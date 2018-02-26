package com.example.yuedong.library.utils;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 *
 * @author Administrator
 * @date 2017/9/22
 */

public class RxUtils {

    /**
     * 切断所有 Disposable
     *
     * @param disposables
     */
    public static void clearDisposables(List<Disposable> disposables){
        if (disposables != null){
            for(Disposable disposable : disposables){
                if (!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
    }

}
