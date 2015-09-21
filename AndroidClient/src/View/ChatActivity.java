package com.stdu.edu.italk.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.adapter.ChatMsgViewAdapter;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.resource.ChatMsgEntity;

/**
 * @author xianming
 */
public class ChatActivity extends Activity implements OnClickListener {

	private Button mBtnSend;// 发送btn
	private ImageButton mBtnBack;// 返回btn
	private ImageView touXiang;//头像
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
	private TextView title;
	UserData data;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat);
		initView();// 初始化view
		initData();// 初始化数据
		mListView.setSelection(mAdapter.getCount() - 1);
		registerBroadCast();
		//显示模糊头像
		//touXiang.setImageBitMap(blurBitmap(touXiang,getBlur()));
	}

	/**
	 * 初始化view
	 */
	public void initView() {
		mListView = (ListView) findViewById(R.id.listview_chat);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (ImageButton) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		title = (TextView) findViewById(R.id.chatTitles);
		touXiang=(ImageView)findViewById(R.id.touxiang);
	}
	
	
	//动态注册消息侦听广播
	void registerBroadCast(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("italk.msgreflesh");
		MsgChangeReceiver msgChangeReceiver = new MsgChangeReceiver();
		registerReceiver(msgChangeReceiver, intentFilter);
	}

	private String[] msgArray = new String[] { "你的头像怎么是这样呀！", "不然呢？", "哈哈，我也有",
			"想看本小姐？~", "恩恩", "好，帮我把今天的高数做了吧", "纳尼~", "尼滚...", "晚上有空么", "怎么了",
			"嘿嘿，帮你做高数呀", "哦！！" };

	private String[] dataArray = new String[] { "2012-09-22 18:00:02",
			"2012-09-22 18:10:22", "2012-09-22 18:11:24",
			"2012-09-22 18:20:23", "2012-09-22 18:30:31",
			"2012-09-22 18:35:37", "2012-09-22 18:40:13",
			"2012-09-22 18:50:26", "2012-09-22 18:52:57",
			"2012-09-22 18:55:11", "2012-09-22 18:56:45",
			"2012-09-22 18:57:33", };
	private final static int COUNT = 12;// 初始化数组总数

	/**
	 * 加载消息历史
	 */
	public void initData() {
		// 更新标题
		Intent intent = getIntent();
		String strName = intent.getStringExtra("name");
		title.setText(strName);
//		Toast.makeText(ChatActivity.this, intent.getStringExtra("loginName"), Toast.LENGTH_SHORT).show();
		// 更新listview
//		for (int i = 0; i < COUNT; i++) {
//			ChatMsgEntity entity = new ChatMsgEntity();
//			entity.setDate(dataArray[i]);
//			if (i % 2 == 0) {
//				entity.setMsgType(true);// 收到的消息
//			} else {
//				entity.setMsgType(false);// 自己发送的消息
//			}
//			entity.setMessage(msgArray[i]);
//			mDataArrays.add(entity);
//		}
		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:// 发送按钮点击事件
			send();
			break;
		case R.id.btn_back:// 返回按钮点击事件
			finish();// 结束,实际开发中，可以返回主界面
			break;
		}
	}

	/**
	 * 发送消息
	 */
	private void send() {
		SendToFriend send;
		String contString = mEditTextContent.getText().toString();
		data= (UserData) getApplication();
		Intent intent=getIntent();
		if (contString.length() > 0) {
			// 封装json数据，格式为:{"from":"value","to":"value","content":"value","time":"value"}
			JSONObject mJSONObject = new JSONObject();
			try {
				//发送json数据至服务器
				mJSONObject.put("from", data.getUserName());
				mJSONObject.put("to", intent.getStringExtra("loginName"));
				mJSONObject.put("content", contString);
				mJSONObject.put("time", getDate().toString());
				send=new SendToFriend(this);
				send.execute(mJSONObject);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setMessage(contString);
			entity.setMsgType(false);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

			mEditTextContent.setText("");// 清空编辑框数据

			mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
		}
	}

	/**
	 * 发送消息时，获取当前事件
	 * 
	 * @return 当前时间
	 */
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
	
	
	//监听消息广播
	private class MsgChangeReceiver extends BroadcastReceiver{
		JSONObject mJSONObject;
		ChatMsgEntity entity;
		
		// 获取目标ID
		public String getLoginName(){
			Intent intent = getIntent();
			String loginName = intent.getStringExtra("loginName");
			return loginName;
		}
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra("jsonMsg");
			String loginName = null;
			try {
				mJSONObject=  new JSONObject(msg);
				entity = new ChatMsgEntity();
				entity.setDate(mJSONObject.getString("time"));
				entity.setMessage(mJSONObject.getString("content"));
				entity.setFrom(mJSONObject.getString("from"));
				entity.setMsgType(true);
				loginName=mJSONObject.getString("from");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//判断是否将消息显示在当前页面
			if(getLoginName().equals(loginName)){
				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
				mListView.setSelection(mListView.getCount() - 1);// 收到一条消息时，ListView显示选择最后一项
				// 更新数据库中的模糊值
				//updateBlur();
			
			}
		}
	}
	
	// //图像模糊处理
	// public Bitmap blurBitmap(Bitmap bitmap,float radius){
		
	// 	//创建一个拥有相同尺寸的图像用于模糊
	// 	Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		
	// 	//初始化 Renderscript
	// 	RenderScript rs = RenderScript.create(getApplicationContext());
		
	// 	//创建Intrinsic Blur脚本
	// 	ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
		
	// 	//创建输入输出图像
	// 	Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
	// 	Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
		
	// 	//设置模糊度
	// 	blurScript.setRadius(radius);
		
	// 	//执行Renderscript
	// 	blurScript.setInput(allIn);
	// 	blurScript.forEach(allOut);
		
	// 	//将最终模糊图像复制入outbitmap中
	// 	allOut.copyTo(outBitmap);
		
	// 	//回收bitmap
	// 	bitmap.recycle();
		
	// 	//销毁Renderscript.
	// 	rs.destroy();
		
	// 	return outBitmap;
		
	// }
}
