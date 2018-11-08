package com.yuedongma.view.tools;

import android.content.Context;

/**
 * Created by abc on 2018/3/22.
 */

public class PxUtil {
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);

    }
    public static int dp2PxInt(Context context, float dp) {
        return (int) (dip2px(context, dp) + 0.5f);
    }
}
