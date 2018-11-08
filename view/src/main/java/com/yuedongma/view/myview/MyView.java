package com.yuedongma.view.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yuedongma.view.R;
import com.yuedongma.view.tools.PxUtil;

/**
 * Created by YuedongMa on 2018/1/15.
 */

public class MyView extends View {
    Paint paint = null;
    private int mwidth;
    private int mheight;
    private int defaultTextColor = Color.GRAY;//默认文本颜色
    private int defaultTextSize = PxUtil.sp2px(getContext(), 16);//默认文本大小
    private int defaultLineColor = getResources().getColor(R.color.black_e6);//默认上下线颜色
    private int marginTopLeftLine = PxUtil.dip2px(getContext(), 12);
    private int marginBottomLeftLine = PxUtil.dip2px(getContext(), 12);
    private int defaultLeftMargin = PxUtil.dip2px(getContext(), 12);//左侧边距
    private int defaultRightMargin = PxUtil.dip2px(getContext(), 12);//右侧边距
    private int defaultTextDistance = PxUtil.dip2px(getContext(), 10);//文本与其他绘制的间隔
    //左侧文本
    private String leftText;
    private float leftTextSize = defaultTextSize;
    private int leftTextColor = defaultTextColor;
    //中间文本
    private String centerText;
    private float centerTextSize = defaultTextSize;
    private int centerTextColor = defaultTextColor;
    //右侧文本
    private String rightText;
    private float rightTextSize = defaultTextSize;
    private int rightTextColor = defaultTextColor;
    //左右图标
    private int leftImage;
    private int rightImage;
    //左右图标宽度
    private int leftImageWidth;
    private int rightImageWidth;
    //左右图标高度
    private int leftImageHeight;
    private int rightImageHeight;
    //正常背景色
    private int normalColor = Color.WHITE;
    //按下背景色
    private int pressColor = normalColor;
    private float margindp = 0;
    //控件类型，button or layout
    private String type;
    //图标缩放
    private float letImageScale = 1f;
    private float rightImageScale = 1f;
    //边角
    private int btRadio = 10;
    private boolean showTopLine;
    private boolean showBottomLine;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mwidth = w;
        mheight = h;

    }

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        centerText = array.getString(R.styleable.MyView_centerText);
        centerTextColor = array.getColor(R.styleable.MyView_centerTextColor, defaultTextColor);
        centerTextSize = array.getDimensionPixelSize(R.styleable.MyView_centerTextSize, defaultTextSize);

        leftText = array.getString(R.styleable.MyView_leftText);
        leftTextColor = array.getColor(R.styleable.MyView_leftTextColor, defaultTextColor);
        leftTextSize = array.getDimensionPixelSize(R.styleable.MyView_leftTextSize, defaultTextSize);

        rightText = array.getString(R.styleable.MyView_rightText);
        rightTextColor = array.getColor(R.styleable.MyView_rightTextColor, defaultTextColor);
        rightTextSize = array.getDimensionPixelSize(R.styleable.MyView_rightTextSize, defaultTextSize);

        leftImage = array.getResourceId(R.styleable.MyView_leftImage, 0);
        rightImage = array.getResourceId(R.styleable.MyView_rightImage, 0);
        letImageScale = array.getFloat(R.styleable.MyView_leftsImageScale, 1f);
        rightImageScale = array.getFloat(R.styleable.MyView_rightImageScale, 1f);
        defaultLeftMargin = array.getDimensionPixelSize(R.styleable.MyView_leftMargin, defaultLeftMargin);
        defaultRightMargin = array.getDimensionPixelSize(R.styleable.MyView_rightMargin, defaultRightMargin);
        defaultTextDistance=array.getDimensionPixelSize(R.styleable.MyView_textdistance,defaultTextDistance);
        normalColor = array.getColor(R.styleable.MyView_normalColor, normalColor);
        pressColor = array.getColor(R.styleable.MyView_pressColor, pressColor);
        type = array.getString(R.styleable.MyView_types);
        showTopLine = array.getBoolean(R.styleable.MyView_showTopLine, false);
        showBottomLine = array.getBoolean(R.styleable.MyView_showBottomLine, true);
        array.recycle();
        init();
    }

    void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(normalColor);
        paint.setAntiAlias(true);
        leftText = leftText == null ? "" : leftText;
        centerText = centerText == null ? "" : centerText;
        rightText = rightText == null ? "" : rightText;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (type.equals("BUTTON")) {
            if (centerText == null) centerText = "";
            RectF rectF = new RectF(PxUtil.dip2px(getContext(), margindp), 0, mwidth - PxUtil.dip2px(getContext(), margindp), mheight);
            canvas.drawRoundRect(rectF, btRadio, btRadio, paint);
            paint.setColor(centerTextColor);
            paint.setTextSize(centerTextSize);
            Rect rect = new Rect();
            paint.getTextBounds(centerText, 0, centerText.length(), rect);
            canvas.drawText(centerText, mwidth / 2 - rect.width() / 2, mheight / 2 + rect.height() / 2, paint);
        } else if (type.equals("LAYOUT")) {
            RectF rectF = new RectF(PxUtil.dip2px(getContext(), margindp), 0, mwidth - PxUtil.dip2px(getContext(), margindp), mheight);
            canvas.drawRect(rectF, paint);
            paint.setColor(defaultLineColor);
            if (showTopLine) canvas.drawLine(marginTopLeftLine, 0, mwidth-marginTopLeftLine, 0, paint);
            if (showBottomLine)
                canvas.drawLine(marginBottomLeftLine, mheight - 1, mwidth-marginBottomLeftLine, mheight - 1, paint);
            if (leftImage != 0) {
                Bitmap leftBitmap = BitmapFactory.decodeResource(getResources(), leftImage);
                Matrix matrix = new Matrix();
                matrix.postScale(letImageScale, letImageScale);
                leftImageHeight = leftBitmap.getHeight();
                leftImageWidth=leftBitmap.getWidth();
                matrix.postTranslate(defaultLeftMargin, mheight / 2 - leftImageHeight * letImageScale / 2);
                canvas.drawBitmap(leftBitmap, matrix, paint);
                if (!leftBitmap.isRecycled()) {
                    leftBitmap.recycle();
                    leftBitmap = null;
                }
            }
            if (rightImage != 0) {
                Bitmap rightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.right_back);
                Matrix matrix = new Matrix();
                matrix.postScale(rightImageScale, rightImageScale);
                rightImageHeight = rightBitmap.getHeight();
                rightImageWidth=rightBitmap.getWidth();
                matrix.postTranslate(mwidth - rightImageWidth - defaultRightMargin, mheight / 2 - rightImageHeight * rightImageScale / 2);
                canvas.drawBitmap(rightBitmap, matrix, paint);
                if (!rightBitmap.isRecycled()) {
                    rightBitmap.recycle();
                    rightBitmap = null;
                }

            }
            //绘制左侧文字
            paint.setColor(leftTextColor);
            paint.setTextSize(leftTextSize);
            Rect recttext = new Rect();
            paint.getTextBounds(leftText, 0, leftText.length(), recttext);
            canvas.drawText(leftText, defaultLeftMargin + leftImageWidth * letImageScale + defaultTextDistance, mheight / 2 + recttext.height() / 2, paint);

            //绘制中间文字
            paint.setColor(centerTextColor);
            paint.setTextSize(centerTextSize);
            Rect centerTextRect = new Rect();
            paint.getTextBounds(centerText, 0, centerText.length(), centerTextRect);
            canvas.drawText(centerText, mwidth / 2 - centerTextRect.width() / 2, mheight / 2 + centerTextRect.height() / 2, paint);

            //绘制右侧文字
            paint.setColor(rightTextColor);
            paint.setTextSize(rightTextSize);
            Rect rightTextRect = new Rect();
            paint.getTextBounds(rightText, 0, rightText.length(), rightTextRect);
            canvas.drawText(rightText, mwidth - defaultRightMargin - rightImageWidth - defaultTextDistance - rightTextRect.width(), mheight / 2 + rightTextRect.height() / 2, paint);

        }


        paint.setColor(normalColor);
        System.gc();//通知垃圾回收尽快回收
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                paint.setColor(pressColor);
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                paint.setColor(pressColor);
                break;
            case MotionEvent.ACTION_UP:
                paint.setColor(normalColor);
                break;

        }
        invalidate();
        return super.onTouchEvent(event);
    }

    public void refresh() {
        this.invalidate();
    }

    public int getMarginTopLeftLine() {
        return marginTopLeftLine;
    }

    public void setMarginTopLeftLine(int marginTopLeftLine) {
        this.marginTopLeftLine = marginTopLeftLine;
        invalidate();
    }

    public int getMarginBottomLeftLine() {
        return marginBottomLeftLine;
    }

    public void setMarginBottomLeftLine(int marginBottomLeftLine) {
        this.marginBottomLeftLine = marginBottomLeftLine;
        invalidate();
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        invalidate();
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
        invalidate();
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
        invalidate();
    }

    public String getCenterText() {
        return centerText;
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
        invalidate();
    }

    public float getCenterTextSize() {
        return centerTextSize;
    }

    public void setCenterTextSize(float centerTextSize) {
        this.centerTextSize = centerTextSize;
        invalidate();
    }

    public int getCenterTextColor() {
        return centerTextColor;
    }

    public void setCenterTextColor(int centerTextColor) {
        this.centerTextColor = centerTextColor;
        invalidate();
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        invalidate();
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
        invalidate();
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        invalidate();
    }

    public int getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(int leftImage) {
        this.leftImage = leftImage;
        invalidate();
    }

    public int getRightImage() {
        return rightImage;
    }

    public void setRightImage(int rightImage) {
        this.rightImage = rightImage;
        invalidate();
    }

    public int getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        invalidate();
    }

    public int getPressColor() {
        return pressColor;
    }

    public void setPressColor(int pressColor) {
        this.pressColor = pressColor;
        invalidate();
    }

    public float getMargindp() {
        return margindp;
    }

    public void setMargindp(float margindp) {
        this.margindp = margindp;
        invalidate();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        invalidate();
    }

    public float getLetImageScale() {
        return letImageScale;
    }

    public void setLetImageScale(float letImageScale) {
        this.letImageScale = letImageScale;
        invalidate();
    }

    public float getRightImageScale() {
        return rightImageScale;
    }

    public void setRightImageScale(float rightImageScale) {
        this.rightImageScale = rightImageScale;
        invalidate();
    }

    public int getBtRadio() {
        return btRadio;
    }

    public void setBtRadio(int btRadio) {
        this.btRadio = btRadio;
        invalidate();
    }

    public boolean isShowTopLine() {
        return showTopLine;
    }

    public void setShowTopLine(boolean showTopLine) {
        this.showTopLine = showTopLine;
        invalidate();
    }

    public boolean isShowBottomLine() {
        return showBottomLine;
    }

    public void setShowBottomLine(boolean showBottomLine) {
        this.showBottomLine = showBottomLine;
        invalidate();
    }

}
