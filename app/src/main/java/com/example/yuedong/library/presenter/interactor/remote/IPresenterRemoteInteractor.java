package com.example.yuedong.library.presenter.interactor.remote;

import com.example.yuedong.library.presenter.interactor.AsyncManagerResult;

import io.reactivex.disposables.Disposable;

/**
 * Created by YuedongMa on 2018/2/26.
 */

public interface IPresenterRemoteInteractor {
    interface onRemoteEventFinishListener{
        void getDisposable(Disposable d);
        void onInerractorFinish(AsyncManagerResult result );
    }
    void updVersion(onRemoteEventFinishListener listener);
}
