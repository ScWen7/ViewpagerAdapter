package com.xxh.viewpageradapter;

import android.animation.FloatEvaluator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/6/19.
 * 作用：
 */

public class CustomTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9F;
    private static final float MIN_ALPHA = 0.6F;

    public CustomTransformer() {
    }

    public void transformPage(View page, float position) {
        if (position < -1.0F) {
            page.setScaleY(0.9F);
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 1.0F) {
//            float scale = Math.max(0.9F, 1.0F - Math.abs(position));
//            page.setScaleY(scale);
//            float ALPHA = Math.max(MIN_ALPHA, 1.0F - Math.abs(position));
//            page.setAlpha(ALPHA);

            if (position < 0) //[0，-1]
            {
                Log.e("TAG", "position:" + position);
                float factor = new FloatEvaluator().evaluate(position / (-1F), 1f, MIN_SCALE);
                page.setScaleY(factor);
            } else//[1，0]
            {
                float faction = 1 - position / (1F);
                float factor = new FloatEvaluator().evaluate(faction, MIN_SCALE, 1f);
                page.setScaleY(factor);
            }

        } else {
            page.setScaleY(0.9F);
            page.setAlpha(MIN_ALPHA);
        }

    }
}
