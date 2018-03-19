package com.example.yuedong.library.views.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.example.yuedong.library.views.home.DrawerActivity;
import com.vondear.rxtools.view.dialog.RxDialogScaleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseFragment_ {
    @BindView(R.id.btclick)
    Button btclick;
    @BindView(R.id.btDrawer)
    Button btDrawer;
    @BindView(R.id.btToast)
    Button btToast;

    @Override
    protected int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_three;
    }

    @Override
    protected void initData(Bundle bundle) {

    }


    @OnClick({R.id.btclick, R.id.btDrawer,R.id.btToast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btclick:
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(getActivity());
                rxDialogScaleView.setImageRes(R.mipmap.ic_launcher);
                rxDialogScaleView.show();
                break;
            case R.id.btDrawer:
                startActivity(new Intent(getActivity(), DrawerActivity.class));
                break;
            case R.id. btToast:

                break;
        }
    }
}
