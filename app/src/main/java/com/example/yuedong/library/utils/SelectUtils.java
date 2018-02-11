package com.example.yuedong.library.utils;

import android.graphics.Color;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.yuedong.library.R;

/**
 * Created by mayuedong on 2017/10/25.
 */

public class SelectUtils {

    private static int bg = Color.parseColor("#52BA97");

    /**
     * 类型选择器
     *
     * @param builder
     * @param tilte
     */
    public static OptionsPickerView showPicker(OptionsPickerView.Builder builder, String tilte) {
        return builder.setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(tilte)//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.parseColor("#ffffff"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#ffffff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#ffffff"))//取消按钮文字颜色
                .setTitleBgColor(bg)//标题背景颜色 Night mode
                .setBgColor(Color.TRANSPARENT)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(false)//设置是否联动，默认true
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .build();

    }

    /**
     * 城市选择
     *
     * @param builder
     * @param tilte
     * @return
     */
    public static OptionsPickerView showCityPicker(OptionsPickerView.Builder builder, String tilte) {
        return builder.setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(tilte)//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.parseColor("#ffffff"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#ffffff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#ffffff"))//取消按钮文字颜色
                .setTitleBgColor(bg)//标题背景颜色 Night mode
                .setBgColor(Color.TRANSPARENT)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .setLinkage(true)//设置是否联动，默认true
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(false)//是否显示为对话框样式
                .build();

    }

    /**
     * 时间选择
     *
     * @param builder
     * @param tilte
     * @return
     */
    public static TimePickerView showTimePicker(TimePickerView.Builder builder, String tilte) {
        return builder.setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(tilte)//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.parseColor("#ffffff"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#ffffff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#ffffff"))//取消按钮色
                .setTitleBgColor(bg)//标题背景颜色 Night mode
                .setBgColor(Color.TRANSPARENT)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();

    }
}
