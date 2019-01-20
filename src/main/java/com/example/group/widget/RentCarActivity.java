package com.example.group.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.group.R;

import java.util.ArrayList;
import java.util.List;


public class RentCarActivity extends AppCompatActivity  {
    private final static String TAG = "RentCarActivity";
    private Spinner spinner1, spinner2,spinner3,spinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentcar);

        spinner1 = (Spinner) findViewById(R.id.province);
        spinner2 = (Spinner) findViewById(R.id.city);
        spinner3 = (Spinner) findViewById(R.id.zone);
        spinner4 = (Spinner) findViewById(R.id.IndoorOrOutdoor);
        // 声明一个ArrayAdapter用于存放简单数据
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
        RentCarActivity.this, android.R.layout.simple_spinner_item, starArray1);
        // 把定义好的Adapter设定到spinner中
        adapter1.setDropDownViewResource(R.layout.item_drowdown);
        spinner1.setAdapter(adapter1);
        // 为第一个Spinner设定选中事件
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 在选中之后触发
                Toast.makeText(RentCarActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 这个一直没有触发，我也不知道什么时候被触发。
                //在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
            }
        });
        spinner2.setAdapter(adapter1);
        spinner3.setAdapter(adapter1);
        spinner4.setAdapter(adapter1);


    }
    private String[] starArray1 = {"品牌","奔驰", "宝马", "奥迪", "法拉利", "福特"};
    private String[] starArray2 = {"品牌","奔驰", "宝马", "奥迪", "法拉利", "福特"};
    private String[] starArray3 = {"品牌","奔驰", "宝马", "奥迪", "法拉利", "福特"};
    private String[] starArray4 = {"品牌","奔驰", "宝马", "奥迪", "法拉利", "福特"};
    private List<String> getData() {
       // 数据源
     List<String> dataList = new ArrayList<String>();
     dataList.add("北京");
     dataList.add("上海");
     dataList.add("南京");
     dataList.add("宜昌");
     return dataList;
     }
}

