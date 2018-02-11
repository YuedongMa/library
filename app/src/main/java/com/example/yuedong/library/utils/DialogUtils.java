package com.example.yuedong.library.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yuedong.library.R;
import com.example.yuedong.library.widget.dialog.CommentDialog;

/**
 * Created by mayuedong on 2017/12/6.
 */

public class DialogUtils {
    /**
     * 公共弹出框示例，自定义布局
     *
     * @param context
     */
    public static void showDialog(final Context context) {
        final CommentDialog dialog = new CommentDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
        TextView tvType = (TextView) view.findViewById(R.id.tvTiShi);
        tvType.setText("提醒");
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        //dialog.setFullScreen();
    }
}
