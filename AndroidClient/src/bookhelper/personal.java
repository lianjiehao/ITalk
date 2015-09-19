 package com.stdu.edu.italk.bookhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.stdu.edu.italk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class personal  extends Activity {
	private TextView resultView;
	private TextView  resultView1;
	private TextView  resultView2;
	private TextView  resultView3;
	private TextView  resultView4;
	private TextView  resultView5;
	Map<String,Object> map=new HashMap<String,Object>();
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	String result,result1,result2,result3,result4,result5;
	
  private Handler handle = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			  Bundle b = msg.getData();
			   String result = b.getString("a");
			   String result1 = b.getString("b");
			   String result2 = b.getString("c");
			   String result3 = b.getString("d");
			   String result4 = b.getString("e");
			   String result5 = b.getString("f");
			  // String name = b.getString("name");
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		//	Log.i("handle","into handle");
		   	//String result = msg.obj.toString();
		   	
			//Log.i("TAG",result);
			// 将WebService返回的结果显示在TextView中
			resultView.setText(result);
			resultView1.setText(result1);
			resultView2.setText(result2);
			resultView3.setText(result3);
			resultView4.setText(result4);
			resultView5.setText(result5);
		}
		
	};


  //  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        resultView = (TextView) findViewById(R.id.photo);
        resultView1 = (TextView) findViewById(R.id.sex);
        resultView2 = (TextView) findViewById(R.id.sexsex);
        resultView3 = (TextView) findViewById(R.id.sexsexsex);
        resultView4 = (TextView) findViewById(R.id.sex1);
        resultView5 = (TextView) findViewById(R.id.ll);
        Intent intent = getIntent();
        String unm = intent.getStringExtra("USERNAME");
       // System.out.println("bbb"+unm);
       // username.setText(unm+"您好！");
         //接受个人中心传递过来的用户名
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.i("Thread","into Thread");
				// TODO Auto-generated method stub
				// 查询手机号码（段）信息
			    Intent intent = getIntent();
			    String unm = intent.getStringExtra("USERNAME");
				getRemoteInfo(unm);
				Message msg = handle.obtainMessage();
				//msg.obj = result; 
				//handle.sendMessage(msg);
		        Bundle b = new Bundle();
		        b.putString("a", result);
		        b.putString("b", result1);
		        b.putString("c", result2);
		        b.putString("d", result3);
		        b.putString("e", result4);
		        b.putString("f", result5);
		        msg.setData(b);
		        msg.sendToTarget();
			}
		}).start();
        
        
    }
    public void getRemoteInfo(String unm)
	    {
	        // Inflate the menu; this adds items to the action bar if it is present.
			 List<String> tushu=new ArrayList<String>();
			 String nameSpace = "http://aab.com/";
			 
	    	 String methodName = "personal";
	    	// String endPoint = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl"; 
	    	 String endPoint = "http://192.168.0.104:8080/personal/services/personal";
	    	 //String soapAction = "http://WebXml.com.cn/getSupportProvince";
	    	 String soapAction = "http://aab.com/personal";
	    	 SoapObject rpc = new SoapObject(nameSpace, methodName); 
	    	 rpc.addProperty("Bname",unm);
	    	 //rpc.addProperty("password",password); 
	    	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	 envelope.bodyOut = rpc;
	    	 envelope.dotNet = false; 
	    	
	        
	    	 envelope.setOutputSoapObject(rpc);
	    	 
	    	
	    	 System.out.println("rpc:"+rpc); 
	         System.out.println("enevlope:"+envelope); 
	    	 System.out.println("××基××本服务设置完毕，下面开始调用服务"); 
	    	 try { 
	    		 HttpTransportSE transport = new HttpTransportSE(endPoint);
	    		 System.out.println("××2×dsdasda");
	    		 transport.call(soapAction, envelope);
	    		 if(envelope.getResponse()!=null)  
	             {  
	                 //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，  
	                 //该对象就代表了WebService的返回消息。  
	                 //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject  
	    			 SoapObject object = (SoapObject)envelope.getResponse();
	    	    	 System.out.println("××5××获得服务数据成功");
	    	    	 int count = object.getPropertyCount();
	    	    	  
	    	        // map2.put("message","性别");
	    	        // map3.put("message","信息");
	    			// list.add(map1);
	    			// list.add(map2);
	    			// list.add(map3);
	    	    	 //for(int index=0;index<count;index++)
	    	    	// {
	    	    		
	    	    		 //map = new HashMap<String, Object>();
	 					// map.put("item", object.getProperty(0).toString());
	 					 result = object.getProperty(0).toString();
	 					 System.out.println("aaaaaa"+result);
	 					 result1 = object.getProperty(1).toString(); 
	 					 result2 = object.getProperty(2).toString();
	 					 result3 = object.getProperty(3).toString();
	 					 result4 = object.getProperty(4).toString();
	 					 result5 = object.getProperty(5).toString();
	 					//map.put("message","姓名"); 
	 					// list.add(map);
	 					
	    	    	 //}
	    	    	 //hd.sendEmptyMessage(0);
	    	    	
	             }
	    		 else
	    		 {
	                // result="aa";
	                 //hd.sendEmptyMessage(0);
	    	     }
	    		 System.out.println("××3××调用webservice服务成功");
	    	 }  catch(Exception e) {
	    		 e.printStackTrace();
	    		 
	            // hd.sendEmptyMessage(0);
	    		 System.out.println("××4××调用webservice服务失败");
	    	 }
		
	    
	    	 
	    }
		
		
}

    
	
	


