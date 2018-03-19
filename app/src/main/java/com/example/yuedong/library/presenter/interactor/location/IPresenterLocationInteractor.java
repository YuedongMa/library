package com.example.yuedong.library.presenter.interactor.location;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;


/**
 * 本地事件交互
 * Created by YuedongMa on 2018/2/26.
 */

public interface IPresenterLocationInteractor {
    interface onLocaionEventFinishListener {
        void onLocationEventFinish(AsyncLocationManagerResult result, int eventType);
    }

    void onCitySelected(Context context, onLocaionEventFinishListener listener);

    void onTimeSelected(Context context, onLocaionEventFinishListener listener);

    void initBottomMenu(Context context, Fragment[] fragments, int[] icon, String[] titles, W_BottomBar bottomBar, W_ViewPager viewPager, boolean isCanSlide, onLocaionEventFinishListener listener);
void showCommentDialog(Context context,String content,String hint,onLocaionEventFinishListener listener);
}
