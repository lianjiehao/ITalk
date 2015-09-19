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

	private static String[] college = { "不限","土木工程学院", "机械工程学院", "经济管理学院", "机械工程学院",
			"人文学院","交通运输学院", " 建筑与艺术学院", "机械工程学院", "材料学院", "电气学院", "信息学院",
			"研究生学院", "工程力学系", "数理系", "外语系", "马克思主义学院", "国际教育学院" };
	private static String[] sex = { "不限","男", "女" };
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

	// 筛选视图的数据加载
	public void showselect() {

		// 更新titlebar数据
		titleRight = (ImageView) findViewById(R.id.rightimageview);

		titlecenter = (TextView) findViewById(R.id.centertextview);
		titlecenter.setText("筛选");

		// 所在地spinner数据加载
		spinner = (Spinner) findViewById(R.id._yuanxi);
		adapter = new ArrayAdapter<String>(this, R.layout.firstspinneritem,
				college);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinner.setAdapter(adapter);

		// 性别spinner数据加载
		spinner = (Spinner) findViewById(R.id._sex);
		adapter = new ArrayAdapter<String>(this, R.layout.firstspinneritem, sex);
		adapter.setDropDownViewResource(R.layout.spinneritem);
		spinner.setAdapter(adapter);

		// 退出事件
		titleRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		intent = new Intent(ShowSelect.this, MyFragmentActivity.class);
		startActivity(intent);
		overridePendingTransition(0, 0);
		//将isvisible置为false,防止右上角按钮无效
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
