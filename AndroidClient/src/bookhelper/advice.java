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
				Toast.makeText(advice.this, "�ύ�ɹ���", Toast.LENGTH_SHORT)
			    .show();
			}
			else
			{
				Toast.makeText(advice.this, "�ύʧ�ܣ�", Toast.LENGTH_SHORT)
			    .show();
			}
			// ��WebService���صĽ����ʾ��TextView��
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
        username.setText(unm+"���ã����������ı��������");
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
						// ��ѯ�ֻ����루�Σ���Ϣ
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
		// �����ռ�
		String nameSpace = "http://aa.com.cn/";
		// ���õķ�������
		String methodName = "advice";
		// EndPoint
		String endPoint = "http://192.168.0.104:8080/advice/services/getadvice";
		// SOAP Action
		String soapAction = "http://aa.com.cn/advice";

		// ָ��WebService�������ռ�͵��õķ�����
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// ���������WebService�ӿ���Ҫ�������������mobileCode��userId
		Intent intent = getIntent();
        String unm = intent.getStringExtra("USERNAME");
		rpc.addProperty("advicename",unm);
		rpc.addProperty("advicemessage", advicemessage);

		// ���ɵ���WebService������SOAP������Ϣ,��ָ��SOAP�İ汾
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		envelope.bodyOut = rpc;
		// �����Ƿ���õ���dotNet������WebService
		envelope.dotNet = true;
		// �ȼ���envelope.bodyOut = rpc;
		envelope.setOutputSoapObject(rpc);
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// ����WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ȡ���ص�����
		SoapObject object = (SoapObject) envelope.bodyIn;
		// ��ȡ���صĽ��
		int count = object.getPropertyCount(); 
		System.out.println(count);
		result = object.getProperty(0).toString();
		//list.add(object.getProperty(0).toString());
		//System.out.println(result);
//		// ��WebService���صĽ����ʾ��TextView��
		
	
	
 }
}
    
    

