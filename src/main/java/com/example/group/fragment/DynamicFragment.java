package com.example.group.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class DynamicFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    private int mPosition;
    private int mImageId;
    private String mDesc;

    public static DynamicFragment newInstance(int position,int image_id,String desc){
        DynamicFragment fragment=new DynamicFragment();
        Bundle buddle=new Bundle();
        buddle.putInt("position",position);
        buddle.putInt("image_id",image_id);
        buddle.putString("desc",desc);
        fragment.setArguments(buddle);
        return fragment;
    }
    public View onCreate(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        mContext=getActivity();
        if(getArguments()!=null){
            mPosition=getArguments().getInt("position",0);
            mImageId=getArguments().getInt("image_id",0);
            mDesc=getArguments().getString("desc");
        }
        mView=inflater.inflate(R.layout.fragment_dynamic,container,false);
        ImageView iv_pic=mView.findViewById(R.id.iv_pic);
        TextView tv_desc=mView.findViewById(R.id.tv_desc);
        iv_pic.setImageResource(mImageId);
        tv_desc.setText(mDesc);
        return mView;
    }
}
