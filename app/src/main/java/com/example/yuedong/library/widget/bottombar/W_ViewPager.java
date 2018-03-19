package com.example.yuedong.library.widget.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.yuedong.library.R;

/**
 * Created by YuedongMa on 2018/3/8.
 */

public class W_ViewPager extends ViewPager {
    public boolean isCanSlide() {
        return isCanSlide;
    }

    public void setCanSlide(boolean canSlide) {
        isCanSlide = canSlide;
    }

    private boolean isCanSlide;

    public W_ViewPager(Context context) {
        super(context);
    }

    public W_ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.W_ViewPager);
        isCanSlide = typedArray.getBoolean(R.styleable.W_ViewPager_isSlide, false);
        typedArray.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanSlide && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanSlide && super.onTouchEvent(ev);
    }
}
