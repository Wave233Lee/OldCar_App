package com.example.group.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.group.R;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class OriginalMaintenance extends AppCompatActivity {
    private ImageView image1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.original_maintenance);
        Toolbar tl_head = (Toolbar) findViewById(R.id.tl_head);
        tl_head.setTitle("");
        setSupportActionBar(tl_head);
        tl_head.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image1=findViewById(R.id.image1);
        getMessage();
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
                    String test_url = data.getJSONObject(0).getJSONObject("picture").getString("path");
                    Log.d("a_log", "传过来的车标LOGO：" + test_url);
                    Glide.with(image1)
                            .load(test_url)
                            .apply(mRequestOptions)
                            .into(image1);
                    Log.d("a_log", "loadImage: 链接");


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
//        params.put("id","1");
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
}
