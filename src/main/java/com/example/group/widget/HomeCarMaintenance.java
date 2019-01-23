package com.example.group.widget;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.group.R;
import com.example.group.util.Utils;

public class HomeCarMaintenance extends AppCompatActivity implements View.OnClickListener {
    private Button bt;
    private Button bt1;
    private Button bt2;
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
        bt=findViewById(R.id.bt);
        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        bt.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt){
            Intent intent=new Intent(this,AddCar.class);
            startActivity(intent);
        } else if(v.getId()==R.id.bt1){
            Intent intent=new Intent(this,CloseMaintenance.class);
            startActivity(intent);
        } else if(v.getId()==R.id.bt2) {
            Intent intent = new Intent(this, OriginalMaintenance.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // 显示菜单项左侧的图标
        Utils.setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

}
