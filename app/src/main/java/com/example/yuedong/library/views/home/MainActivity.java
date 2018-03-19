package com.example.yuedong.library.views.home;

import android.Manifest;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.yuedong.library.R;
import com.example.yuedong.library.api.HomeApi;
import com.example.yuedong.library.base.BaseActivity;
import com.example.yuedong.library.base.BaseActivity_;
import com.example.yuedong.library.http.MTransformer;
import com.example.yuedong.library.http.download.DownloadObserver;
import com.example.yuedong.library.http.download.DownloadRetrofit;
import com.example.yuedong.library.listener.QuestPermissionListener;
import com.example.yuedong.library.presenter.HomePresenter;
import com.example.yuedong.library.presenter.contract.HomeContract;
import com.example.yuedong.library.views.home.fragments.OneFragment;
import com.example.yuedong.library.views.home.fragments.ThreeFragment;
import com.example.yuedong.library.views.home.fragments.TwoFragment;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_BottomBarTab;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;
import com.vondear.rxtools.view.RxTitle;


import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @BindView(R.id.viewPager)
    W_ViewPager viewPager;
    @BindView(R.id.bottomBar)
    W_BottomBar mBottomBar;
    private Fragment[] fragments = {new OneFragment(), new TwoFragment(), new ThreeFragment()};
    private int[] icon = {R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp};
    private String[] title = {"首页", "消息", "我的"};
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public int initLayout(Bundle savedInstanceState) {

        return R.layout.activity_main;

    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    public void initData(Bundle bundle) {
        mPresenter.initBottomMenu(this, fragments, icon, title, mBottomBar, viewPager, false);
    }

    @Override
    protected void clearDisposable() {
        mPresenter.cleardisposable();
    }


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void callBottomPostion(int pos) {

    }
}