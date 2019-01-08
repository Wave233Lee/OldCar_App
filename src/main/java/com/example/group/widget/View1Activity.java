package com.example.group.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.group.R;
import com.example.group.adapter.GridAdapter;
import com.example.group.bean.GoodsInfo;


public class View1Activity extends AppCompatActivity  {
    private final static String TAG = "View1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view1);
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
}

