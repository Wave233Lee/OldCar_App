package com.example.group.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.group.R;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;
import com.example.group.widget.CarActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private List<Map<String, ?>> data;

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

                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    public void getMessage(View v) {

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

