package com.example.group.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.group.R;
import com.example.group.adapter.DataAdapter;
import com.example.group.adapter.DataAdapter1;
import com.example.group.bean.carshowInfo;

import java.util.ArrayList;

public class HomeCarShow extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<carshowInfo> birdList;
    private DataAdapter1 adapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldcar_home_carshow);

        recyclerView = (RecyclerView) findViewById(R.id.recycley_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);

        birdList = initData();
        adapter1 = new DataAdapter1(getApplicationContext(), birdList);
        recyclerView.setAdapter(adapter1);

    }
    private final String names[] = {
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Marshmallow",
            "Marshmallow",
            "Marshmallow"
    };
    private final String imageUrls[] = {
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg",
            "http://111.230.34.50:8080/oldcar/img/carBrand/Audi.jpg"

    };

    private ArrayList<carshowInfo> initData() {
        ArrayList<carshowInfo> birds = new ArrayList<>();

        for (int i = 0; i < names.length; ++i) {
            carshowInfo bird = new carshowInfo();
            bird.setImageUrl(imageUrls[i]);
            bird.setName(names[i]);
            birds.add(bird);
        }
        return birds;
    }
}

