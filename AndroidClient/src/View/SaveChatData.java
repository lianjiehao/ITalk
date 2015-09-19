package com.stdu.edu.italk.View;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;

public class SaveChatData extends AsyncTask<String,Void,Map <String,String>> {
	Context context;
	int tag=0;
	Map <String,String>map;

	public SaveChatData(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected Map <String,String> doInBackground(String... params) {
		map= new HashMap<String, String>();
		// 保存聊天list
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		SQLiteDatabase sqliteInsert = dbHelper.getWritableDatabase();
		SQLiteDatabase sqliteSelect = dbHelper.getWritableDatabase();
		Cursor cursor = sqliteSelect.rawQuery("select name from chatList", null);
		if (cursor.moveToFirst()) {
			for (int j = 0; j < cursor.getCount(); j++) {
				cursor.moveToPosition(j);
				// 获取昵称
				String name = cursor.getString(0);
				if(name.equals(params[0])){
					tag=1;
			 }
		   }
		}
		
		if(tag==0){
		sqliteInsert.execSQL("insert into chatList(name,belongs,toLoginName) values (?,?,?)",new String[]{params[0],params[1],params[2]});
		}
		
		//获取登录名
		String loginName=null;
		String name=null;
		SQLiteDatabase sqliteSelect1 = dbHelper.getWritableDatabase();
		Cursor cursor1 = sqliteSelect.rawQuery("select * from friend where name=?", new String[]{params[0]});
		if (cursor1.moveToFirst()) {
			for (int j = 0; j < cursor1.getCount(); j++) {
				cursor1.moveToPosition(j);
				//获取登陆名
				loginName = cursor1.getString(1);
				//获取昵称
				name=cursor1.getString(2);
				map.put("loginName", loginName);
				map.put("name", name);
			}
		}
		return map;
	}

	@Override
	protected void onPostExecute(Map<String,String> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Intent intent=new Intent(context,ChatActivity.class);
		intent.putExtra("name",result.get("name"));
		intent.putExtra("loginName", result.get("loginName"));
		context.startActivity(intent);
	}
	
}
