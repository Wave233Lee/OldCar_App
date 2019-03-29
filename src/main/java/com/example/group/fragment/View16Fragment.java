package com.example.group.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.R;
import com.example.group.widget.CarActivity;
import com.example.group.widget.HomeCarShow;
import com.example.group.widget.ImageTextButton;

public class View16Fragment extends Fragment {
    protected View mView;
    protected Context mContext;
    ImageTextButton bt1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view16_fragment,container,false);
        bt1 = (ImageTextButton)mView.findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(mContext, CarActivity.class);
                startActivity(intent);
            }
        });
        return mView;
    }
}
