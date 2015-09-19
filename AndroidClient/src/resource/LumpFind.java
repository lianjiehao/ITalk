package com.stdu.edu.italk.resource;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * 发现界面的数据源
 * @author xianming 2015-7-25
 *
 */
public class LumpFind {
	private int imageLeft;
	private String itemName;
	private int imageRight;
	private int type;
	public int getImageLeft() {
		return imageLeft;
	}
	public void setImageLeft(int imageLeft) {
		this.imageLeft = imageLeft;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getImageRight() {
		return imageRight;
	}
	public void setImageRight(int imageRight) {
		this.imageRight = imageRight;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public LumpFind(int imageLeft, String itemName, int imageRight, int type) {
		super();
		this.imageLeft = imageLeft;
		this.itemName = itemName;
		this.imageRight = imageRight;
		this.type = type;
	}


}
