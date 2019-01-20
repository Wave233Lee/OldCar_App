package com.example.group.bean;

import com.example.group.R;

import java.util.ArrayList;

public class Cars {
    public int image;
    public String name;
    public String desc;

    public Cars() {
        this.image = 0;
        this.name = "";
        this.desc = "";
    }

    public Cars(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    private static int[] iconArray = {R.drawable.car1, R.drawable.car1, R.drawable.car1,
            R.drawable.car1, R.drawable.car1, R.drawable.car1};
    private static String[] nameArray = {"样车1", "样车2", "样车3", "样车4", "样车5", "样车6"};
    private static String[] descArray = {
            "品牌/名称/车型简介",
            "品牌/名称/车型简介",
            "品牌/名称/车型简介",
            "品牌/名称/车型简介",
            "品牌/名称/车型简介",
            "品牌/名称/车型简介"
    };
    public static ArrayList<Cars> getDefaultList() {
        ArrayList<Cars> carList = new ArrayList<Cars>();
        for (int i=0; i<iconArray.length; i++) {
           carList.add(new Cars(iconArray[i], nameArray[i], descArray[i]));
        }
        return carList;
    }
}

