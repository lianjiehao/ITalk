package com.stdu.edu.italk.login;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.register.RegisterBaseData;
import com.stdu.edu.italk.service.MsgListenerService;

public class ClientServerConnection_Login extends
		AsyncTask<String, Void, String> {

	Context context;
	private String realname = null;
	private String userid = null;
	private String result = null;

	public ClientServerConnection_Login(Context context) {
		this.context = context;
	}

	// ����webservice
	public String getremoteInfo(String loginName, String password) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// �����ռ�
		String nameSpace = "http://common/";
		// ���õķ�������
		String methodName = "AuthLogin";
		// EndPoint
		String endPoint = "http://jwservice.stdu.edu.cn/JWQueryWebService/jwqueryPort?wsdl";
		// SOAPAction
		String soapAction = "http://common/AuthLogin";

		// ָ��webService�������ռ�͵��õķ�����
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// ���������webService�ӿ���Ҫ�������������
		rpc.addProperty("arg0", loginName);
		rpc.addProperty("arg1", password);

		// ���ɵ���webservice������SOAP������Ϣ����ָ��SOAP�İ汾
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		// �����Ƿ���õ���dotNet������Webservice
		envelope.dotNet = false;
		envelope.setOutputSoapObject(rpc);

		System.out.println("rpc:" + rpc);
		System.out.println("enevlope:" + envelope);
		System.out.println("����2������������������ϣ����濪ʼ���÷���");

		try {
			HttpTransportSE transport = new HttpTransportSE(endPoint);

			System.out.println("����2��dsdasda");
			transport.call(null, envelope);

			System.out.println("allalla");
			SoapObject object = (SoapObject) envelope.bodyIn;
			System.out.println("����5������÷������ݳɹ�");
			// ��ȡ���ص�json����
			result = object.getProperty(0).toString();
//			// json����
//			JSONObject stu = new JSONObject(result);
//			userid = stu.getString("userid");
//			realname = stu.getString("realname");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����4��������webservice����ʧ��");
		}
		return result;
	}

	@Override
	protected String doInBackground(String... params) {
		// ����������ȫ�ֶ���
		UserData data = (UserData) context.getApplicationContext();
		data.setUserName(params[0]);
		data.setUserPassword(params[1]);
		
		
//		// webservice��֤�û��Ϸ���
//		String str = getremoteInfo(params[0], params[1]);
//		if (str.equals("anyType{}"))
//			return "false";
//		else
//			return "true";
		
		 //post����������������,���ز���ʱ����
		 HttpClient httpClient=new DefaultHttpClient();
		 String result=null;
		 String uri = data.getIP()+"/ITalkServer/Login.do";
		 HttpPost httpPost=new HttpPost(uri);
		 NameValuePair userNameParam=new BasicNameValuePair("LoginName",
		 params[0]);
		 NameValuePair passWordParam=new BasicNameValuePair("LoginPassword",
		 params[1]);
		 List <NameValuePair> list=new ArrayList<NameValuePair>();
		 list.add(passWordParam);
		 list.add(userNameParam);
		 try {
		 httpPost.setEntity(new UrlEncodedFormEntity(list));
		 //��ȡ��Ӧ����
		 HttpResponse response = httpClient.execute(httpPost);
		 result=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		
		 } catch (ClientProtocolException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 finally{
		 return result;
		 }
	}

	
	@Override
	protected void onPostExecute(String result) {
		if (result.equals("true")) {
			new Thread(new Runnable() {
				UserData data = (UserData) context.getApplicationContext();
				Socket s = null;
				@Override
				public void run() {
					try {
						// �������������
						s = new Socket(data.getSocketIP(), 8001);
						data.setS(s);
						// ������֤��Ϣ
						sendAuth(data.getUserName());
						// ������Ϣ����
						Intent startIntent = new Intent(context,
								MsgListenerService.class);
						context.startService(startIntent);
						Intent intent = new Intent(context,
								RegisterBaseData.class);
						context.startActivity(intent);
						((Activity) context).overridePendingTransition(
								R.anim.in_from_right, R.anim.out_from_left);

					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} 
		else if (result.equals("false")) {
			Toast.makeText(context, "��½ʧ��", Toast.LENGTH_SHORT).show();
		}
	}

	// ��֤
	public void sendAuth(final String auth) {
		UserData data = (UserData) context.getApplicationContext();
		try {
			PrintStream out = new PrintStream(data.getS().getOutputStream());
			out.print(auth + "\n");
			out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
