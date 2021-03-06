package com.example.yuedong.library.presenter.interactor.remote;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by YuedongMa on 2018/2/26.
 */

public interface IPresenterRemoteInteractor {
    interface onRemoteEventFinishListener {
        void getDisposable(Disposable d);

        void onInerractorFinish(AsyncRemoteManagerResult result);
    }

    void getVersion(onRemoteEventFinishListener listener);
    void downLoadFile(Context context,String path,onRemoteEventFinishListener listener);
}
