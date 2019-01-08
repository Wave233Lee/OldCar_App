package com.example.group.fragment;

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
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.util.HttpCallbackListener;
import com.example.group.util.HttpUtil;
import com.example.group.widget.CarActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class View2Fragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    protected Context mContext;
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view2_fragment,container,false);

        ArrayAdapter<String> starAdapter1 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray1);
        starAdapter1.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp1 = (Spinner) mView.findViewById(R.id.sp_dialog1);
        sp1.setPrompt("省");
        sp1.setAdapter(starAdapter1);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new MySelectedListener1());

        ArrayAdapter<String> starAdapter2 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray2);
        starAdapter2.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp2 = (Spinner) mView.findViewById(R.id.sp_dialog2);
        sp2.setPrompt("市");
        sp2.setAdapter(starAdapter2);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new MySelectedListener2());

        ArrayAdapter<String> starAdapter3 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray3);
        starAdapter3.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp3 = (Spinner) mView.findViewById(R.id.sp_dialog3);
        sp3.setPrompt("区");
        sp3.setAdapter(starAdapter3);
        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new MySelectedListener3());

        ArrayAdapter<String> starAdapter4 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray4);
        starAdapter4.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp4 = (Spinner) mView.findViewById(R.id.sp_dialog4);
        sp4.setPrompt("室内外");
        sp4.setAdapter(starAdapter4);
        sp4.setSelection(0);
        sp4.setOnItemSelectedListener(new MySelectedListener4());

        ArrayAdapter<String> starAdapter5 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray5);
        starAdapter5.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp5 = (Spinner) mView.findViewById(R.id.dialog5);
        sp5.setPrompt("品牌");
        sp5.setAdapter(starAdapter5);
        sp5.setSelection(0);
        sp5.setOnItemSelectedListener(new MySelectedListener5());

        ArrayAdapter<String> starAdapter6 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray6);
        starAdapter6.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp6 = (Spinner) mView.findViewById(R.id.sp_time1);
        sp6.setPrompt("年");
        sp6.setAdapter(starAdapter6);
        sp6.setSelection(0);
        sp6.setOnItemSelectedListener(new MySelectedListener6());

        ArrayAdapter<String> starAdapter7 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray7);
        starAdapter7.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp7 = (Spinner) mView.findViewById(R.id.sp_time2);
        sp7.setPrompt("月");
        sp7.setAdapter(starAdapter7);
        sp7.setSelection(0);
        sp7.setOnItemSelectedListener(new MySelectedListener7());

        ArrayAdapter<String> starAdapter8 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray8);
        starAdapter8.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp8 = (Spinner) mView.findViewById(R.id.sp_time3);
        sp8.setPrompt("日");
        sp8.setAdapter(starAdapter8);
        sp8.setSelection(0);
        sp8.setOnItemSelectedListener(new MySelectedListener8());

        ArrayAdapter<String> starAdapter9 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray6);
        starAdapter6.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp9 = (Spinner) mView.findViewById(R.id.sp_time4);
        sp9.setPrompt("年");
        sp9.setAdapter(starAdapter6);
        sp9.setSelection(0);
        sp9.setOnItemSelectedListener(new MySelectedListener6());

        ArrayAdapter<String> starAdapter10 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray7);
        starAdapter7.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp10 = (Spinner) mView.findViewById(R.id.sp_time5);
        sp10.setPrompt("月");
        sp10.setAdapter(starAdapter7);
        sp10.setSelection(0);
        sp10.setOnItemSelectedListener(new MySelectedListener7());

        ArrayAdapter<String> starAdapter11 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray8);
        starAdapter8.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp11 = (Spinner) mView.findViewById(R.id.sp_time6);
        sp11.setPrompt("日");
        sp11.setAdapter(starAdapter8);
        sp11.setSelection(0);
        sp11.setOnItemSelectedListener(new MySelectedListener8());

        gridView=(GridView) mView.findViewById(R.id.gridView);
        dataList=new ArrayList<Map<String,Object>>();
        adapter=new SimpleAdapter(this.mContext, getData(), R.layout.item_view1, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return mView;
    }

    private List<Map<String, Object>> getData() {

        int[] drawable = { R.drawable.trip_01,R.drawable.trip_01,R.drawable.trip_01,R.drawable.trip_01,R.drawable.trip_01,R.drawable.trip_01,};
        String[] iconName = {  "车型图", "车型图", "车型图", "车型图", "车型图", "车型图" };
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

    private String[] starArray1 = {"北京", "上海", "湖南", "深圳", "云南", "广州"};
    class MySelectedListener1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray1[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray2 = {"长沙", "怀化"};
    class MySelectedListener2 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray2[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray3 = {"一区","二区","三区"};
    class MySelectedListener3 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray3[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray4 = {"室内", "室外"};
    class MySelectedListener4 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray4[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray5 = {"红旗", "奔驰","丰田","林肯"};
    class MySelectedListener5 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray5[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray6 = {"2018", "2019","2020","2021"};
    class MySelectedListener6 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是" + starArray6[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray7 = {"1", "2","3","4","5","6","7","8","9","10","11","12"};
    class MySelectedListener7 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray7[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray8 = {"1", "2","3","4","5","6","7","8","9","10",
            "11","12","13", "14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};
    class MySelectedListener8 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray8[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    public static Bitmap getImage(String path){
        Log.d("brand_log","inqqqq");

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

    public Bitmap returnBitMap(String url) {
        Log.d("brand_log","in");
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();

            Log.d("brand_log",is.toString());
            Log.d("brand_log","suces");

            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


//    public void getMessage(View v) {
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id","1");
//
//        init();
//        try {
//            //构造完整URL
//            String originAddress = "http://111.230.34.50:8080/oldcar/user/getById";
//            String completedURL = HttpUtil.makeURL(originAddress, params);
//            Log.d("a_log","that's completedUrl:"+completedURL);
//            //发送请求
//            HttpUtil.sendGETRequest(completedURL, new HttpCallbackListener() {
//                @Override
//                public void OnFinish(String response) {
//                    Message message = new Message();
//                    message.obj = response;
//                    handler.sendMessage(message);
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Log.d("a_log","error:"+e.toString());
//                    Message message = new Message();
//                    message.obj = e.toString();
//                    handler.sendMessage(message);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}



