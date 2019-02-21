package com.example.group.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.group.R;
import com.example.group.adapter.DataAdapter;
import com.example.group.adapter.DataAdapter1;
import com.example.group.bean.GoodInfo1;
import com.example.group.bean.carshowInfo;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeCarShow extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<GoodInfo1> birdList;
    private DataAdapter adapter1;
    private String imageUrls[]=new String[9];
    ImageTextButton bt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldcar_home_carshow);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle("");
        setSupportActionBar(tl_head);
        tl_head.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getMessage();
        recyclerView = (RecyclerView) findViewById(R.id.rv_grid);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(manager);

        birdList = initData();
        adapter1 = new DataAdapter(getApplicationContext(), birdList);
        recyclerView.setAdapter(adapter1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));

        bt1 = (ImageTextButton)findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(HomeCarShow.this, CarActivity.class);
                startActivity(intent);
            }
        });

    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result;
            try {
                JSONObject jsonObject = new JSONObject(msg.obj.toString());
                int code = jsonObject.getInt("code");
                Log.d("a_log","code:  "+code);
                if (code == 0){
                    Log.d("a_log", "data：" + jsonObject.toString());
                    JSONArray data = jsonObject.getJSONArray("data");
                    Log.d("a_log", "传过来的车标LOGO：" + jsonObject.toString());
                    for(int i=0;i<data.length();i++){
                        JSONObject temp = data.getJSONObject(i);
                    }
                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存


                    for (int i=0;i<9;i++){
                        String test_url = data.getJSONObject(i).getJSONObject("picture").getString("path");
                        imageUrls[i]=test_url;
                        Log.d("a_log", "传过来的车标LOGO111：" + imageUrls[i]);
                    }

                    birdList=initData();
                    adapter1.setDatas(birdList);


                }else {
                    result = "获取失败";
                    Log.d("a_log", "连接失败");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    public void getMessage() {

        HashMap<String, String> params = new HashMap<>();
        //params.put("id","1");
        try {
            //构造完整URL
            String originAddress = "http://111.230.34.50:8080/oldcar/carBrand/getAll";
            String completedURL = HttpUtil.makeURL(originAddress, params);
            Log.d("a_log","that's completedUrl:"+completedURL);
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
                    Log.d("a_log","error:"+e.toString());
                    Message message = new Message();
                    message.obj = e.toString();
                    handler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private String imageUrls[]={
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
//            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg"
//    };

    private ArrayList<GoodInfo1> initData() {
        ArrayList<GoodInfo1> birds = new ArrayList<>();

        for (int i = 0; i < imageUrls.length; i++) {
            GoodInfo1 bird = new GoodInfo1();
            Log.d("a_log", "传过来的车标LOGO2：" + imageUrls[i]);
            bird.setImageUrl(imageUrls[i]);
            birds.add(bird);
        }
        return birds;
    }

}

