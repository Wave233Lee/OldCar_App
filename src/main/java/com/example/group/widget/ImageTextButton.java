package com.example.group.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.group.OldcarMyselfActivity;
import com.example.group.R;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;

import org.json.JSONObject;

import java.util.HashMap;

public class ImageTextButton extends RelativeLayout {

    private ImageView imgView1;
    private ImageView imgView2;
    private TextView  textView1;
    private TextView  textView2;

    public ImageTextButton(Context context) {
        super(context,null);
    }

    public ImageTextButton(Context context,AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.img_text_bt, this,true);

        getMessage();
        this.imgView1 = (ImageView)findViewById(R.id.image1);
        this.imgView2 = (ImageView)findViewById(R.id.image2);
        this.textView1 = (TextView)findViewById(R.id.text1);
        this.textView2 = (TextView)findViewById(R.id.text2);
        Glide.with(imgView1).load("http://111.230.34.50:8080/oldcar/img/car/Audi_A3.jpg").into(imgView1);
        Glide.with(imgView2).load("http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg").into(imgView2);

        this.setClickable(true);
        this.setFocusable(true);
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
                    JSONObject data = jsonObject.getJSONObject("data");

                    //更新UI
                    textView1.setText(data.getString("name"));
                    textView2.setText(data.getString("years"));
                    String avatar_url = data.getJSONObject("picture").getString("path");
                    Log.d("a_log","传过来的"+avatar_url);
                    //设置图片圆角角度
                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存

                    Glide.with(imgView1).load(avatar_url).apply(mRequestOptions).into(imgView1);


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
            String originAddress = "http://111.230.34.50:8080/oldcar/car/getAll";
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
