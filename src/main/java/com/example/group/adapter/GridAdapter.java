package com.example.group.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
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
import com.example.group.widget.AccessoryActivity;
import com.example.group.widget.CarClubActivity;
import com.example.group.widget.FinancialActivity;
import com.example.group.widget.HomeCarCulture;
import com.example.group.widget.HomeCarMaintenance;
import com.example.group.widget.HomeCarShow;
import com.example.group.widget.ImportCarActivity;
import com.example.group.widget.PeripheryActivity;
import com.example.group.widget.RecyclerExtras.OnItemClickListener;
import com.example.group.widget.RecyclerExtras.OnItemLongClickListener;
import com.example.group.widget.ReleaseActivity;
import com.example.group.widget.RentCarActivity;

public class GridAdapter extends RecyclerView.Adapter<ViewHolder> implements
		OnItemClickListener, OnItemLongClickListener {
	private final static String TAG = "GridAdapter";
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<GoodsInfo> mGoodsArray;

	public GridAdapter(Context context, ArrayList<GoodsInfo> goodsArray) {
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

	public class ItemHolder extends RecyclerView.ViewHolder {
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
		switch (position){
			case 0:
				Intent intent1=new Intent(this.mContext, HomeCarShow.class);
				mContext.startActivity(intent1);
				break;
			case 1:
				Intent intent2=new Intent(this.mContext, RentCarActivity.class);
				mContext.startActivity(intent2);
				break;
			case 2:
				Intent intent3=new Intent(this.mContext, HomeCarCulture.class);
				mContext.startActivity(intent3);
				break;
			case 3:
				Intent intent4=new Intent(this.mContext, AccessoryActivity.class);
				mContext.startActivity(intent4);
				break;
			case 4:
				Intent intent5=new Intent(this.mContext, ReleaseActivity.class);
				mContext.startActivity(intent5);
				break;
			case 5:
				Intent intent6=new Intent(this.mContext, PeripheryActivity.class);
				mContext.startActivity(intent6);
				break;
            case 6:
                Intent intent7=new Intent(this.mContext, HomeCarMaintenance.class);
                mContext.startActivity(intent7);
                break;
			case 7:
				Intent intent8=new Intent(this.mContext, ImportCarActivity.class);
				mContext.startActivity(intent8);
				break;
			case 8:
				Intent intent9=new Intent(this.mContext, CarClubActivity.class);
				mContext.startActivity(intent9);
				break;
			case 9:
				Intent intent10=new Intent(this.mContext, FinancialActivity.class);
				mContext.startActivity(intent10);
				break;

		}

	}

	@Override
	public void onItemLongClick(View view, int position) {
		String desc = String.format("您长按了第%d项，栏目名称是%s", position + 1,
				mGoodsArray.get(position).title);
		Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
	}

}
