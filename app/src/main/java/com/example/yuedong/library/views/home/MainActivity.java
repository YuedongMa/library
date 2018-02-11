package com.example.yuedong.library.views.home;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseActivity_;
import com.example.yuedong.library.views.home.fragments.OneFragment;
import com.example.yuedong.library.views.home.fragments.ThreeFragment;
import com.example.yuedong.library.views.home.fragments.TwoFragment;
import com.example.yuedong.library.widget.bottombar.W_BottomBar;
import com.example.yuedong.library.widget.bottombar.W_BottomBarTab;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxTitle;

import butterknife.BindView;

public class MainActivity extends BaseActivity_ {
    private Fragment[] mFragments = new Fragment[3];
    private W_BottomBar mBottomBar;
    private FragmentManager fManager;
    @BindView(R.id.rx_title)
    RxTitle mRxTitle;
    @Override
    public int initLayout(Bundle savedInstanceState) {

        return R.layout.activity_main;
    }
    @Override
    public void initData() {
//        toCheckPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, this, new QuestPermissionListener() {
//            @Override
//            public void onPermissionAccept() {
//              //  Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPermissionRefuse() {
//             //   Toast.makeText(MainActivity.this, "ref", Toast.LENGTH_SHORT).show();
//            }
//        });

        mBottomBar = (W_BottomBar) findViewById(R.id.bottomBar);
        fManager = getSupportFragmentManager();
        switchFragment(0, 0, OneFragment.newInstance());
        mBottomBar
                .addItem(new W_BottomBarTab(this, R.mipmap.ic_message_white_24dp, "首页",new OneFragment()))
                .addItem(new W_BottomBarTab(this, R.mipmap.ic_message_white_24dp, "消息", TwoFragment.newInstance()))
                .addItem(new W_BottomBarTab(this, R.mipmap.ic_message_white_24dp, "我的", ThreeFragment.newInstance()))  ;

//
        mBottomBar.setOnTabSelectedListener(new W_BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                switchFragment(position, prePosition, mBottomBar.getItem(position).getFragment());
                mBottomBar.getItem(position).setUnreadCount(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void onEvent() {

    }
    private void switchFragment(int index, int pre, Fragment frament) {
        mBottomBar.setCurrentItem(index);
        FragmentTransaction transaction = fManager.beginTransaction();
        if (mFragments[pre] != null) transaction.hide(mFragments[pre]);
        if (mFragments[index] != null) {
            if (mFragments[index].getClass().getSimpleName().equals(frament.getClass().getSimpleName())) {
                transaction.show(mFragments[index]);
            } else {
                mFragments[index] = frament;
                transaction.add(R.id.tab_container, mFragments[index]);
            }
        } else {
            mFragments[index] = frament;
            transaction.add(R.id.tab_container, mFragments[index]);
        }
        transaction.commit();
    }
}