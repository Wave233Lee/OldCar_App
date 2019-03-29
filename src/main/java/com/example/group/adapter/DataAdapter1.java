package com.example.group.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.group.R;
import com.example.group.bean.carshowInfo;
import com.example.group.widget.CarActivity;

import java.util.ArrayList;

public class DataAdapter1 extends RecyclerView.Adapter<DataAdapter1.ViewHolder> {

    public Context mContext;
    public ArrayList<carshowInfo> mBirdsList;
    //我们在适配器中传入两个参数——当前上下文+数据列表
    public DataAdapter1(Context context,ArrayList<carshowInfo> birdList){
        mContext = context;
        mBirdsList = birdList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout1,parent,false);
        return new ViewHolder(view);
    }
    //使用Glide的地方——onBindViewHolder方法内部，这个方法适配器用于赋值数据
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mBirdsList.get(position).getImageUrl())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CarActivity.class);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mBirdsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            //适配器构造时只会用到实体类的get方法，用以获取相应的属性
            imageView = (ImageView) itemView.findViewById(R.id.bird_image);
            textView = (TextView) itemView.findViewById(R.id.bird_name);
        }

    }
}