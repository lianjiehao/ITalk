package com.stdu.edu.italk.resource;

import android.widget.ImageView;


/**
 * 聊天界面的数据源
 * @author xianming 2015-7-24
 *
 */
public class LumpChat {
	private String byName;
	private String sendTime;
	private String qinMi;
	private String message;
	// private ImageView touXiang;
	private int messageIconResource;

	public LumpChat(String byName, String sendTime, String qinMi,
			String message, int messageIconResource) {
		this.byName = byName;
		this.sendTime = sendTime;
		this.qinMi = qinMi;
		this.message = message;
		this.messageIconResource = messageIconResource;
	}

	public int getMessageIconResource() {
		return messageIconResource;
	}

	public void setMessageIconResource(int messageIconResource) {
		this.messageIconResource = messageIconResource;
	}

	public String getByName() {
		return byName;
	}

	public void setByName(String byName) {
		this.byName = byName;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getQinMi() {
		return qinMi;
	}

	public void setQinMi(String qinMi) {
		this.qinMi = qinMi;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
