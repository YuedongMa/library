package com.example.yuedong.library.views.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.example.yuedong.library.utils.PaperUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogScaleView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseFragment_ {
    @BindView(R.id.btclick)
    Button btclick;

    public static ThreeFragment newInstance() {
        Bundle args = new Bundle();
        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_three;
    }

    @Override
    protected void initData() {
        PaperUtils.initCity(getActivity());
    }

    @Override
    protected void onEvent() {

        btclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(getActivity());
                rxDialogScaleView.setImageRes(R.mipmap.ic_launcher);
                rxDialogScaleView.show();

            }
        });
    }


}
