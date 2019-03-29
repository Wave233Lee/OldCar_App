package com.example.group.bean;

import java.util.ArrayList;

import com.example.group.R;

public class MyselfInfo {
    public int pic_id;
    public String title;
    public String desc;
    public boolean bPressed;
    public int id;
    private static int seq = 0;

    public MyselfInfo(int pic_id, String title, String desc) {
        this.pic_id = pic_id;
        this.title = title;
        this.desc = desc;
        this.bPressed = false;
        this.id = this.seq;
        this.seq++;
    }

    private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
            , R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07
            , R.drawable.pic_08};
    private static String[] gridTitleArray = {"2", "2", "0", "1",
            "5", "8", "0", "2"};
    public static ArrayList<MyselfInfo> getDefaultGrid() {
        ArrayList<MyselfInfo> gridArray = new ArrayList<MyselfInfo>();
        for (int i=0; i<gridImageArray.length; i++) {
            gridArray.add(new MyselfInfo(gridImageArray[i], gridTitleArray[i], null));
        }
        return gridArray;
    }

}

