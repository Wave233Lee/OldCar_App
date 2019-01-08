package com.example.group.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.group.R;
import com.example.group.adapter.PlanetAdapter;
import com.example.group.bean.Planet;

import java.util.ArrayList;

public class View7Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private ListView lv_planet;
    private ArrayList<Planet> planetList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view5_fragment,container,false);
        planetList = Planet.getDefaultList();
        PlanetAdapter adapter = new PlanetAdapter(this.mContext, R.layout.item_list, planetList, Color.WHITE);
        lv_planet = (ListView) mView.findViewById(R.id.lv_planet);
        lv_planet.setAdapter(adapter);
        lv_planet.setOnItemClickListener(adapter);
        lv_planet.setOnItemLongClickListener(adapter);
        return mView;
    }

}
