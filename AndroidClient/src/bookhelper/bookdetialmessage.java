package com.stdu.edu.italk.bookhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.stdu.edu.italk.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class bookdetialmessage extends Activity{
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
	Map<String,Object> map=new HashMap<String,Object>();
	Handler lsr=new Handler()	
	   {
		
		   @Override
		  public void handleMessage(Message msg)//重写方法  
		  {
			
			   Bundle b = msg.getData();
			   String result = b.getString("a");
			   //String result1 = b.getString("b");
			  // String result2 = b.getString("c");
			  // String result3 = b.getString("d");
			  // String result4 = b.getString("e");
			  // String result5 = b.getString("f");
			 
			super.handleMessage(msg);
		
			location.setText(""+result);
			//resultView1.setText(result1);
			//resultView2.setText(result2);
			//resultView3.setText(result3);
			//resultView4.setText(result4);
			//resultView5.setText(result5);
		    
		
		  }
	   };
	String booknumber=" ";
	private TextView location;
	String macs= " ";
	String mac1=" ";
	private ListView list11;
	protected void onCreate(Bundle savedInstanceState)
	{
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookdetialmessage);
		Intent intent = getIntent();
	    booknumber = intent.getStringExtra("BOOKNUM");
	   // list11 = (ListView)findViewById(R.id.androidlist);
	    location=(TextView)findViewById(R.id.Location);
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.i("Thread","into Thread");
				// TODO Auto-generated method stub
				// 查询手机号码（段）信息
			    Intent intent = getIntent();
			    String unm = intent.getStringExtra("USERNAME");
			    System.out.println("aaaabbbb"+unm);
				arr1(booknumber);
				
				Message msg = lsr.obtainMessage();
				System.out.println("aaaaaaaaaa"+mac1);
				 Bundle b = new Bundle();
			      b.putString("a", mac1);
			        //b.putString("b", result1);
			       // b.putString("c", result2);
			      //  b.putString("d", result3);
			      //  b.putString("e", result4);
			      //  b.putString("f", result5);
			        msg.setData(b);
			        msg.sendToTarget();
			}
		}).start();
	
        
    }
	public String arr1(String mac)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
		  String result1="";
		 String macs="";
		 //String nameSpace = "http://WebXml.com.cn/";
		 String nameSpace = "http://common/";
    	 String methodName = "MarcQuery";
    	 String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";
    	 String soapAction = "http://common/MarcQuery";
    	 SoapObject rpc = new SoapObject(nameSpace, methodName); 
    	 rpc.addProperty("arg0",mac);
    	 //rpc.addProperty("password",password); 
    	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	 envelope.bodyOut = rpc;
    	 envelope.dotNet = false; 
    	
        
    	 envelope.setOutputSoapObject(rpc);
    	 
    	
    	 System.out.println("rpc:"+rpc); 
         System.out.println("enevlope:"+envelope); 
    	 System.out.println("××基××本服务设置完毕，下面开始调用服务"); 
    	 try
    	 { 
    		 HttpTransportSE transport = new HttpTransportSE(endPoint);
    		 System.out.println("××2×dsdasda");
    		 transport.call(null, envelope);
    		 System.out.println("pokoooooooooooo");
    		 //if(envelope.getResponse()!=null)  
            // {  
                 //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，  
                 //该对象就代表了WebService的返回消息。  
                 //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject  
    		 
    		     SoapObject object = (SoapObject)envelope.bodyIn;
    	    	 System.out.println("××5××获得服务数据成功");
    	    	 int count = object.getPropertyCount();
    	    	 System.out.println(count);
    	         result1 =object.getProperty(0).toString();
    	         JSONArray arr1 = new JSONArray(result1);
    	         for (int i = 0; i < 1; i++)
    	         {  
    	        	 JSONObject temp = (JSONObject) arr1.get(0);  
    	             macs = temp.getString("location");  
    	         }
    	         
    	         mac1= macs;
    	         System.out.println(mac1);
    	       
    	         
    	        
    		   
    	            
    	     //}
    	
    	
    		 
    		 System.out.println("××3××调用webservice服务成功");
    	 } 
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    		 System.out.println("××4××调用webservice服务失败");
    	 }
	
    
    	 return macs;
    }
	
	
}




