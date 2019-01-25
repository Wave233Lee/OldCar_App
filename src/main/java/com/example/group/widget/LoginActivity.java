package com.example.group.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.group.R;
import com.example.group.adapter.GridAdapter;
import com.example.group.bean.GoodsInfo;


public class LoginActivity extends AppCompatActivity  {
    private final static String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        initGrid();
    }

//    private void initGrid() {
//        RecyclerView rv_grid = (RecyclerView) findViewById(R.id.rv_grid);
//
//        GridLayoutManager manager = new GridLayoutManager(this, 5);
//        rv_grid.setLayoutManager(manager);
//
//        GridAdapter adapter = new GridAdapter(this, GoodsInfo.getDefaultGrid());
//        adapter.setOnItemClickListener(adapter);
//        adapter.setOnItemLongClickListener(adapter);
//        rv_grid.setAdapter(adapter);
//        rv_grid.setItemAnimator(new DefaultItemAnimator());
//        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
//    }
}

