package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.group.R;
import com.example.group.adapter.GridAdapterView1;
import com.example.group.adapter.PlanetAdapter1;
import com.example.group.adapter.ShowCarAdapter;
import com.example.group.bean.Cars;
import com.example.group.bean.GoodsInfoView1;
import com.example.group.bean.Planet;
import com.example.group.widget.CarActivity;
import com.example.group.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultView3Fragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    protected Context mContext;
    private ListView lv_planet;
    private ArrayList<Cars> carList;
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.searchresultview3fragment,container,false);
        carList = Cars.getDefaultList();
       ShowCarAdapter adapter = new ShowCarAdapter(this.mContext, R.layout.item_list, carList, Color.WHITE);
        lv_planet = (ListView) mView.findViewById(R.id.lv_planet);
        lv_planet.setAdapter(adapter);
        lv_planet.setOnItemClickListener(adapter);
        lv_planet.setOnItemLongClickListener(adapter);
        return mView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this.mContext,CarActivity.class);
        mContext.startActivity(intent);
    }
}

