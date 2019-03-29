package com.example.group.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.bean.Cars;
import com.example.group.bean.Club;
import com.example.group.widget.CarActivity;
import com.example.group.widget.ChatActivity;
import com.example.group.widget.LoginActivity;
import com.example.group.widget.Text;

import java.util.ArrayList;

public class ClubAdapter extends BaseAdapter implements View.OnClickListener,
        View.OnLongClickListener {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mLayoutId;
    private ArrayList<Club> mClubList;
    private int mBackground;

    public ClubAdapter(Context context, int layout_id, ArrayList<Club> club_list, int background) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mLayoutId = layout_id;
        mClubList = club_list;
        mBackground = background;
    }

    @Override
    public int getCount() {
        return mClubList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mClubList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(mLayoutId, null);
            holder.ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
            holder.mButton = (Button) convertView.findViewById(R.id.item_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Club club = mClubList.get(position);
        holder.ll_item.setBackgroundColor(mBackground);
        holder.iv_icon.setImageResource(club.image);
        holder.tv_name.setText(club.name);
        holder.tv_desc.setText(club.desc);
        holder.iv_icon.setOnClickListener(this);
        holder.tv_name.setOnClickListener(this);
        holder.tv_desc.setOnClickListener(this);
        holder.mButton.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View view) {
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

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public final class ViewHolder {
        private LinearLayout ll_item;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
        public Button mButton;
    }


}

