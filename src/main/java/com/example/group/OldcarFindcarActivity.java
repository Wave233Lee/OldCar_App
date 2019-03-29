package com.example.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.group.adapter.CombineAdapter;
import com.example.group.adapter.GridAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.util.Utils;
import com.example.group.widget.SpacesItemDecoration;

public class OldcarFindcarActivity extends AppCompatActivity  {
	private final static String TAG = "OldcarFindcarActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oldcar_findcar);
		Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
		tl_head.setTitle("老爷车");
		setSupportActionBar(tl_head);
		initCombine();
		initGrid();
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

		CombineAdapter adapter = new CombineAdapter(this, GoodsInfo.getDefaultCombine1());
		adapter.setOnItemClickListener(adapter);
		adapter.setOnItemLongClickListener(adapter);
		rv_combine.setAdapter(adapter);
		rv_combine.setItemAnimator(new DefaultItemAnimator());
		rv_combine.addItemDecoration(new SpacesItemDecoration(1));
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
}