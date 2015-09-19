package com.stdu.edu.italk.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.View.ChatActivity;
import com.stdu.edu.italk.adapter.LumpChatAdapter;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;
import com.stdu.edu.italk.resource.LumpChat;
import com.stdu.edu.italk.resource.LumpShow;

public class RefleshChatFragment extends AsyncTask<String,Void,String> {
	Context context;
	private ListView chatList;
	private List<LumpChat> LumpChatList = new ArrayList<LumpChat>();
	String toLoginName;

	public RefleshChatFragment(Context context,ListView chatList) {
		super();
		this.context = context;
		this.chatList=chatList;
	}

	@Override
	protected String doInBackground(String... params) {
		// 创建DatabaseHelper对象
				SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
						context, "dbLocal", null, 1);
				SQLiteDatabase sqliteSelect = dbHelper.getWritableDatabase();
				Cursor cursor = sqliteSelect.rawQuery("select * from chatList where belongs=?",new String []{params[0]});
				if (cursor.moveToFirst()) {
					for (int j = 0; j < cursor.getCount(); j++) {
						cursor.moveToPosition(j);
						// 获取昵称
						String name = cursor.getString(1);
						toLoginName = cursor.getString(3);
						LumpChatList.add(new LumpChat(name, "上午11:31", "亲密值:50", "么么哒~",
								R.drawable.getmessage_icon));
					}
				}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		LumpChatAdapter lumpAdapter = new LumpChatAdapter(context,
				R.layout.chatlump, LumpChatList);
		chatList.setAdapter(lumpAdapter);
		chatList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				LumpChat lump = LumpChatList.get(position);
				String str = lump.getByName();
				Intent intent = new Intent(context, ChatActivity.class);
				intent.putExtra("name", str);
				intent.putExtra("loginName", toLoginName);
				context.startActivity(intent);
			}
		});
		
	}
	
	
	

}
