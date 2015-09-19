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
		//���������������List��
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		// ��ѯ����
		SQLiteDatabase sqliteSelect = dbHelper.getWritableDatabase();
		Cursor cursor = sqliteSelect.rawQuery("select * from friend where name=?", new String[]{name});
		if (cursor.moveToFirst()) {
			for (int j = 0; j < cursor.getCount(); j++) {
				cursor.moveToPosition(j);
				// ��ȡ����
				strGanQing = cursor.getString(3);
				//��ȡ��½��
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
