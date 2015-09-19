package com.stdu.edu.italk.View;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.stdu.edu.italk.application.UserData;

public class SendToFriend extends AsyncTask<JSONObject,Void,String> {
	PrintWriter pout = null;  

	Context context;
	public SendToFriend(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected String doInBackground(JSONObject... params) {
		// 通过socket发送数据至服务器
		sendMessage(params[0].toString());
		return null;
	}
	
	//发送消息
	public void sendMessage(final String msg) {
			UserData data = (UserData) context.getApplicationContext();
			try {
				 pout = new PrintWriter(new BufferedWriter(    
                         new OutputStreamWriter(data.getS().getOutputStream(),"UTF-8")),true);
				 
//					PrintStream out = new PrintStream(data.getS().getOutputStream());
				 pout.print(msg+"\n");
				 pout.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}

}
