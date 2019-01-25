package com.example.group.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.adapter.PlanetAdapter;
import com.example.group.bean.Planet;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Introduce extends Fragment {
    protected View mView;
    protected Context mContext;
    private TextView text;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.introduce_fragment,container,false);
        text=mView.findViewById(R.id.text);
        getMessage();
        return mView;
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
                    JSONObject data = jsonObject.getJSONObject("data");

                    //更新UI
                    JSONObject data2=jsonObject.getJSONObject("data").getJSONObject("detail");
                    String description=data2.getString("description");
                    Log.d("a_log",description);
                    text.setText(description);




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
        params.put("id","1");
        try {
            //构造完整URL
            String originAddress = "http://111.230.34.50:8080/oldcar/car/getById";
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
