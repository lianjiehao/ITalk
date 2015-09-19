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

	private Button mBtnSend;// ����btn
	private ImageButton mBtnBack;// ����btn
	private EditText mEditTextContent;
	private ListView mListView;
	private ChatMsgViewAdapter mAdapter;// ��Ϣ��ͼ��Adapter
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// ��Ϣ��������
	private TextView title;
	UserData data;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat);
		initView();// ��ʼ��view
		initData();// ��ʼ������
		mListView.setSelection(mAdapter.getCount() - 1);
		registerBroadCast();
	}

	/**
	 * ��ʼ��view
	 */
	public void initView() {
		mListView = (ListView) findViewById(R.id.listview_chat);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (ImageButton) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		title = (TextView) findViewById(R.id.chatTitles);
	}
	
	
	//��̬ע����Ϣ�����㲥
	void registerBroadCast(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("italk.msgreflesh");
		MsgChangeReceiver msgChangeReceiver = new MsgChangeReceiver();
		registerReceiver(msgChangeReceiver, intentFilter);
	}

	private String[] msgArray = new String[] { "���ͷ����ô������ѽ��", "��Ȼ�أ�", "��������Ҳ��",
			"�뿴��С�㣿~", "����", "�ã����Ұѽ���ĸ������˰�", "����~", "���...", "�����п�ô", "��ô��",
			"�ٺ٣�����������ѽ", "Ŷ����" };

	private String[] dataArray = new String[] { "2012-09-22 18:00:02",
			"2012-09-22 18:10:22", "2012-09-22 18:11:24",
			"2012-09-22 18:20:23", "2012-09-22 18:30:31",
			"2012-09-22 18:35:37", "2012-09-22 18:40:13",
			"2012-09-22 18:50:26", "2012-09-22 18:52:57",
			"2012-09-22 18:55:11", "2012-09-22 18:56:45",
			"2012-09-22 18:57:33", };
	private final static int COUNT = 12;// ��ʼ����������

	/**
	 * ������Ϣ��ʷ
	 */
	public void initData() {
		// ���±���
		Intent intent = getIntent();
		String strName = intent.getStringExtra("name");
		title.setText(strName);
//		Toast.makeText(ChatActivity.this, intent.getStringExtra("loginName"), Toast.LENGTH_SHORT).show();
		// ����listview
//		for (int i = 0; i < COUNT; i++) {
//			ChatMsgEntity entity = new ChatMsgEntity();
//			entity.setDate(dataArray[i]);
//			if (i % 2 == 0) {
//				entity.setMsgType(true);// �յ�����Ϣ
//			} else {
//				entity.setMsgType(false);// �Լ����͵���Ϣ
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
		case R.id.btn_send:// ���Ͱ�ť����¼�
			send();
			break;
		case R.id.btn_back:// ���ذ�ť����¼�
			finish();// ����,ʵ�ʿ����У����Է���������
			break;
		}
	}

	/**
	 * ������Ϣ
	 */
	private void send() {
		SendToFriend send;
		String contString = mEditTextContent.getText().toString();
		data= (UserData) getApplication();
		Intent intent=getIntent();
		if (contString.length() > 0) {
			// ��װjson���ݣ���ʽΪ:{"from":"value","to":"value","content":"value","time":"value"}
			JSONObject mJSONObject = new JSONObject();
			try {
				//����json������������
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
			mAdapter.notifyDataSetChanged();// ֪ͨListView�������ѷ����ı�

			mEditTextContent.setText("");// ��ձ༭������

			mListView.setSelection(mListView.getCount() - 1);// ����һ����Ϣʱ��ListView��ʾѡ�����һ��
		}
	}

	/**
	 * ������Ϣʱ����ȡ��ǰ�¼�
	 * 
	 * @return ��ǰʱ��
	 */
	private String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date());
	}
	
	
	//������Ϣ�㲥
	private class MsgChangeReceiver extends BroadcastReceiver{
		JSONObject mJSONObject;
		ChatMsgEntity entity;
		
		// ��ȡĿ��ID
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
			//�ж��Ƿ���Ϣ��ʾ�ڵ�ǰҳ��
			if(getLoginName().equals(loginName)){
				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();// ֪ͨListView�������ѷ����ı�
				mListView.setSelection(mListView.getCount() - 1);// �յ�һ����Ϣʱ��ListView��ʾѡ�����һ��
			}
		}
	}
}