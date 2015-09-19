package com.stdu.edu.italk.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.stdu.edu.italk.application.UserData;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


/**
 * 侦听消息服务
 * @author tangxianming
 *
 */
public class MsgListenerService extends Service {
	UserData application;
	Socket s;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application = (UserData) getApplication();
		s = application.getS();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					s = application.getS();
					while (true) {
						InputStreamReader ins = new InputStreamReader(
								s.getInputStream(),"UTF-8");
						BufferedReader bf = new BufferedReader(ins);
						String msg = bf.readLine();
						Log.d("MyService", "------"+msg+"--------");
						Intent intent = new Intent("italk.msgreflesh");
						intent.putExtra("jsonMsg", msg.toString());
						sendBroadcast(intent);
					}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();

	}

}
