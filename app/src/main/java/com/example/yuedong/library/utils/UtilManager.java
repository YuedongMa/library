package com.example.yuedong.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuedong.library.R;
import com.example.yuedong.library.widget.dialog.CommentDialog;
import com.vondear.rxtools.SDeviceTool;
import com.vondear.rxtools.SLogTool;
import com.vondear.rxtools.model.ActionItem;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialog;
import com.vondear.rxtools.view.dialog.RxDialogScaleView;
import com.vondear.rxtools.view.popupwindows.RxPopupImply;
import com.vondear.rxtools.view.popupwindows.RxPopupSingleView;
import com.vondear.rxtools.view.popupwindows.tools.RxPopupView;
import com.vondear.rxtools.view.popupwindows.tools.RxPopupViewManager;

/**
 * Created by YuedongMa on 2018/2/24.
 */

public class UtilManager implements RxPopupViewManager.TipListener {
    private static UtilManager utilManager;
    private Context mcontext;

    public UtilManager(Context context) {
        this.mcontext = context;
    }

    public static UtilManager getInstance(Context context) {
        if (utilManager == null) return new UtilManager(context);
        return utilManager;
    }

    //==============================================Toast Start=================================================================
    public void showToast(String msg) {
        RxToast.normal(mcontext, msg).show();
    }

    public void showSuccess(String msg) {
        RxToast.success(mcontext, msg).show();
    }

    public void showError(String msg) {
        RxToast.error(mcontext, msg).show();
    }

    public void showInfo(String msg) {
        RxToast.info(mcontext, msg).show();
    }

    public void showWarn(String msg) {
        RxToast.warning(mcontext, msg).show();
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
            builder = new RxPopupView.Builder(mcontext, view, parentView, msg, defaultDirection);
            builder.setAlign(defaultAlign);
            builder.setTextSize(12);
            builder.setBackgroundColor(mcontext.getResources().getColor(R.color.gray));
        }
        rxPopupViewManager.findAndDismiss(view);
        View tv = rxPopupViewManager.show(builder.build());
        return tv;
    }

    public RxPopupSingleView showSelectedPop(View view, String[] arr) {
        if (titlePopup == null) {
            initPopupView(arr);
            titlePopup.setColorItemText(mcontext.getResources().getColor(R.color.gray));
        }

        titlePopup.show(view, 0);//dex与view的上下间距
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
        titlePopup = new RxPopupSingleView(mcontext, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.popupwindow_definition_layout);//布局中的list view可根据view的宽度设置相应的宽度，使其相对应
        for (int i = 0; i < arr.length; i++) {
            titlePopup.addAction(new ActionItem(arr[i]));
        }

    }

    @Override
    public void onTipDismissed(View view, int i, boolean b) {

    }

    //==========================================popUpWindow End============================================================================================
    private CommentDialog commentDialog;
    private RxDialog rxDialog;
    private RxDialogScaleView rxDialogScaleView;

    //============================================Dialog Start================================================================================================

    /**
     * 根据布局显示公共dialog
     * 利用dialog.findviewbyid来找到其内部控件的实例
     *
     * @param context
     * @param layoutRes
     * @return
     */
    public CommentDialog showCommentDilog(Context context, int layoutRes) {
        commentDialog = null;
        commentDialog = new CommentDialog(context);
        View view = LayoutInflater.from(context).inflate(layoutRes, null);
        commentDialog.setContentView(view);
        commentDialog.show();
        return commentDialog;
    }

    /**
     * 固定布局的公共dialog
     * @param context
     * @param content 内容
     * @param hint 提示
     * @return
     */
    public CommentDialog showCommentDilog(Context context, String content, String hint) {
        commentDialog = null;
        commentDialog = new CommentDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
        TextView tvcontent = (TextView) view.findViewById(R.id.tvContent);
        TextView tvHint = (TextView) view.findViewById(R.id.tvHint);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        tvHint.setText(hint);
        tvcontent.setText(content);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentDialog.dismiss();
            }
        });
        commentDialog.setContentView(view);
        commentDialog.show();
        return commentDialog;
    }

    /**
     * 显示透明dialog
     *
     * @param context
     * @param layoutres
     * @return
     */
    public RxDialog showTranDialog(Context context, int layoutres) {
        rxDialog = null;
        rxDialog = new RxDialog(context);
        View view1 = LayoutInflater.from(context).inflate(layoutres, null);
        rxDialog.setContentView(view1);
        rxDialog.show();
        return rxDialog;
    }

    /**
     * 显示可缩放的图片
     *
     * @param context
     * @param res
     */
    public void showBigImage(Context context, int res) {
        rxDialogScaleView = null;
        rxDialogScaleView = new RxDialogScaleView(context);
        rxDialogScaleView.setImageRes(res);
        rxDialogScaleView.show();
    }

    /**
     * 显示可缩放的图片
     *
     * @param context
     * @param bitmap
     */
    public void showBigImage(Context context, Bitmap bitmap) {
        rxDialogScaleView = null;
        rxDialogScaleView = new RxDialogScaleView(context);
        rxDialogScaleView.setImageBitmap(bitmap);
        rxDialogScaleView.show();
    }
}
