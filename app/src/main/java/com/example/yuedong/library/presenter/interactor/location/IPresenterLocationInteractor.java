package com.example.yuedong.library.presenter.interactor.location;

import android.content.Context;

import com.example.yuedong.library.presenter.interactor.AsyncManagerResult;

/**
 * 本地事件交互
 * Created by YuedongMa on 2018/2/26.
 */

public interface IPresenterLocationInteractor {
    interface onLocaionEventFinishListener {
        void onLocationEventFinish(AsyncManagerResult result,int eventType);
    }

    void onCitySelected(Context context, onLocaionEventFinishListener listener);
    void onTimeSelected(Context context, onLocaionEventFinishListener listener);
}
