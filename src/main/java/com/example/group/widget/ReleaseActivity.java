package com.example.group.widget;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.adapter.GridAdapter;
import com.example.group.adapter.MyPagerAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.util.Utils;

import java.util.ArrayList;

public class ReleaseActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private TextView tv_four;
    private TextView tv_five;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离
    private  int three=0;
    private  int four=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle("我要发布");
        setSupportActionBar(tl_head);
        initViews();
    }


    private void initViews() {
        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_four = findViewById(R.id.tv_four);
        tv_five = findViewById(R.id.tv_five);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 5 - bmpWidth) / 4;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3
        three = one * 3;
        four = one * 4;

        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = LayoutInflater.from(this);
        listViews.add(mInflater.inflate(R.layout.view11, null, false));
        listViews.add(mInflater.inflate(R.layout.view12, null,false));
        listViews.add(mInflater.inflate(R.layout.view13, null, false));
        listViews.add(mInflater.inflate(R.layout.view14, null, false));
        listViews.add(mInflater.inflate(R.layout.view15, null, false));

        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);

        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        tv_four.setOnClickListener(this);
        tv_five.setOnClickListener(this);

        vpager_four.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                break;
            case R.id.tv_four:
                vpager_four.setCurrentItem(3);
                break;
            case R.id.tv_five:
                vpager_four.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(three, 0, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(four, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(three, one, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(four, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(three, two, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(four, two, 0, 0);
                }
                break;
            case 3:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, three, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, three, 0, 0);
                }else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, three, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(four, three, 0, 0);
                }
                break;
            case 4:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, four, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, four, 0, 0);
                }else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, four, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(three, four, 0, 0);
                }
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

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        Utils.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    private void initGrid() {
        RecyclerView rv_grid = (RecyclerView) findViewById(R.id.rv_grid);

        GridLayoutManager manager = new GridLayoutManager(this, 5);
        rv_grid.setLayoutManager(manager);

        GridAdapter adapter = new GridAdapter(this, GoodsInfo.getDefaultGrid());
        adapter.setOnItemClickListener(adapter);
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }
}