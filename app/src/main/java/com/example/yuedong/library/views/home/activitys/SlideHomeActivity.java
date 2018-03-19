package com.example.yuedong.library.views.home.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseActivity;
import com.example.yuedong.library.presenter.HomePresenter;
import com.example.yuedong.library.presenter.contract.HomeContract;
import com.example.yuedong.library.views.home.fragments.OneFragment;
import com.example.yuedong.library.views.home.fragments.ThreeFragment;
import com.example.yuedong.library.views.home.fragments.TwoFragment;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_ViewPager;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;

public class SlideHomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @BindView(R.id.viewPager)
    W_ViewPager viewPager;
    @BindView(R.id.bottomBar)
    W_BottomBar mBottomBar;
    private Fragment[] fragments = {new OneFragment(), new TwoFragment(), new ThreeFragment()};
    private int[] icon = {R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp, R.mipmap.ic_message_white_24dp};
    private String[] title = {"首页", "消息", "我的"};

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.activity_slide_home;
    }

    @Override
    public void initData(Bundle bundle) {
        mPresenter.initBottomMenu(this, fragments, icon, title, mBottomBar, viewPager, true);
    }

    @Override
    public void callBottomPostion(int pos) {
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected void clearDisposable() {
        mPresenter.cleardisposable();
    }
}
