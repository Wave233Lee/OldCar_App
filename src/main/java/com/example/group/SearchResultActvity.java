package com.example.group;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class SearchResultActvity extends AppCompatActivity implements View.OnClickListener,
		ViewPager.OnPageChangeListener{
	private static final String TAG = "SearchResultActvity";
	private TextView tv_search_result;
	private ArrayList<View> listViews;
	private int offset = 0;//移动条图片的偏移量
	private int currIndex = 0;//当前页面的编号
	private int bmpWidth;// 移动条图片的长度
	private int one = 0; //移动条滑动一页的距离
	private int two = 0; //滑动条移动两页的距离
	private  int three=0;
	private ViewPager vpager_four;
	private ImageView img_cursor;
	private TextView tv_one;
	private TextView tv_two;
	private TextView tv_three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		tv_search_result = (TextView) findViewById(R.id.tv_search_result);
		doSearchQuery(getIntent()); 
		
		Toolbar tl_result = (Toolbar) findViewById(R.id.tl_result);
//		tl_result.setBackgroundResource(R.color.blue_light);
//		tl_result.setLogo(R.drawable.ic_app);
//		tl_result.setTitle("搜索结果页");
		tl_result.setNavigationIcon(R.drawable.ic_back);
		setSupportActionBar(tl_result);
		initViews();
	}

	private void doSearchQuery(Intent intent) {
		if (intent == null) {
			return;
		} else {
			// 如果是通过ACTION_SEARCH来调用，即如果通过搜索调用
			if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
				// 获取额外信息
				Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
				String value = bundle.getString("hi");
				// 获取搜索内容
				String queryString = intent.getStringExtra(SearchManager.QUERY);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_null, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void initViews() {
		vpager_four = (ViewPager) findViewById(R.id.vpager_four);
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		img_cursor = (ImageView) findViewById(R.id.img_cursor);



		//往ViewPager填充View，同时设置点击事件与页面切换事件
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.view16, null, false));
		listViews.add(mInflater.inflate(R.layout.view4, null, false));
		listViews.add(mInflater.inflate(R.layout.view5, null, false));
		vpager_four.setAdapter(new MyPagerAdapter(listViews));
		vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

		tv_one.setOnClickListener(this);
		tv_two.setOnClickListener(this);
		tv_three.setOnClickListener(this);

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
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
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
}
