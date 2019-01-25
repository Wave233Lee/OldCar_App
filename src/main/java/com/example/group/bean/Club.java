package com.example.group.bean;

import com.example.group.R;

import java.util.ArrayList;

public class Club {
    public int image;
    public String name;
    public String desc;
    public String join;

    public Club() {
        this.image = 0;
        this.name = "";
        this.desc = "";
        this.join = "";
    }

    public Club(int image, String name, String desc,String join) {
        this.image = image;
        this.name = name;
        this.desc = desc;
        this.join = join;

    }

    private static int[] iconArray = {R.drawable.club1, R.drawable.club2, R.drawable.club3,
            R.drawable.club2, R.drawable.club1, R.drawable.club3};
    private static String[] nameArray = {"俱乐部1", "俱乐部2", "俱乐部3", "俱乐部4", "俱乐部5", "俱乐部6"};
    private static String[] descArray = {
            "俱乐部简介/人数",
            "俱乐部简介/人数",
            "俱乐部简介/人数",
            "俱乐部简介/人数",
            "俱乐部简介/人数",
            "俱乐部简介/人数"
    };
    private static String[] joinArray = {"加入", "加入", "加入", "加入", "加入", "加入"};
    public static ArrayList<Club> getDefaultList() {
        ArrayList<Club> clubList = new ArrayList<Club>();
        for (int i=0; i<iconArray.length; i++) {
            clubList.add(new Club(iconArray[i], nameArray[i], descArray[i],joinArray[i]));
        }
        return clubList;
    }
}

