package com.example.group;

import java.util.ArrayList;
import java.util.List;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.example.group.adapter.CombineAdapter;
import com.example.group.adapter.GridAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.util.DisplayUtil;
import com.example.group.util.Utils;
import com.example.group.widget.BannerPager;
import com.example.group.widget.CarActivity;
import com.example.group.widget.SpacesItemDecoration;
import com.example.group.widget.BannerPager.BannerClickListener;
import com.sunfusheng.marqueeview.MarqueeView;

public class OldcarHomeActivity extends AppCompatActivity implements BannerClickListener, View.OnClickListener {
	private final static String TAG = "OldcarHomeActivity";
	private Button bt1;
	private Button bt2;
	private Button bt3;
	private Button bt4;
	private Button bt5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oldcar_home);
		Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
		tl_head.setTitle(" ");
		setSupportActionBar(tl_head);
		initBanner();
		initGrid();
		bt1=findViewById(R.id.bt1);
		bt2=findViewById(R.id.bt2);
		bt3=findViewById(R.id.bt3);
		bt4=findViewById(R.id.bt4);
		bt5=findViewById(R.id.bt5);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
		List<String> info = new ArrayList<>();
		info.add("老车咨询  珠海虎头奔三周年颁奖盛典");
		info.add("老车咨询  珠海虎头奔三周年颁奖盛典");
		info.add("老车咨询  珠海虎头奔三周年颁奖盛典");
		info.add("老车咨询  珠海虎头奔三周年颁奖盛典");
		info.add("老车咨询  珠海虎头奔三周年颁奖盛典");
		marqueeView.startWithList(info);
		marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
		marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
			@Override
			public void onItemClick(int position, TextView textView) {

			}
		});
		initCombine();
		initCombine1();
	}
<<<<<<< HEAD

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(this, CarActivity.class);
		startActivity(intent);
	}

=======
	private void initSearchView(Menu menu) {
		MenuItem menuItem = menu.findItem(R.id.menu_search);
		SearchView searchView = (SearchView) menuItem.getActionView();
	//	SearchView.setIconifiedByDefault(getIntent().getBooleanExtra("collapse",true));
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		ComponentName cn = new ComponentName(this, SearchResult1Activity.class);
//		Intent intent = new Intent();
		//设置部件
//		intent.setComponent(cn);
//		startActivity(intent);
		SearchableInfo info = searchManager.getSearchableInfo(cn);
		if (info == null) {
			Log.d(TAG, "Fail to get SearchResultActvity.");
		}
		searchView.setSearchableInfo(info);
		// 新旧SearchView公用代码结束

		sac_key = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
		sac_key.setTextColor(Color.WHITE);
		sac_key.setHintTextColor(Color.WHITE);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				Log.d(TAG, "onQueryTextSubmit = " + query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return true;
			}
		});

		Bundle bundle = new Bundle();
		bundle.putString("hi", "hello");
		searchView.setAppSearchData(bundle);
	}

	private SearchView.SearchAutoComplete sac_key;
	private String[] hintArray = { "iphone", "iphone7s", "iphone7", "iphone7 plus", "iphone6s", "iphone6", "iphone6 plus"};

	private void doSearch(String text) {
		if (text.indexOf("i") == 0) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					R.layout.search_list_auto, hintArray);
			sac_key.setAdapter(adapter);
			sac_key.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					TextView tv_item = (TextView) view;
					sac_key.setText(tv_item.getText());
				}
			});
		}
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// 显示菜单项左侧的图标
		Utils.setOverflowIconVisible(featureId, menu);
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_search, menu);
		//对搜索框做初始化
		initSearchView(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
		} else if (id == R.id.menu_refresh) {
			//tv_desc.setText("当前刷新时间: "+Utils.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
			return true;
		} else if (id == R.id.menu_about) {
			Toast.makeText(this, "这个是工具栏的演示demo", Toast.LENGTH_LONG).show();
			return true;
		} else if (id == R.id.menu_quit) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
>>>>>>> 9e388d3
	private void initBanner() {
		BannerPager banner = (BannerPager) findViewById(R.id.banner_pager);
		LayoutParams params = (LayoutParams) banner.getLayoutParams();
		params.height = (int) (DisplayUtil.getSreenWidth(this) * 250f/ 640f);
		banner.setLayoutParams(params);
		
		ArrayList<Integer> bannerArray = new ArrayList<Integer>();
		bannerArray.add(Integer.valueOf(R.drawable.banner_1));
		bannerArray.add(Integer.valueOf(R.drawable.banner_2));
		bannerArray.add(Integer.valueOf(R.drawable.banner_3));
		bannerArray.add(Integer.valueOf(R.drawable.banner_4));
		bannerArray.add(Integer.valueOf(R.drawable.banner_5));
		banner.setImage(bannerArray);
		banner.setOnBannerListener(this);
		banner.start();
	}

	@Override
	public void onBannerClick(int position) {
		Intent intent=new Intent(this, CarActivity.class);
		startActivity(intent);
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

	private void initCombine() {
		RecyclerView rv_combine = (RecyclerView) findViewById(R.id.rv_combine);

		GridLayoutManager manager = new GridLayoutManager(this, 4);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
            	return 2;
            }
        });
		rv_combine.setLayoutManager(manager);

		CombineAdapter adapter = new CombineAdapter(this, GoodsInfo.getDefaultCombine());
		adapter.setOnItemClickListener(adapter);
		adapter.setOnItemLongClickListener(adapter);
		rv_combine.setAdapter(adapter);
		rv_combine.setItemAnimator(new DefaultItemAnimator());
		rv_combine.addItemDecoration(new SpacesItemDecoration(1));
	}

	private void initCombine1() {
		RecyclerView rv_combine = (RecyclerView) findViewById(R.id.rv_combine1);

		GridLayoutManager manager = new GridLayoutManager(this, 4);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return 2;
			}
		});
		rv_combine.setLayoutManager(manager);

		CombineAdapter adapter = new CombineAdapter(this, GoodsInfo.getDefaultCombine());
		adapter.setOnItemClickListener(adapter);
		adapter.setOnItemLongClickListener(adapter);
		rv_combine.setAdapter(adapter);
		rv_combine.setItemAnimator(new DefaultItemAnimator());
		rv_combine.addItemDecoration(new SpacesItemDecoration(1));
	}

<<<<<<< HEAD
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

=======
//	@Override
//	public boolean onMenuOpened(int featureId, Menu menu) {
//		// 显示菜单项左侧的图标
//		Utils.setOverflowIconVisible(featureId, menu);
//		return super.onMenuOpened(featureId, menu);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menu_home, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		if (id == android.R.id.home) {
//			finish();
//		} else if (id == R.id.menu_search) {
//			Intent intent = new Intent(this, SearchViewActivity.class);
//			intent.putExtra("collapse", false);
//			startActivity(intent);
//		} else if (id == R.id.menu_refresh) {
//			Toast.makeText(this, "当前刷新时间: "+
//					Utils.getNowDateTime("yyyy-MM-dd HH:mm:ss"), Toast.LENGTH_LONG).show();
//			return true;
//		} else if (id == R.id.menu_about) {
//			Toast.makeText(this, "这个是商城首页", Toast.LENGTH_LONG).show();
//			return true;
//		} else if (id == R.id.menu_quit) {
//			finish();
//		}
//		return super.onOptionsItemSelected(item);
//	}
>>>>>>> 9e388d3
}