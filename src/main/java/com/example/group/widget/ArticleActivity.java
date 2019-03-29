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


public class ArticleActivity extends AppCompatActivity  {
    private final static String TAG = "ArticleActivity";
    private LinearLayout sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        sample = (LinearLayout)findViewById(R.id.sample);
        sample.setOnClickListener(new ArticleActivity.MyOnClickListener());
//        initGrid();
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.sample) {
                Intent intent = new Intent(ArticleActivity.this, DetailActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(ArticleActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        }
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

