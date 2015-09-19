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
 * ��������ע��ҳ
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
	
	private static  String[] ganqing = { "����", "������","����" };
	private static  String[] xinbie = { "��", "Ů"};
	private static  String[] nianji = { "��һ", "���","����","����","��һ","�ж�","����","��ʿ"};
	private static  String[] yuanxi={"��ľ����ѧԺ","��е����ѧԺ","���ù���ѧԺ","����ѧԺ","��ͨ����ѧԺ","����ѧԺ","����ѧԺ","����ѧԺ","����ѧԺ","��ϢѧԺ","�о���ѧԺ","��רѧԺ","���ʽ���ѧԺ","�ķ�ѧԺ","������ѧϵ","����ϵ","����ϵ","���˼����ѧԺ"};
	UserData data ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registerbasedata);
		title = (TextView) findViewById(R.id.basedatatitle);
		title.setText("�������� ");
		editTextName=(EditText) findViewById(R.id.byname);
		
		//���ø����ֵ
		GanQing();
//		
//		//�����Ա�
//		 XinBie();
//		 
//		 //�����꼶
//		 nianji();
//		 
//		 //����Ժϵ
//		 yuanxi();
		 
		buttonWancheng=(Button) findViewById(R.id.wancheng);
		// �����ת�¼�
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
								Toast.makeText(RegisterBaseData.this, "�ǳƲ���Ϊ��", Toast.LENGTH_SHORT).show();
								buttonWancheng.setBackgroundResource(R.drawable.wancheng_1);
								break;
							}
						    //����������������
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
		// ���ذ�ť�������¼�
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
	
	//��ȡ����״̬��ֵ
	public void GanQing(){
		// ����״̬spinner���ݼ���
		spinnerGanqing = (Spinner) findViewById(R.id.ganqing);
		adapter = new ArrayAdapter<String>(this,
			R.layout.firstspinneritem, ganqing);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinnerGanqing.setAdapter(adapter);
		spinnerGanqing.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				  //�õ���ѡ�����ֵ 
				strGanQing = ganqing[position];
				System.out.println(strGanQing);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
	
//	   //��ȡ�Ա�
//		public void XinBie(){
//			// ����״̬spinner���ݼ���
//			spinnerXinbie = (Spinner) findViewById(R.id.xinbie);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, xinbie);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerXinbie.setAdapter(adapter);
//			spinnerXinbie.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //�õ���ѡ�����ֵ 
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
//		 //��ȡ�꼶
//		public void nianji(){
//			// ����״̬spinner���ݼ���
//			spinnerNianji = (Spinner) findViewById(R.id.nnianji);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, nianji);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerNianji.setAdapter(adapter);
//			spinnerNianji.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //�õ���ѡ�����ֵ 
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
//		 //��ȡԺϵ
//		public void yuanxi(){
//			// ����״̬spinner���ݼ���
//			spinnerYuanxi = (Spinner) findViewById(R.id.yuanxi);
//			adapter = new ArrayAdapter<String>(this,
//				R.layout.firstspinneritem, yuanxi);
//			adapter.setDropDownViewResource(R.layout.spinneritem);
//			spinnerYuanxi.setAdapter(adapter);
//			spinnerYuanxi.setOnItemSelectedListener(new OnItemSelectedListener() {
//				@Override
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int position, long id) {
//					  //�õ���ѡ�����ֵ 
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

