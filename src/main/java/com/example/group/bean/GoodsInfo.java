package com.example.group.bean;

import java.util.ArrayList;

import com.example.group.R;

public class GoodsInfo {
	public int pic_id;
	public String title;
	public String desc;
	public boolean bPressed;
	public int id;
	private static int seq = 0;
	
	public GoodsInfo(int pic_id, String title, String desc) {
		this.pic_id = pic_id;
		this.title = title;
		this.desc = desc;
		this.bPressed = false;
		this.id = this.seq;
		this.seq++;
	}

	private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
			, R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07
			, R.drawable.pic_08, R.drawable.pic_09, R.drawable.pic_10};
	private static String[] gridTitleArray = {"老车展示", "老车租赁", "老车文化", "老车配件",
			"卖老车", "周边产品", "维修保养", "进口新车", "车友会", "金融服务"};
	public static ArrayList<GoodsInfo> getDefaultGrid() {
		ArrayList<GoodsInfo> gridArray = new ArrayList<GoodsInfo>();
		for (int i=0; i<gridImageArray.length; i++) {
			gridArray.add(new GoodsInfo(gridImageArray[i], gridTitleArray[i], null));
		}
		return gridArray;
	}

	private static int[] combineImageArray = {R.drawable.trip_01, R.drawable.trip_02, R.drawable.trip_03, R.drawable.trip_04};
	private static String[] combineTitleArray = {
			"老车新闻", "老车租赁", "老车配件", "老车修复"};
	public static ArrayList<GoodsInfo> getDefaultCombine() {
		ArrayList<GoodsInfo> combineArray = new ArrayList<GoodsInfo>();
		for (int i=0; i<combineImageArray.length; i++) {
			combineArray.add(new GoodsInfo(combineImageArray[i], combineTitleArray[i], null));
		}
		return combineArray;
	}

	private static int[] combine1ImageArray = {R.drawable.trip_01, R.drawable.trip_02, R.drawable.trip_03, R.drawable.trip_04,R.drawable.trip_03, R.drawable.trip_04};
	private static String[] combine1TitleArray = {
			"老车新闻", "老车租赁", "老车配件", "老车修复","老车配件", "老车修复"};
	public static ArrayList<GoodsInfo> getDefaultCombine1() {
		ArrayList<GoodsInfo> combine1Array = new ArrayList<GoodsInfo>();
		for (int i=0; i<combine1ImageArray.length; i++) {
			combine1Array.add(new GoodsInfo(combine1ImageArray[i], combine1TitleArray[i], null));
		}
		return combine1Array;
	}

}
