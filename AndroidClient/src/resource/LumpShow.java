package com.stdu.edu.italk.resource;

import android.widget.ImageView;

/**
 * 校友界面的数据源
 * @author xianming 2015-7-26
 *
 */
public class LumpShow {
	private String xueYuan;
	private String zhuangTai;
	private String juLi;
//	private ImageView touXiang;
//	private ImageView tag;

	public LumpShow(String xueYuan, String zhuangTai,String juLi) {
		this.xueYuan = xueYuan;
		this.zhuangTai = zhuangTai;
		this.juLi=juLi;
	}

	public String getXueYuan() {
		return xueYuan;
	}

	public String getZhuangTai() {
		return zhuangTai;
	}
	
	public String getJuLi() {
		return juLi;
	}
}
