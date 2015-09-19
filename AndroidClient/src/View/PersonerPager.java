package com.stdu.edu.italk.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.fragment.MyFragmentActivity;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;
import com.stdu.edu.italk.rewritewidget.DampView;

public class PersonerPager extends Activity implements OnClickListener {
	private ImageButton backToHome;
	Intent intent;
	private ImageView img;
	DampView view;
	TextView italkHao;
	TextView ganQing;
	TextView persontitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.persondocument);
		initView();
		initClick();
		setupView();
		refleshView();
	}

	public void initView() {
		backToHome = (ImageButton) findViewById(R.id.backtohome1);
		italkHao = (TextView) findViewById(R.id.italkhao);
		ganQing = (TextView) findViewById(R.id.ganqing);
		persontitle = (TextView) findViewById(R.id.persontitle);
	}

	public void setupView() {
		img = (ImageView) findViewById(R.id.personbackground);
		view = (DampView) findViewById(R.id.dampview);
		view.setImageView(img);
	}

	public void initClick() {
		backToHome.setOnClickListener(this);
	}

	public void refleshView() {
		UserData data = (UserData) this.getApplicationContext();
		String strGanQing = null;
		String strLoginName = null;
		String strName = null;
		SchoolFriSQLiteOpenHelper mSQLiteHelper = new SchoolFriSQLiteOpenHelper(
				this, "dbLocal", null, 1);
		SQLiteDatabase db = mSQLiteHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where loginName=?",
				new String[] { data.getUserName() });
		if (cursor.moveToFirst()) {
			for (int j = 0; j < cursor.getCount(); j++) {
				cursor.move(j);
				// 获取并更新感情状况
				strGanQing = cursor.getString(4);
				ganQing.setText(strGanQing);

				// 获取并更新登陆名
				strLoginName = cursor.getString(1);
				italkHao.setText(strLoginName);
				
				// 获取并更新昵称
				strName = cursor.getString(3);
				persontitle.setText(strName);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backtohome1:
			intent = new Intent(this, MyFragmentActivity.class);
			startActivity(intent);
			break;
		}
	}

}
