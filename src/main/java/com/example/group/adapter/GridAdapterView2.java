package com.example.group.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.bean.GoodsInfo;
import com.example.group.widget.RecyclerExtras.OnItemClickListener;
import com.example.group.widget.RecyclerExtras.OnItemLongClickListener;
import com.example.group.widget.View1Activity;

public class GridAdapterView2 extends RecyclerView.Adapter<ViewHolder> implements
        OnItemClickListener, OnItemLongClickListener {
    private final static String TAG = "GridAdapterView2";
    private Bundle mBundle = new Bundle();
    private LinearLayout ll_container;
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GoodsInfo> mGoodsArray;

    public GridAdapterView2(Context context, ArrayList<GoodsInfo> goodsArray) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mGoodsArray = goodsArray;
    }

    @Override
    public int getItemCount() {
        return mGoodsArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        View v = null;
        ViewHolder holder = null;
        v = mInflater.inflate(R.layout.item_grid, vg, false);
        holder = new ItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        ItemHolder holder = (ItemHolder) vh;
        holder.iv_pic.setImageResource(mGoodsArray.get(position).pic_id);
        holder.tv_title.setText(mGoodsArray.get(position).title);

        // 列表项的点击事件需要自己实现
        holder.ll_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.ll_item.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
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

    public class ItemHolder extends ViewHolder {
        public LinearLayout ll_item;
        public ImageView iv_pic;
        public TextView tv_title;

        public ItemHolder(View v) {
            super(v);
            ll_item = (LinearLayout) v.findViewById(R.id.ll_item);
            iv_pic = (ImageView) v.findViewById(R.id.iv_pic);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
        }

    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemLongClickListener mOnItemLongClickListener;
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this.mContext,View1Activity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        String desc = String.format("您长按了第%d项，栏目名称是%s", position + 1,
                mGoodsArray.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }


}

