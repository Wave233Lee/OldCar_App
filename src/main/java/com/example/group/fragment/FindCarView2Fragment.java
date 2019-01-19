package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.adapter.GridAdapterView1;
import com.example.group.bean.GoodsInfoView1;
import com.example.group.widget.CarActivity;
import com.example.group.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCarView2Fragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    protected Context mContext;
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.findcar_view2fragment,container,false);

        ArrayAdapter<String> starAdapter1 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray1);
        starAdapter1.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp1 = (Spinner) mView.findViewById(R.id.brand);
        sp1.setPrompt("品牌");
        sp1.setAdapter(starAdapter1);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new MySelectedListener1());

        ArrayAdapter<String> starAdapter2 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray2);
        starAdapter2.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp2 = (Spinner) mView.findViewById(R.id.price);
        sp2.setPrompt("价格");
        sp2.setAdapter(starAdapter2);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new MySelectedListener2());

        ArrayAdapter<String> starAdapter3 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray3);
        starAdapter3.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp3 = (Spinner) mView.findViewById(R.id.level);
        sp3.setPrompt("级别");
        sp3.setAdapter(starAdapter3);
        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new MySelectedListener3());

        ArrayAdapter<String> starAdapter4 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray4);
        starAdapter4.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp4 = (Spinner) mView.findViewById(R.id.equipment);
        sp4.setPrompt("配置");
        sp4.setAdapter(starAdapter4);
        sp4.setSelection(0);
        sp4.setOnItemSelectedListener(new MySelectedListener4());
        initGrid();
//        gridView=(GridView) mView.findViewById(R.id.gridView);
//        dataList=new ArrayList<Map<String,Object>>();
//        adapter=new SimpleAdapter(this.mContext, getData(), R.layout.item_view1, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
//        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(this);
        return mView;
    }

    private String[] starArray1 = {"品牌","奔驰", "宝马", "奥迪", "法拉利", "福特"};
    class MySelectedListener1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray1[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray2 = {"价格","3万", "8万", "10万", "15万", "20万", "30万"};
    class MySelectedListener2 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray2[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray3 = {"级别","高级", "大众"};
    class MySelectedListener3 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray3[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray4 = {"配置","发动机", "轮胎", "车身", "座椅", "空间"};
    class MySelectedListener4 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray4[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private void initGrid() {
        RecyclerView rv_grid = (RecyclerView) mView.findViewById(R.id.rv_grid);

        GridLayoutManager manager = new GridLayoutManager(this.mContext, 5);
        rv_grid.setLayoutManager(manager);

        GridAdapterView1 adapter = new GridAdapterView1(this.mContext, GoodsInfoView1.getDefaultGrid());
        adapter.setOnItemClickListener(adapter);
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }

    private List<Map<String, Object>> getData() {

        int[] drawable = { R.drawable.trip_01, R.drawable.trip_02,
                R.drawable.trip_03, R.drawable.trip_04,R.drawable.trip_01, R.drawable.trip_02,
                R.drawable.trip_03, R.drawable.trip_04,R.drawable.trip_01, R.drawable.trip_02,
                R.drawable.trip_03, R.drawable.trip_04,  };
        String[] iconName = { "车型图", "车型图", "车型图", "车型图", "车型图", "车型图", "车型图", "车型图",
                "车型图", "车型图", "车型图", "车型图" };
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
}

