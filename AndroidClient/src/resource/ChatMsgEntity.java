package com.stdu.edu.italk.resource;

/**
 * ������Ϣ����Դ
 * 
 * @author xianming 
 * 
 */
public class ChatMsgEntity {
	private String from;//˭�����ҵ�
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

	private String date;//��������
	private String message;//������Ϣ��
	private boolean isComMeg = true;// �Ƿ��Ƿ�������Ϣ
	private String to;//����˭


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
