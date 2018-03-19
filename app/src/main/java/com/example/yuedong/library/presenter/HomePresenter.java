package com.example.yuedong.library.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.yuedong.library.base.basepresenter.MPresenter;
import com.example.yuedong.library.presenter.contract.HomeContract;
import com.example.yuedong.library.presenter.interactor.location.AsyncLocationManagerResult;
import com.example.yuedong.library.presenter.interactor.location.IPresenterLocationInteractor;
import com.example.yuedong.library.presenter.interactor.location.PresenterLocationInteractorImpl;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;

/**
 * Created by YuedongMa on 2018/3/8.
 */

public class HomePresenter extends MPresenter<HomeContract.View> implements HomeContract.Presenter, IPresenterLocationInteractor.onLocaionEventFinishListener {
    private IPresenterLocationInteractor locationInteractor;

    public HomePresenter() {
        locationInteractor = new PresenterLocationInteractorImpl();
    }

    //页面销毁时将接口置空，防内存泄漏
    @Override
    public void cleardisposable() {
        locationInteractor = null;

    }

    @Override
    public void initBottomMenu(Context context,Fragment[] fragments, int[] icon, String[] titles, W_BottomBar bottomBar, W_ViewPager viewPager, boolean isCanSlide) {
        locationInteractor.initBottomMenu(context,fragments, icon, titles, bottomBar, viewPager, isCanSlide, this);
    }

    @Override
    public void onLocationEventFinish(AsyncLocationManagerResult result, int eventType) {
        if (eventType == result.KEY_BOTTOM_MENU_SELECTED) {
            mView.callBottomPostion(result.getResult(Integer.class));
        }
    }
}
