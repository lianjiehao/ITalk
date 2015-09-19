package com.stdu.edu.italk.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.fragment.MyFragmentActivity;
import com.stdu.edu.italk.rewritewidget.DampView;

public class friendpager extends Activity implements OnClickListener {
	private ImageButton backToHome;
	private ImageView img;
	DampView view;
	TextView title;
	TextView ganQing;
	String strGanQing;
	LinearLayout layout;
	String toStrName;
	
	String tologinName;
	ListView chatList;
	UserData data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.frienddocument);
		initView();
		initClick();
		setupView();
		refleshChatFragment();
	}
	
	public void initView(){
		backToHome=(ImageButton) findViewById(R.id.backtohome2);
		title  = (TextView) findViewById(R.id.fri_title);
		ganQing =(TextView) findViewById(R.id.friend_ganQing);
		layout = (LinearLayout) findViewById(R.id.sendMessageToFri);
	}
	
	
	public void setupView() {
		img = (ImageView) findViewById(R.id.friendbackground);
		view = (DampView) findViewById(R.id.dampview_friend);
		
		//取出Intent中值
		Intent intent = getIntent();
		toStrName = intent.getStringExtra("name");
		tologinName=intent.getStringExtra("loginName");
		title.setText(intent.getStringExtra("name"));
		SetFriendPagerData data = new SetFriendPagerData(this,ganQing);
		data.execute(intent.getStringExtra("name"));
		
		view.setImageView(img);
	}
	
	//刷新聊天fragement
	public void refleshChatFragment(){
		
	}
	
	
	public void initClick(){
		backToHome.setOnClickListener(this);
		layout.setOnClickListener(this);
	}
	

	
	@Override
	public void onClick(View v) {
		Intent intent;
		View view = LayoutInflater.from(friendpager.this).inflate(R.layout.tab_chat,null);
		chatList = (ListView) view.findViewById(R.id.chatlist);
		switch(v.getId()){
		case R.id.backtohome2:
			intent=new Intent(this,MyFragmentActivity.class);
			startActivity(intent);
			break;
			
		case R.id.sendMessageToFri:
			SaveChatData saveChat = new SaveChatData(this);
			data = (UserData) getApplication();
			saveChat.execute(toStrName,data.getUserName(),tologinName);
			break;
		}
	}
}
