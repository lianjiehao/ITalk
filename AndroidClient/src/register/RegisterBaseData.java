package com.stdu.edu.italk.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.fragment.ClientServerConnection_SchoolFri;
import com.stdu.edu.italk.login.LoginPager;

/**
 * 基本资料注册页
 * 
 * @author xianming 2015-7-11
 */

public class RegisterBaseData extends Activity {
	Button buttonBack;
	Intent intentBack;
	Button buttonWancheng;
	TextView title;
	private Spinner spinnerGanqing;
	private Spinner spinnerXinbie;
	private Spinner spinnerNianji;
	private Spinner spinnerYuanxi;
	
	private ArrayAdapter<String> adapter;
	EditText editTextName;
	private String strGanQing;
	private String strXinbie;
	private String strNianji;
	private String strYuanxi;
	
	private static  String[] ganqing = { "单身", "恋爱中","保密" };
	private static  String[] xinbie = { "男", "女"};
	private static  String[] nianji = { "大一", "大二","大三","大四","研一","研二","研三","博士"};
	private static  String[] yuanxi={"土木工程学院","机械工程学院","经济管理学院","人文学院","交通运输学院","建艺学院","人文学院","材料学院","电气学院","信息学院","研究生学院","高专学院","国际教育学院","四方学院","工程力学系","数理系","外语系","马克思主义学院"};
	UserData data ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registerbasedata);
		title = (TextView) findViewById(R.id.basedatatitle);
		title.setText("资料完善 ");
		editTextName=(EditText) findViewById(R.id.byname);
		
		//设置感情的值
		GanQing();
//		
//		//设置性别
//		 XinBie();
//		 
//		 //设置年级
//		 nianji();
//		 
//		 //设置院系
//		 yuanxi();
		 
		buttonWancheng=(Button) findViewById(R.id.wancheng);
		// 完成跳转事件
		buttonWancheng.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View arg0, MotionEvent event) {
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN: {
							buttonWancheng.setBackgroundResource(R.drawable.wancheng_2);
							break;
						}
						case MotionEvent.ACTION_UP: {
							String name = editTextName.getText().toString();
							
							if(name.equals("")){
								Toast.makeText(RegisterBaseData.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
								buttonWancheng.setBackgroundResource(R.drawable.wancheng_1);
								break;
							}
						    //保存数据至服务器
							else{
						    data=(UserData) RegisterBaseData.this.getApplicationContext();
						    ClientServerConnection_Register connection=new ClientServerConnection_Register(RegisterBaseData.this);
						    connection.execute(data.getUserName(),data.getUserPassword(),name,strGanQing,strXinbie,strYuanxi,strNianji);
							buttonWancheng.setBackgroundResource(R.drawable.wancheng_1);
							break;
							}
						}
						}
						return true;
					}
				});
		
		buttonBack = (Button) findViewById(R.id.backtologin);
		// 返回按钮的侦听事件
		buttonBack.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					buttonBack.setTextColor(Color.rgb(220, 220, 220));
					break;
				}

				case MotionEvent.ACTION_UP: {
					intentBack = new Intent(RegisterBaseData.this,
							LoginPager.class);
					startActivity(intentBack);
					finish();
					overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
				}
				}
				return true;
			}
		});
	}
	
	//获取感情状态的值
	public void GanQing(){
		// 感情状态spinner数据加载
		spinnerGanqing = (Spinner) findViewById(R.id.ganqing);
		adapter = new ArrayAdapter<String>(this,
			R.layout.firstspinneritem, ganqing);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinnerGanqing.setAdapter(adapter);
		spinnerGanqing.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				  //拿到被选择项的值 
				strGanQing = ganqing[position];
				System.out.println(strGanQing);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	
//	   //获取性别
//		public void XinBie(){
//			// 感情状态spinner数据加载
//			spinnerXinbie = (Spinner) findViewById(R.id.xinbie);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, xinbie);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerXinbie.setAdapter(adapter);
//			spinnerXinbie.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //拿到被选择项的值 
//					strXinbie = xinbie[position];
//				}
//
//				@Override
//				public void onNothingSelected(AdapterView<?> parent) {
//					
//				}
//			});
//			
//		}
//		
//		 //获取年级
//		public void nianji(){
//			// 感情状态spinner数据加载
//			spinnerNianji = (Spinner) findViewById(R.id.nnianji);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, nianji);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerNianji.setAdapter(adapter);
//			spinnerNianji.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //拿到被选择项的值 
//					strNianji = nianji[position];
//				}
//
//				@Override
//				public void onNothingSelected(AdapterView<?> parent) {
//					
//				}
//			});
//			
//		}
//		
//		
//		 //获取院系
//		public void yuanxi(){
//			// 感情状态spinner数据加载
//			spinnerYuanxi = (Spinner) findViewById(R.id.yuanxi);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, yuanxi);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerYuanxi.setAdapter(adapter);
//			spinnerYuanxi.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //拿到被选择项的值 
//					strYuanxi = yuanxi[position];
//				}
//
//				@Override
//				public void onNothingSelected(AdapterView<?> parent) {
//					
//				}
//			});
//			
//		}
	
	
	
}

