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
import android.widget.GridView;
import android.widget.SimpleAdapter;

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
        initGrid();
//        gridView=(GridView) mView.findViewById(R.id.gridView);
//        dataList=new ArrayList<Map<String,Object>>();
//        adapter=new SimpleAdapter(this.mContext, getData(), R.layout.item_view1, new String[]{"pic","name"}, new int[]{R.id.pic,R.id.name});
//        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(this);
        return mView;
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

