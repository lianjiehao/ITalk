package com.stdu.edu.italk.bookhelper;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;




import com.stdu.edu.italk.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class advice extends Activity{
	private TextView username;
	private Button obtainsubmit;
	private EditText obtainadvice;
	String advicename,advicemessage;
	String result;
//	HttpThread thread=null;
private Handler handle = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("handle","into handle");
			String result = msg.obj.toString();
			Log.i("TAG",result);
			if((result != null && !result.isEmpty() ))
			{
				Toast.makeText(advice.this, "提交成功！", Toast.LENGTH_SHORT)
			    .show();
			}
			else
			{
				Toast.makeText(advice.this, "提交失败！", Toast.LENGTH_SHORT)
			    .show();
			}
			// 将WebService返回的结果显示在TextView中
			//resultView.setText(result);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advice);
        username = (TextView)findViewById(R.id.advicename);
        Intent intent = getIntent();
        String unm = intent.getStringExtra("USERNAME");
       // System.out.println("bbb"+unm);
        username.setText(unm+"您好，请输入您的宝贵意见！");
        obtainsubmit = (Button)findViewById(R.id.submit);
        obtainsubmit.setOnClickListener(new submitListener());
        obtainadvice = (EditText)findViewById(R.id.WriteAdviceMessage);
 }
    class submitListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			advicemessage=obtainadvice.getText().toString();
			Intent intent = getIntent();
	        String unm = intent.getStringExtra("USERNAME");
	          new Thread(new Runnable() {
					
					@Override
					public void run() {
						Log.i("Thread","into Thread");
						// TODO Auto-generated method stub
						// 查询手机号码（段）信息
						//getRemoteInfo(advicemessage);
						String result="m";
						Message msg = handle.obtainMessage();
						msg.obj = result; 
						handle.sendMessage(msg);
					}
				}).start();
			
			}
		}
    public void getRemoteInfo(String advicemessage) {
		Log.i("getRemoteInfo","into getRemoteInfo");
		// 命名空间
		String nameSpace = "http://aa.com.cn/";
		// 调用的方法名称
		String methodName = "advice";
		// EndPoint
		String endPoint = "http://192.168.0.104:8080/advice/services/getadvice";
		// SOAP Action
		String soapAction = "http://aa.com.cn/advice";

		// 指定WebService的命名空间和调用的方法名
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// 设置需调用WebService接口需要传入的两个参数mobileCode、userId
		Intent intent = getIntent();
        String unm = intent.getStringExtra("USERNAME");
		rpc.addProperty("advicename",unm);
		rpc.addProperty("advicemessage", advicemessage);

		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// 设置是否调用的是dotNet开发的WebService
		envelope.dotNet = true;
		// 等价于envelope.bodyOut = rpc;
		envelope.setOutputSoapObject(rpc);
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// 调用WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取返回的数据
		SoapObject object = (SoapObject) envelope.bodyIn;
		// 获取返回的结果
		int count = object.getPropertyCount(); 
		System.out.println(count);
		result = object.getProperty(0).toString();
		//list.add(object.getProperty(0).toString());
		//System.out.println(result);
//		// 将WebService返回的结果显示在TextView中
		
	
	
 }
}
    
    

