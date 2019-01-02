package com.example.group;

import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.adapter.GridAdapter;
import com.example.group.adapter.MyPagerAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.util.Utils;
import com.example.group.widget.SpacesItemDecoration;
import com.example.group.widget.View1Activity;

import java.util.ArrayList;

public class OldcarCarActivity extends AppCompatActivity implements View.OnClickListener,
		ViewPager.OnPageChangeListener {

	private ViewPager vpager_four;
	private ImageView img_cursor;
	private TextView tv_one;
	private TextView tv_two;
	private TextView tv_three;
	private TextView tv_four;

	private ArrayList<View> listViews;
	private int offset = 0;//移动条图片的偏移量
	private int currIndex = 0;//当前页面的编号
	private int bmpWidth;// 移动条图片的长度
	private int one = 0; //移动条滑动一页的距离
	private int two = 0; //滑动条移动两页的距离
	private  int three=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oldcar_car);
		Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
		tl_head.setTitle("老爷车");
		setSupportActionBar(tl_head);
		initViews();
	}


	private void initViews() {
		vpager_four = (ViewPager) findViewById(R.id.vpager_four);
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		tv_four = findViewById(R.id.tv_four);
		img_cursor = (ImageView) findViewById(R.id.img_cursor);

		//下划线动画的相关设置：
		bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 4 - bmpWidth) / 3;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		img_cursor.setImageMatrix(matrix);// 设置动画初始位置
		//移动的距离
		one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
		two = one * 2;// 移动两页的偏移量,比如1直接跳3
		three = one * 3;

		//往ViewPager填充View，同时设置点击事件与页面切换事件
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = LayoutInflater.from(this);
		listViews.add(mInflater.inflate(R.layout.view1, null, false));
		listViews.add(mInflater.inflate(R.layout.view2, null,false));
		listViews.add(mInflater.inflate(R.layout.view3, null, false));
		listViews.add(mInflater.inflate(R.layout.view4, null, false));

		vpager_four.setAdapter(new MyPagerAdapter(listViews));
		vpager_four.setCurrentItem(0);

		tv_one.setOnClickListener(this);
		tv_two.setOnClickListener(this);
		tv_three.setOnClickListener(this);
		tv_four.setOnClickListener(this);

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
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
				}
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, three, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
				}else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		} else if (id == R.id.menu_search) {
			Intent intent = new Intent(this, SearchViewActivity.class);
			intent.putExtra("collapse", false);
			startActivity(intent);
		} else if (id == R.id.menu_refresh) {
			Toast.makeText(this, "当前刷新时间: "+
					Utils.getNowDateTime("yyyy-MM-dd HH:mm:ss"), Toast.LENGTH_LONG).show();
			return true;
		} else if (id == R.id.menu_about) {
			Toast.makeText(this, "这个是商城首页", Toast.LENGTH_LONG).show();
			return true;
		} else if (id == R.id.menu_quit) {
			finish();
		}
		return super.onOptionsItemSelected(item);
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