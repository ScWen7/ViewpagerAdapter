package com.xxh.viewpageradapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xxh.viewpageradapter.adapter.CommonAdapter;
import com.xxh.viewpageradapter.adapter.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager view_pager;
    private ArrayList<ImageView> imageViews;
    int[] imgs = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8, R.mipmap.image1
    };
    List<Integer> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.setOffscreenPageLimit(3);

        initData();
        initViewpager();
    }

    private void initViewpager() {

        //设置Page间间距
        view_pager.setPageMargin(20);
        view_pager.setPageTransformer(false, new CustomTransformer());
        view_pager.setAdapter(new CommonAdapter<Integer>(this, mList, R.layout.image) {
            @Override
            protected void convert(CommonViewHolder commonViewHolder, Integer item, int position) {
                commonViewHolder.setImageResource(R.id.image_icon, item);
            }
        });
        view_pager.setCurrentItem(1);

//        view_pager.setCurrentItem(imageViews.size() * 500 / 2);
    }

    private void initData() {
        for (int i = 0; i < imgs.length; i++) {
            mList.add(imgs[i]);
        }
    }
}
