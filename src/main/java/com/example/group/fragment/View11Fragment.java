package com.example.group.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group.R;
import com.example.group.adapter.GridAdapterView2;
import com.example.group.bean.GoodsInfoView2;
import com.example.group.widget.CarActivity;
import com.example.group.widget.SpacesItemDecoration;


public class View11Fragment extends Fragment implements AdapterView.OnItemClickListener {
    protected View mView;
    protected Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mContext=getActivity();
        mView=inflater.inflate(R.layout.view11fragment,container,false);

        ArrayAdapter<String> starAdapter1 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray1);
        starAdapter1.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp1 = (Spinner) mView.findViewById(R.id.sp_dialog1);
        sp1.setPrompt("省");
        sp1.setAdapter(starAdapter1);
        sp1.setSelection(0);
        sp1.setOnItemSelectedListener(new View11Fragment.MySelectedListener1());

        ArrayAdapter<String> starAdapter2 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray2);
        starAdapter2.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp2 = (Spinner) mView.findViewById(R.id.sp_dialog2);
        sp2.setPrompt("市");
        sp2.setAdapter(starAdapter2);
        sp2.setSelection(0);
        sp2.setOnItemSelectedListener(new View11Fragment.MySelectedListener2());

        ArrayAdapter<String> starAdapter3 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray3);
        starAdapter3.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp3 = (Spinner) mView.findViewById(R.id.sp_dialog3);
        sp3.setPrompt("区");
        sp3.setAdapter(starAdapter3);
        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new View11Fragment.MySelectedListener3());

        ArrayAdapter<String> starAdapter4 = new ArrayAdapter<String>(mContext,R.layout.item_select, starArray4);
        starAdapter4.setDropDownViewResource(R.layout.item_drowdown);
        Spinner sp4 = (Spinner) mView.findViewById(R.id.sp_dialog4);
        sp4.setPrompt("路");
        sp4.setAdapter(starAdapter4);
        sp4.setSelection(0);
        sp4.setOnItemSelectedListener(new View11Fragment.MySelectedListener4());

        initGrid();
        return mView;
    }

    private void initGrid() {
        RecyclerView rv_grid = (RecyclerView) mView.findViewById(R.id.rv_grid);

        GridLayoutManager manager = new GridLayoutManager(this.mContext, 5);
        rv_grid.setLayoutManager(manager);

        GridAdapterView2 adapter = new GridAdapterView2(this.mContext, GoodsInfoView2.getDefaultGrid());
        adapter.setOnItemClickListener(adapter);
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this.mContext,CarActivity.class);
        mContext.startActivity(intent);
    }

    private String[] starArray1 = {"北京", "上海", "湖南", "深圳", "云南", "广州"};
    class MySelectedListener1 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray1[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray2 = {"长沙", "怀化"};
    class MySelectedListener2 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray2[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray3 = {"一区","二区","三区"};
    class MySelectedListener3 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray3[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private String[] starArray4 = {"一路", "二路"};
    class MySelectedListener4 implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(mContext, "您选择的是"+starArray4[arg2], Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}

