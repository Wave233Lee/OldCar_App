package com.example.group.bean;

import java.util.ArrayList;

import com.example.group.R;

public class SaleInfo {
    public int pic_id;
    public String title;
    public String desc;
    public boolean bPressed;
    public int id;
    private static int seq = 0;

    public SaleInfo(int pic_id, String title, String desc) {
        this.pic_id = pic_id;
        this.title = title;
        this.desc = desc;
        this.bPressed = false;
        this.id = this.seq;
        this.seq++;
    }

    private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
            , R.drawable.pic_04};
    private static String[] gridTitleArray = {"老爷车", "二手车", "平行进口", "新能源",};
    public static ArrayList<SaleInfo> getDefaultGrid() {
        ArrayList<SaleInfo> gridArray = new ArrayList<SaleInfo>();
        for (int i=0; i<gridImageArray.length; i++) {
            gridArray.add(new SaleInfo(gridImageArray[i], gridTitleArray[i], null));
        }
        return gridArray;
    }

}

