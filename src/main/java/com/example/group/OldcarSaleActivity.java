package com.example.group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.group.adapter.SaleAdapter;
import com.example.group.bean.MyselfInfo;
import com.example.group.bean.SaleInfo;
import com.example.group.util.Utils;
import com.example.group.widget.SpacesItemDecoration;

public class OldcarSaleActivity extends AppCompatActivity {
    private final static String TAG = "OldcarSaleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldcar_sale);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle(" ");
        setSupportActionBar(tl_head);
        initGrid();
    }

    private void initGrid() {
        RecyclerView rv_grid = (RecyclerView) findViewById(R.id.rv_grid);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rv_grid.setLayoutManager(manager);

        SaleAdapter adapter = new SaleAdapter(this, SaleInfo.getDefaultGrid());
        adapter.setOnItemClickListener(adapter);
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        Utils.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_oldcarsale, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
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
