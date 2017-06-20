package com.xxh.viewpageradapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 解晓辉 on 2017/6/20.
 * 作用：
 */

public class CircleIndictor extends View implements ViewPager.OnPageChangeListener {

    private Paint mCirclePaint;

    private int normalColor = Color.GRAY;

    private int selectColor = Color.GREEN;


    private int radius = DisplayUtils.dpToPx(5);

    private int space = DisplayUtils.dpToPx(10);

    private int count = 0;

    private List<PointF> mPointFs;

    private int selectPosition = 0;


    public CircleIndictor(Context context) {
        super(context);
        init();
    }

    public CircleIndictor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(normalColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = radius * 2 + space * (count - 1);
        int height = radius * 2;
        setMeasuredDimension(width, height);
        initPosition();
    }

    private void initPosition() {
        mPointFs = new ArrayList<>();
        float toX = 0f;
        for (int i = 0; i < count; i++) {
            PointF pointF = new PointF();
            if (i == 0) {
                toX = radius;
            } else {
                toX += radius * 2 + space;
            }
            pointF.set(toX, radius * 2);
            mPointFs.add(pointF);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int size = mPointFs.size();
        for (int i = 0; i < size; i++) {
            PointF pointF = mPointFs.get(i);
            if (i == selectPosition) {
                mCirclePaint.setColor(selectColor);
            } else {
                mCirclePaint.setColor(normalColor);
            }
            canvas.drawCircle(pointF.x, pointF.y, radius, mCirclePaint);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectPosition = position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setUpViewpager(ViewPager viewpager) {
        if (viewpager != null) {
            viewpager.addOnPageChangeListener(this);
            count = viewpager.getAdapter().getCount();
            selectPosition = viewpager.getCurrentItem();
            invalidate();
        }
    }
}
