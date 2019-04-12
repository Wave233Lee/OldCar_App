package com.example.group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.group.adapter.MyselfAdapter;
import com.example.group.bean.MyselfInfo;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;
import com.example.group.widget.Activity_redcard;
import com.example.group.widget.Activity_setup;
import com.example.group.widget.CollectionActivity;
import com.example.group.widget.FocusActivity;
import com.example.group.widget.HistoryActivity;
import com.example.group.widget.IntegrationActivity;
import com.example.group.widget.MyOrderActivity;
import com.example.group.widget.PraiseActivity;
import com.example.group.widget.SpacesItemDecoration;

import org.json.JSONObject;

import java.util.HashMap;

public class OldcarMyselfActivity extends AppCompatActivity implements OnClickListener {
    private ImageView image1;
    private TextView test1;
    private TextView test2;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button order;
    private Button waitpay;
    private Button waitsend;
    private Button waitreceive;
    private Button evaluation;
    private Button aftersale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldcar_myself);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle(" ");
        tl_head.setTitleTextColor(Color.RED);
        tl_head.setBackgroundResource(R.color.blue_light);
        setSupportActionBar(tl_head);
        tl_head.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image1 = findViewById(R.id.image1);
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        order = findViewById(R.id.order);
        waitpay = findViewById(R.id.waitpay);
        waitsend = findViewById(R.id.waitsend);
        waitreceive = findViewById(R.id.waitreceive);
        evaluation = findViewById(R.id.evaluation);
        aftersale = findViewById(R.id.aftersale);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        order.setOnClickListener(this);
        waitpay.setOnClickListener(this);
        waitreceive.setOnClickListener(this);
        evaluation.setOnClickListener(this);
        aftersale.setOnClickListener(this);
        getMessage();
        initGrid();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, FocusActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, CollectionActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button4) {
            Intent intent = new Intent(this, IntegrationActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button5) {
            Intent intent = new Intent(this, PraiseActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.order) {
            Intent intent = new Intent(this, MyOrderActivity.class);
            startActivity(intent);
        }
    }

    private void initGrid() {
        RecyclerView rv_grid = (RecyclerView) findViewById(R.id.rv_grid);

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rv_grid.setLayoutManager(manager);

        MyselfAdapter adapter = new MyselfAdapter(this, MyselfInfo.getDefaultGrid());
        adapter.setOnItemClickListener(adapter);
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_myself, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.menu_redcard) {
            Intent intent = new Intent(this, Activity_redcard.class);
            startActivity(intent);
        } else if (id == R.id.menu_setup) {
            Intent intent = new Intent(this, Activity_setup.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result;
            try {
                JSONObject jsonObject = new JSONObject(msg.obj.toString());
                int code = jsonObject.getInt("code");
                Log.d("a_log", "code:  " + code);
                if (code == 0) {
//                    result = "登陆成功";
//                    Toast.makeText(OldcarMyselfActivity.this, result, Toast.LENGTH_SHORT).show();
                    JSONObject data = jsonObject.getJSONObject("data");

                    //更新UI
                    test1.setText(data.getString("userName"));
                    String l = "level " + data.getString("level");
                    test2.setText(l);
                    String watch = data.getString("follow") + "\n" + "关注";
                    button1.setText(watch);
                    String collect = data.getString("collection") + "\n" + "收藏";
                    button2.setText(collect);
                    String foot = data.getString("history") + "\n" + "足迹";
                    button3.setText(foot);
                    String score = data.getString("integration") + "\n" + "积分";
                    button4.setText(score);
                    String givelike = data.getString("praise") + "\n" + "获赞";
                    button5.setText(givelike);
                    String avatar_url = data.getJSONObject("avatar").getString("path");
                    Log.d("a_log", "传过来的" + avatar_url);
                    //设置图片圆角角度
                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存

                    Glide.with(image1).load(avatar_url).apply(mRequestOptions).into(image1);


                } else {
                    result = "获取失败";
                    Log.d("a_log", "连接失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public void getMessage() {

        HashMap<String, String> params = new HashMap<>();
        params.put("id", "1");
        try {
            //构造完整URL
            String originAddress = "http://111.230.34.50:8080/oldcar/user/getById";
            String completedURL = HttpUtil.makeURL(originAddress, params);
            Log.d("a_log", "that's completedUrl:" + completedURL);
            //发送请求
            HttpUtil.sendGETRequest(completedURL, new HttpCallbackListener() {
                @Override
                public void OnFinish(String response) {
                    Message message = new Message();
                    message.obj = response;
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("a_log", "error:" + e.toString());
                    Message message = new Message();
                    message.obj = e.toString();
                    handler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
