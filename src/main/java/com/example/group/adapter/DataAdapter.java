package com.example.group.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.group.R;
import com.example.group.bean.GoodInfo1;
import com.example.group.widget.CarActivity;
import com.example.group.widget.RecyclerExtras;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public Context mContext;
    public ArrayList<GoodInfo1> mBirdsList;
    //我们在适配器中传入两个参数——当前上下文+数据列表
    public DataAdapter(Context context,ArrayList<GoodInfo1> birdList){
        mContext = context;
        mBirdsList = birdList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
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
                Toast.makeText(mContext,"点击",Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mBirdsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout ll_item;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            //适配器构造时只会用到实体类的get方法，用以获取相应的属性
            imageView = (ImageView) itemView.findViewById(R.id.bird_image);
        }
    }

}

