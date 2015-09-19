package com.stdu.edu.italk.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.adapter.ImagePagerAdapter;
import com.stdu.edu.italk.adapter.LumpFindAdapter;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.bookhelper.MainActivity;
import com.stdu.edu.italk.couser.Kebiao;
import com.stdu.edu.italk.resource.LumpFind;
/**
 * 发现页的fragment
 * @author xianming
 *
 */
public class FindFragment extends Fragment implements OnPageChangeListener, OnItemClickListener {

	private ListView findList;
	private List<LumpFind> list = new ArrayList<LumpFind>();
	private LumpFindAdapter adapter;
	private List<View> listimage;
	private ViewPager viewpager;
	private ImagePagerAdapter imagepageradapter;
	private View viewimage01;
	private View viewimage02;
	private View viewimage03;
	View view;
	private String result = "";
	private String user11 = " ";
	private String user111 = " ";
	String student1=" ";
	String student2=" ";
	UserData data;

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.tab_find, container, false);
		viewimage01 = inflater.inflate(R.layout.image01, container, false);
		viewimage02 = inflater.inflate(R.layout.image02, container, false);
		viewimage03 = inflater.inflate(R.layout.image03, container, false);
		listimage = new ArrayList<View>();
		listimage.add(viewimage01);
		listimage.add(viewimage02);
		listimage.add(viewimage03);
		viewpager = (ViewPager) view.findViewById(R.id.pager);
		imagepageradapter = new ImagePagerAdapter(listimage);
		viewpager.setAdapter(imagepageradapter);
		viewpager.setCurrentItem(10004 / 2);
		view.findViewById(R.id.page_icon_1).setBackgroundResource(
				R.drawable.page_indicator_unfocused);
		view.findViewById(R.id.page_icon_2).setBackgroundResource(
				R.drawable.page_indicator_focused);
		view.findViewById(R.id.page_icon_3).setBackgroundResource(
				R.drawable.page_indicator_unfocused);
		viewpager.setOnPageChangeListener(this);
		findList = (ListView) view.findViewById(R.id.listviewfind);
		initData();
		adapter = new LumpFindAdapter(list, getActivity());
		findList.setAdapter(adapter);
		findList.setOnItemClickListener(this);
		return view;
	}

	public void initData() {
		LumpFind lumpfind01 = new LumpFind(R.drawable.kebiao, "我的课表",
				R.drawable.jiantou, 0);
		list.add(lumpfind01);
		LumpFind lumpfind02 = new LumpFind(R.drawable.siliji, "图书馆助手",
				R.drawable.jiantou, 0);
		list.add(lumpfind02);
		LumpFind lumpfind03 = new LumpFind(R.drawable.tiedatieba, "校内吧",
				R.drawable.jiantou, 0);
		list.add(lumpfind03);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0%listimage.size()) {
		case (0): {
			view.findViewById(R.id.page_icon_1).setBackgroundResource(
					R.drawable.page_indicator_focused);
			view.findViewById(R.id.page_icon_2).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			view.findViewById(R.id.page_icon_3).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			break;
		}

		case 1: {
			view.findViewById(R.id.page_icon_1).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			view.findViewById(R.id.page_icon_2).setBackgroundResource(
					R.drawable.page_indicator_focused);
			view.findViewById(R.id.page_icon_3).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			break;
		}
		case 2: {
			view.findViewById(R.id.page_icon_2).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			view.findViewById(R.id.page_icon_1).setBackgroundResource(
					R.drawable.page_indicator_unfocused);
			view.findViewById(R.id.page_icon_3).setBackgroundResource(
					R.drawable.page_indicator_focused);
			break;
		}

		}

	}
	
	//listView点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		LumpFind lumpfind = list.get(position);
		if (lumpfind.getItemName().equals("我的课表"))
		{
			// 跳转至课表
			Intent intent = new Intent();
			intent.setClass(getActivity(), Kebiao.class);
			startActivity(intent);
			
		} 
		if (lumpfind.getItemName().equals("图书馆助手"))
		{
			 data=(UserData) getActivity().getApplication();
			// 跳转至图书助手
		    System.out.println("hahhahsdk");
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
            bundle.putString("KEY_USERNAME",data.getUserName());
            bundle.putString("KEY_NUM",data.getUserPassword());
            intent.putExtras(bundle);
			intent.setClass(getActivity(), MainActivity.class);
			startActivity(intent);

		}
		
	}
	
	    //访问webservice
		public void getremoteInfo(String user,String password)
	    {
	        // Inflate the menu; this adds items to the action bar if it is present.
	    	 String nameSpace = "http://common/";
	    	 String methodName = "AuthLogin";
	    	 String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";  
	    	 String soapAction = "http://common/AuthLogin";
	    	 SoapObject rpc = new SoapObject(nameSpace, methodName); 
	    	 rpc.addProperty("arg0",user);
	    	 rpc.addProperty("arg1",password); 
	    	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	 envelope.bodyOut = rpc;
	    	 envelope.dotNet = false; 
	    	 envelope.setOutputSoapObject(rpc);
	    	 System.out.println("rpc:"+rpc); 
	         System.out.println("enevlope:"+envelope); 
	    	 System.out.println("××2××基本服务设置完毕，下面开始调用服务"); 
	    	 
	    	 try { 
	    		 HttpTransportSE transport = new HttpTransportSE(endPoint);
	    		 
	    		 System.out.println("××2×dsdasda");
	    		 transport.call(null,envelope);
	    		   
	    		 System.out.println("allalla");
	                 //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，  
	                 //该对象就代表了WebService的返回消息。  
	                 //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject  
	    			 SoapObject object = (SoapObject)envelope.bodyIn;
	    	    	 System.out.println("××5××获得服务数据成功");
	    	    	 result = object.getProperty(0).toString();
	    	    	 JSONObject stu = new JSONObject(result);
	                 user11=stu.getString("name");
	                 user111=stu.getString("cert_id");
	    		 System.out.println("××3××调用webservice服务成功");
	    	 }  catch(Exception e) {
	    		 e.printStackTrace();
	    		 System.out.println("××4××调用webservice服务失败");
	    	 }
	    	 
	    	 
	    }
	
	
}
