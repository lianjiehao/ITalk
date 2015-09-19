package com.stdu.edu.italk.resource;

/**
 * 聊天消息数据源
 * 
 * @author xianming 
 * 
 */
public class ChatMsgEntity {
	private String from;//谁发给我的
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public boolean isComMeg() {
		return isComMeg;
	}

	public void setComMeg(boolean isComMeg) {
		this.isComMeg = isComMeg;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	private String date;//返回日期
	private String message;//返回信息
	private boolean isComMeg = true;// 是否是发来的消息
	private String to;//发给谁


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getMsgType() {
		return isComMeg;
	}

	public void setMsgType(boolean isComMsg) {
		isComMeg = isComMsg;
	}

	public ChatMsgEntity() {
	}

	public ChatMsgEntity(String from, String date, String message,
			boolean isComMeg, String to) {
		super();
		this.from = from;
		this.date = date;
		this.message = message;
		this.isComMeg = isComMeg;
		this.to = to;
	}


}
