package com.stdu.edu.italk.application;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class UserData extends Application {
	String userName;  //学号
	String userPassword;  //密码
	List <String>schoolList;   //校友列表数据
	String IP;
	Socket s=null;
	String socketIP;
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		schoolList = new ArrayList<String>();
		schoolList.add("ss");
		IP="http://192.168.36.155:8080";
		socketIP="192.168.36.155";
	}
	
	public String getSocketIP() {
		return socketIP;
	}
	public void setSocketIP(String socketIP) {
		this.socketIP = socketIP;
	}
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public List getSchoolList() {
		return schoolList;
	}
	public void setSchoolList(List <String>schoolList) {
		this.schoolList = schoolList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
