package com.example.group.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.group.R;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;
import com.example.group.widget.CarActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Handler;


public class View9Fragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    protected Context mContext;
    private GridView gridView;
    private ImageView image1;
    private ImageView image2;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view9fragment,container,false);
        gridView=(GridView) mView.findViewById(R.id.gridView);
        image1=mView.findViewById(R.id.image1);
        image2=mView.findViewById(R.id.image2);
        getMessage(mView);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this.mContext, getData(), R.layout.item_view2, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return mView;
    }

    private List<Map<String, Object>> getData() {

        int[] drawable = { R.drawable.trip_01, R.drawable.trip_02,
                R.drawable.trip_03, R.drawable.trip_04,R.drawable.trip_01, R.drawable.trip_02,};
        String[] iconName = { "灯光", "轮子", "内饰", "刹车系统", "动力系统", "悬挂系统" };
        for (int i = 0; i < drawable.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", drawable[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this.mContext,CarActivity.class);
        mContext.startActivity(intent);
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
                    result = "登陆成功";
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                    JSONObject data = jsonObject.getJSONObject("data");

                    String avatar_url = data.getJSONObject("avatar").getString("path");
                    Log.d("a_log","传过来的"+avatar_url);
                        Glide.with(image1)
                                .load(avatar_url)
                                .into(image1);
                        Log.d("tag", "loadImage: 链接");


                }else {
                    result = "获取失败";
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    public static Bitmap getImage(String path){

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            Log.d("a_log","链接的URL"+conn.getURL().toString());
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                Log.d("a_log","实际链接的"+inputStream.toString());
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getMessage(View v) {

        HashMap<String, String> params = new HashMap<>();
        params.put("id","1");
        try {
            //构造完整URL
            String originAddress = "http://111.230.34.50:8080/oldcar/user/getById";
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

