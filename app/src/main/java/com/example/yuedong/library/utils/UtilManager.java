package com.example.yuedong.library.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuedong.library.R;
import com.vondear.rxtools.SLogTool;
import com.vondear.rxtools.model.ActionItem;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.popupwindows.RxPopupImply;
import com.vondear.rxtools.view.popupwindows.RxPopupSingleView;
import com.vondear.rxtools.view.popupwindows.tools.RxPopupView;
import com.vondear.rxtools.view.popupwindows.tools.RxPopupViewManager;

/**
 * Created by YuedongMa on 2018/2/24.
 */

public class UtilManager implements RxPopupViewManager.TipListener {
    private static UtilManager utilManager;
    private Context context;

    public UtilManager(Context context) {
        this.context = context;
    }

    public static UtilManager getInstance(Context context) {
        if (utilManager == null) return new UtilManager(context);
        return utilManager;
    }

    //==============================================Toast Start=================================================================
    public void showToast(String msg) {
        RxToast.normal(context, msg).show();
    }

    public void showSuccess(String msg) {
        RxToast.success(context, msg).show();
    }

    public void showError(String msg) {
        RxToast.error(context, msg).show();
    }

    public void showInfo(String msg) {
        RxToast.info(context, msg).show();
    }

    public void showWarn(String msg) {
        RxToast.warning(context, msg).show();
    }

    //自定义部分
    //。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。

    //==========================================Toast End===================================================================
    private RxPopupViewManager rxPopupViewManager;
    private RxPopupView.Builder builder;
    private RxPopupSingleView titlePopup;
    private int defaultAlign = RxPopupView.ALIGN_CENTER;
    private int defaultDirection = RxPopupView.POSITION_BELOW;

    //====================================popUpWindows Start==================================================================


    public View showPop(View view, ViewGroup parentView, String msg) {
        if (rxPopupViewManager == null) {
            rxPopupViewManager = new RxPopupViewManager(this);
            builder = new RxPopupView.Builder(context, view, parentView, msg, defaultDirection);
            builder.setAlign(defaultAlign);
            builder.setTextSize(12);
            builder.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        rxPopupViewManager.findAndDismiss(view);
        View tv = rxPopupViewManager.show(builder.build());
        return tv;
    }

    public RxPopupSingleView showSelectedPop(View view, String[] arr) {
        if (titlePopup == null) {
            initPopupView(arr);
            titlePopup.setColorItemText(context.getResources().getColor(R.color.gray));
            titlePopup.show(view, 0);//dex与view的上下间距
        }
        return titlePopup;
    }
    //返回的titlePopup可在视图中实现点击选择事件
//      titlePopup.setItemOnClickListener(new RxPopupSingleView.OnItemOnClickListener() {
//
//        @Override
//        public void onItemClick(ActionItem item, int position) {
//
//        }
//    });

    public UtilManager setPopDirection(int direction) {
        this.defaultDirection = direction;
        return this;
    }

    public UtilManager setPopAlign(int align) {
        this.defaultAlign = align;
        return this;
    }

    private void initPopupView(String[] arr) {
        titlePopup = new RxPopupSingleView(context, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.popupwindow_definition_layout);//布局中的list view可根据view的宽度设置相应的宽度，使其相对应
        for (int i = 0; i < arr.length; i++) {
            titlePopup.addAction(new ActionItem(arr[i]));
        }

    }

    @Override
    public void onTipDismissed(View view, int i, boolean b) {
        SLogTool.e(view + "" + i + "" + b + "");

    }
}
