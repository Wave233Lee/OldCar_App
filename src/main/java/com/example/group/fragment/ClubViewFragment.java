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
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.adapter.ClubAdapter;
import com.example.group.adapter.GridAdapterView1;
import com.example.group.adapter.PlanetAdapter1;
import com.example.group.adapter.ShowCarAdapter;
import com.example.group.bean.Cars;
import com.example.group.bean.Club;
import com.example.group.bean.GoodsInfoView1;
import com.example.group.bean.Planet;
import com.example.group.widget.BuyActivity;
import com.example.group.widget.CarActivity;
import com.example.group.widget.CarClubActivity;
import com.example.group.widget.ChatActivity;
import com.example.group.widget.ClubActivity;
import com.example.group.widget.LoginActivity;
import com.example.group.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    private TextView join;
    protected Context mContext;
    private ListView lv_planet;
    private ArrayList<Club> clubList;
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.club_viewfragment,container,false);
        clubList = Club.getDefaultList();
       ClubAdapter adapter = new ClubAdapter(this.mContext, R.layout.club_list, clubList, Color.WHITE);
//          adapter.setOnInnerItemOnClickListener();
        lv_planet = (ListView) mView.findViewById(R.id.lv_planet);
        lv_planet.setAdapter(adapter);
        lv_planet.setOnItemClickListener(new ListView.OnItemClickListener(){
            private Context mContext;

            @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(view.getId()==R.id.item_btn){
                    Intent intent=new Intent(this.mContext,LoginActivity.class);
                    mContext.startActivity(intent);
                }else if(view.getId()==R.id.tv_desc){
                    Intent intent = new Intent(this.mContext, ChatActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

        return mView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId()==R.id.iv_icon){
            Intent intent=new Intent(this.mContext,ChatActivity.class);
            mContext.startActivity(intent);
        }else if(view.getId()==R.id.tv_name){
            Intent intent=new Intent(this.mContext,ChatActivity.class);
            mContext.startActivity(intent);
        }else if(view.getId()==R.id.tv_desc){
            Intent intent=new Intent(this.mContext,ChatActivity.class);
            mContext.startActivity(intent);
        }else if(view.getId()==R.id.item_btn){
            Intent intent=new Intent(this.mContext,LoginActivity.class);
            mContext.startActivity(intent);
        }



    }
}

