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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;
import com.example.group.bean.Cars;
import com.example.group.widget.CarActivity;
import com.example.group.widget.Text;

import java.util.ArrayList;

public class ShowCarAdapter extends BaseAdapter implements OnItemClickListener,
        OnItemLongClickListener {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mLayoutId;
    private ArrayList<Cars> mCarList;
    private int mBackground;

    public ShowCarAdapter(Context context, int layout_id, ArrayList<Cars> car_list, int background) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mLayoutId = layout_id;
        mCarList = car_list;
        mBackground = background;
    }

    @Override
    public int getCount() {
        return mCarList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mCarList.get(arg0);
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Cars car = mCarList.get(position);
        holder.ll_item.setBackgroundColor(mBackground);
        holder.iv_icon.setImageResource(car.image);
        holder.tv_name.setText(car.name);
        holder.tv_desc.setText(car.desc);
        return convertView;
    }

    public final class ViewHolder {
        private LinearLayout ll_item;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.mContext, CarActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.mContext, CarActivity.class);
        mContext.startActivity(intent);
        return true;
    }
}

