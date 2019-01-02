package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class View4Fragment extends Fragment implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    protected View mView;
    protected Context mContext;
    private ViewPager vpager_two;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view4_fragment,container,false);
        initViews();
        return mView;
    }

    private void initViews() {
        vpager_two = (ViewPager) mView.findViewById(R.id.vpager_two1);
        tv_one = (TextView) mView.findViewById(R.id.tv_one);
        tv_two = (TextView) mView.findViewById(R.id.tv_two);
        img_cursor = (ImageView) mView.findViewById(R.id.img_cursor);





        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.view5, null, false));
        listViews.add(mInflater.inflate(R.layout.view6, null, false));
        vpager_two.setAdapter(new MyPagerAdapter(listViews));
        vpager_two.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);

        vpager_two.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_two.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_two.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                animation = new TranslateAnimation(one, 0, 0, 0);
                break;
            case 1:
                animation = new TranslateAnimation(offset, one, 0, 0);
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
}


