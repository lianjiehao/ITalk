package com.stdu.edu.italk.View;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;
import com.stdu.edu.italk.resource.LumpShow;

public class SetFriendPagerData extends AsyncTask<String,Void,Map<String,String>> {

	Context context;
	TextView ganQing;
	int tag=0;
	
	public SetFriendPagerData(Context context ,TextView ganQing) {
		super();
		this.context = context;
		this.ganQing=ganQing;
	}

	public Map<String,String> getData(final String name){
		String strGanQing=null;
		String loginName=null;
		Map<String,String> map = new HashMap<String,String>();
		//将本地数据添加至List中
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		// 查询数据
		SQLiteDatabase sqliteSelect = dbHelper.getWritableDatabase();
		Cursor cursor = sqliteSelect.rawQuery("select * from friend where name=?", new String[]{name});
		if (cursor.moveToFirst()) {
			for (int j = 0; j < cursor.getCount(); j++) {
				cursor.moveToPosition(j);
				// 获取感情
				strGanQing = cursor.getString(3);
				//获取登陆名
				loginName = cursor.getString(1);
				map.put("strGanQing", strGanQing);
				map.put("loginName", loginName);
			}
		}
		return map;
}

	@Override
	protected Map<String,String> doInBackground(String... params) {
		return getData(params[0]);
	}

	
	@Override
	protected void onPostExecute(Map<String,String> result) {
		ganQing.setText(result.get("strGanQing"));
	}
	
}
