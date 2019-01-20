package com.example.group.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.util.Utils;

public class Video extends AppCompatActivity {
    private TextView tv_tab_button;
    private TextView tv_tab_button1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoactivity);
        Toolbar tl_head=findViewById(R.id.tl_head1);
        tl_head.setTitle("我要发布");
        setSupportActionBar(tl_head);
        tv_tab_button = findViewById(R.id.tv_tab_button);
        tv_tab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tab_button.setSelected(true);
            }
        });
        tv_tab_button1 = findViewById(R.id.tv_tab_button1);
        tv_tab_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tab_button1.setSelected(true);
            }
        });
    }
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        Utils.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }
}
