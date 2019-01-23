package com.example.group.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.group.R;
import com.example.group.util.Utils;

public class HomeCarMaintenance extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle("");
        setSupportActionBar(tl_head);
        tl_head.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        Utils.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_maintenance, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            finish();
//        } else if (id == R.id.menu_redcard) {
//            Intent intent = new Intent(this, Activity_redcard.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
