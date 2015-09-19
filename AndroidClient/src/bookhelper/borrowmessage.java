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
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class borrowmessage extends ListActivity
{
	private TextView book_number;
	private ListView lv;
	String bookname=" ";
	String booknum =" ";
	String writer = " ";
	String publish =" ";
	String unm=" ";
	String booklend=" ";
	String bookback=" ";
	int booknumber;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
	Map<String,Object> map=new HashMap<String,Object>();
	Handler lsr=new Handler() 
	   {
		
		   @Override
		  public void handleMessage(Message msg)//重写方法  
		  {
			
			   Bundle b = msg.getData();
			   int result = b.getInt("a");
			   book_number.setText("您共借了"+result+"本书");
			   ArrayList<HashMap<String,Object>>  a = (ArrayList<HashMap<String,Object>>)msg.obj;   
			   SimpleAdapter mSimpleAdapter = new SimpleAdapter(borrowmessage.this,a,R.layout.list_item ,
					   new String[]{"itemj","itemh","itemp","itemwriter","itempublish"}, new int[]{R.id.title,R.id.title1,R.id.title2,R.id.title3,R.id.title4});
			   
			   
			   lv.setAdapter(mSimpleAdapter);
			   
		    
		
		  }
	   };
	  
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.main);
		Intent intent = getIntent();
	    unm = intent.getStringExtra("USERNAME");
	    final ProgressDialog progressDialog=new ProgressDialog(borrowmessage.this);
        progressDialog.setTitle("网络连接");
        progressDialog.setMessage("正在搜索，请稍等......");
        progressDialog.setIndeterminate(true);
        //progressDialog=ProgressDialog.show(clswdy.this, "网络连接","正在验证，请稍等......",true,true);
        progressDialog.setButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
             progressDialog.cancel();
             
            }
           });
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
           });
        progressDialog.show();
	    book_number=(TextView)findViewById(R.id.booknumber);
		lv=(ListView)findViewById(android.R.id.list);
		 new Thread(new Runnable() {
				
				@Override
				public void run() {
					Log.i("Thread","into Thread");
					// TODO Auto-generated method stub
					// 查询手机号码（段）信息
				    Intent intent = getIntent();
				    String unm = intent.getStringExtra("USERNAME");
				    System.out.println("aaaabbbb"+unm);
					getRemoteInfo(unm);
					progressDialog.dismiss();
					Message msg = lsr.obtainMessage();
					msg.obj = list; 
					Bundle b = new Bundle();
				    b.putInt("a", booknumber);
					msg.setData(b);
					lsr.sendMessage(msg);
				}
			}).start();
		
	        
	    }
	 protected void onListItemClick(ListView l,View v,int position,long id){
		 super.onListItemClick(l, v, position, id);
		 if(position==0)
		 {
			 System.out.println("aaaaaa");
		 }
		 
	 }
		 

	
	//访问webservice
	 public void getRemoteInfo(String unm)
	    {
	        // Inflate the menu; this adds items to the action bar if it is present.
		 List<String> tushu=new ArrayList<String>();
		 //String nameSpace = "http://WebXml.com.cn/";
		 String nameSpace = "http://common/";
    	 String methodName = "getreaderBook";
    	 String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";
    	 String soapAction = "http://common/TitleQuery";
    	 SoapObject rpc = new SoapObject(nameSpace, methodName); 
    	 rpc.addProperty("arg0",unm);
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
	    		 transport.call(null, envelope);
	    		 if(envelope.getResponse()!=null)  
	             {  
	                
	    			 SoapObject object = (SoapObject)envelope.bodyIn;
	    	    	 System.out.println("××5××获得服务数据成功");
	    	    	
	    	    	 {
	    	       
	    	    	
	    	    		
	    	    		 
	 				String result=object.getProperty(0).toString();
	 				JSONArray arr = new JSONArray(result);  
	 				booknumber = arr.length();
	 				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa"+booknumber);
	 				for (int i = 0; i < arr.length(); i++)
	    	         {  
	    	             JSONObject temp = (JSONObject) arr.get(i);  
	    	             booknum = temp.getString("prop_no"); 
	    	             booklend= temp.getString("lend_date"); 
	    	             bookback= temp.getString("norm_ret_date");
	    	             getbookmessage(booknum);
	    	             map.put("itemh",booklend);
	    	             map.put("itemp",bookback);
	    	             list.add(map);
	    	            // System.out.println(booknum);
	    	    		// map = new HashMap<String, Object>();
	    	    		// map.put("itemj",booknum);
	 					 //list.add(map);
	 				 }
	 			   // System.out.println(result);
	 					
	    	    	 }
	    	    	
	    	    	
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
	 public void getbookmessage(String booknum)
	    {
	        // Inflate the menu; this adds items to the action bar if it is present.
		 List<String> tushu=new ArrayList<String>();
		 //String nameSpace = "http://WebXml.com.cn/";
		 String nameSpace = "http://common/";
 	 String methodName = "getbook";
 	 String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";
 	 String soapAction = "http://common/TitleQuery";
 	 SoapObject rpc = new SoapObject(nameSpace, methodName); 
 	 rpc.addProperty("arg0",booknum);
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
	    		 transport.call(null, envelope);
	    		 if(envelope.getResponse()!=null)  
	             {  
	                
	    			 SoapObject object = (SoapObject)envelope.bodyIn;
	    	    	 System.out.println("××5××获得服务数据成功");
	    	    	
	    	    	 {
	    	       
	    	    	
	    	    		
	    	    		 
	 				String result=object.getProperty(0).toString();
	 				 JSONObject stu = new JSONObject(result);
	                 bookname=stu.getString("m_title");
	                 writer = stu.getString("m_author");
	                 publish = stu.getString("m_publisher");
	    	    		 map = new HashMap<String, Object>();
	    	    		 map.put("itemj",bookname);
	 					 map.put("itemwriter","作者："+writer );
	 					 map.put("itempublish","出版社："+publish);
	 				 
	 			   // System.out.println(result);
	 					
	    	    	 }
	    	    	
	    	    	
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
