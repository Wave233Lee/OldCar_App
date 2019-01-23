package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.widget.CarActivity;

public class StaticFragment extends Fragment implements View.OnClickListener {
    protected View mView;
    protected Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.fragment_static,container,false);
        ImageView iv_adv=mView.findViewById(R.id.iv_adv);
        iv_adv.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v){
        Intent intent=new Intent(mContext, CarActivity.class);
        startActivity(intent);
    }
}
