package com.example.yuedong.library.views.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuedong.library.R;
import com.example.yuedong.library.base.BaseFragment_;
import com.vondear.rxtools.model.ActionItem;
import com.vondear.rxtools.view.popupwindows.RxPopupSingleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildOneFragment extends BaseFragment_ {

    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;
    Unbinder unbinder;

    public static ChildOneFragment newInstance() {

        Bundle args = new Bundle();

        ChildOneFragment fragment = new ChildOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout(Bundle savedInstanceState) {
        return R.layout.fragment_child_one;
    }

    @Override
    protected void initData(Bundle bundle) {

    }




    @OnClick({R.id.text_view, R.id.parent_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_view:
              $.showSelectedPop(view,new String[]{"标清","高清","超清"}).setItemOnClickListener(new RxPopupSingleView.OnItemOnClickListener() {
                  @Override
                  public void onItemClick(ActionItem actionItem, int i) {
                      $.showToast(actionItem.mTitle.toString());
                  }
              });
                break;
            case R.id.parent_layout:
                break;
        }
    }
}
