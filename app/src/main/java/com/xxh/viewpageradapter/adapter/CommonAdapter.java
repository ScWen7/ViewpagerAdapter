package com.xxh.viewpageradapter.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/6/19.
 * 作用：
 */

public abstract class CommonAdapter<T> extends PagerAdapter {

    protected Context mContext;
    private List<T> mDatas;

    private int mItemViewId;

    public CommonAdapter(Context context, List<T> datas, int itemViewId) {
        mContext = context;
        mDatas = datas;
        mItemViewId = itemViewId;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CommonViewHolder commonViewHolder = getViewHoler(position, container);
        convert(commonViewHolder, getItem(position), position);
        container.addView(commonViewHolder.getConvertView());
        return commonViewHolder.getConvertView();

    }

    private T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 子类重写次方法进行数据的绑定
     *
     * @param commonViewHolder 获取到的ViewHolder
     * @param item             数据bean
     */
    protected abstract void convert(CommonViewHolder commonViewHolder, T item, int position);


    private CommonViewHolder getViewHoler(int position, ViewGroup parent) {
        return CommonViewHolder.get(mContext, parent, mItemViewId, position);
    }


}
