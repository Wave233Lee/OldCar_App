package com.example.group.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.R;

public class StaticFragment extends Fragment implements View.OnClickListener {
    protected View mView;
    protected Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.fragment_static,container,false);
        TextView tv_adv=mView.findViewById(R.id.tv_adv);
        ImageView iv_adv=mView.findViewById(R.id.iv_adv);
        tv_adv.setOnClickListener(this);
        iv_adv.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.tv_adv){
            Toast.makeText(mContext,"您点击了广告文本",Toast.LENGTH_LONG).show();
        }else if(v.getId()==R.id.iv_adv){
            Toast.makeText(mContext,"您点击了广告图片",Toast.LENGTH_LONG).show();
        }
    }
}
