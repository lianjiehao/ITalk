package com.stdu.edu.italk.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.fragment.MyFragmentActivity;
import com.stdu.edu.italk.fragment.SchoolFriFragment;

public class ShowSelect extends Activity implements OnClickListener {

	private static String[] college = { "����","��ľ����ѧԺ", "��е����ѧԺ", "���ù���ѧԺ", "��е����ѧԺ",
			"����ѧԺ","��ͨ����ѧԺ", " ����������ѧԺ", "��е����ѧԺ", "����ѧԺ", "����ѧԺ", "��ϢѧԺ",
			"�о���ѧԺ", "������ѧϵ", "����ϵ", "����ϵ", "���˼����ѧԺ", "���ʽ���ѧԺ" };
	private static String[] sex = { "����","��", "Ů" };
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	ImageView titleRight;
	TextView titlecenter;
	Intent intent;
	MyFragmentActivity myFragmentActivity;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
		setContentView(R.layout.selector);
		getWindow().setLayout(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		showselect();
	}

	// ɸѡ��ͼ�����ݼ���
	public void showselect() {

		// ����titlebar����
		titleRight = (ImageView) findViewById(R.id.rightimageview);

		titlecenter = (TextView) findViewById(R.id.centertextview);
		titlecenter.setText("ɸѡ");

		// ���ڵ�spinner���ݼ���
		spinner = (Spinner) findViewById(R.id._yuanxi);
		adapter = new ArrayAdapter<String>(this, R.layout.firstspinneritem,
				college);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinner.setAdapter(adapter);

		// �Ա�spinner���ݼ���
		spinner = (Spinner) findViewById(R.id._sex);
		adapter = new ArrayAdapter<String>(this, R.layout.firstspinneritem, sex);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinner.setAdapter(adapter);

		// �˳��¼�
		titleRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		intent = new Intent(ShowSelect.this, MyFragmentActivity.class);
		startActivity(intent);
		overridePendingTransition(0, 0);
		//��isvisible��Ϊfalse,��ֹ���Ͻǰ�ť��Ч
		myFragmentActivity.isVisible=false;
	}
	
	
	
	 @Override
	  public boolean onTouchEvent(MotionEvent event) {
	    // If we've received a touch notification that the user has touched
	    // outside the app, finish the activity.
	    if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
	      //finish();
	    	myFragmentActivity.isVisible=false;
	    	finish();
	      return true;
	    }

	    // Delegate everything else to Activity.
	    return super.onTouchEvent(event);
	  }
	

}
