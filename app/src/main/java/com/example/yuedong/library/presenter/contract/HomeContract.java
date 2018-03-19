package com.example.yuedong.library.presenter.contract;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.yuedong.library.base.basepresenter.BasePresenter;
import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;

/**
 * Created by YuedongMa on 2018/3/8.
 */

public class HomeContract {
    public interface View extends BaseView {
        void callBottomPostion(int pos);
    }

    public interface Presenter extends BasePresenter<View> {
        void cleardisposable();

        void initBottomMenu(Context context, Fragment[] fragments, int[] icon, String[] titles, W_BottomBar bottomBar, W_ViewPager viewPager, boolean isCanSlide);
    }
}
