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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.adapter.CombineAdapter;
import com.example.group.adapter.GridAdapter;
import com.example.group.bean.GoodsInfo;
import com.example.group.util.Utils;
import com.example.group.widget.ArticleActivity;
import com.example.group.widget.BuyActivity;
import com.example.group.widget.DetailActivity;
import com.example.group.widget.LoginActivity;
import com.example.group.widget.SpacesItemDecoration;

public class AddBtnActivity extends AppCompatActivity  {
    private final static String TAG = "AddBtnActivity";
    private TextView tv_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        tv_join = (TextView)findViewById(R.id.tv_join);
        tv_join.setOnClickListener(new AddBtnActivity.MyOnClickListener());
    }
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.tv_join) {
                Intent intent = new Intent(AddBtnActivity.this, LoginActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(AddBtnActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        }
    }

}