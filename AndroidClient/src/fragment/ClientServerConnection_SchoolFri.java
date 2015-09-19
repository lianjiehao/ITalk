package com.stdu.edu.italk.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.View.friendpager;
import com.stdu.edu.italk.adapter.LumpShowListAdapter;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;
import com.stdu.edu.italk.resource.LumpShow;


//����У�����ݣ����洢���������ݿ�
public class ClientServerConnection_SchoolFri extends
		AsyncTask<String, Void, List> {

	Context context;
	private List<LumpShow> LumpSeacherList = new ArrayList<LumpShow>();
	private ListView schoolFriendList;
	UserData data;
	String strLoginName;
	
	public ClientServerConnection_SchoolFri(Context context,ListView schoolFriendList ) {
		this.context = context;
		this.schoolFriendList = schoolFriendList;
	}

	@Override
	protected List doInBackground(String... params) {
		// post����������������
		HttpClient httpClient = new DefaultHttpClient();
		String str = null;
		List<String> result = new ArrayList<String>();
		data = (UserData) context.getApplicationContext();
		String uri = data.getIP()+"/ITalkServer/SchoolFri.do";
		HttpPost httpPost = new HttpPost(uri);
		NameValuePair userNameParam = new BasicNameValuePair("LoginName",
				params[0]);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(userNameParam);
		try {
			// ��װ����
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			// �����󲢻�ȡ��Ӧ����
			HttpResponse response = httpClient.execute(httpPost);
			str = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		savaShoolFriData(str);
		return addTOListView();
	}
	
	
	
	//���������������list��
	public List addTOListView(){
		//���������������List��
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		// ��ѯ����
		SQLiteDatabase sqliteSelect = dbHelper.getWritableDatabase();
		Cursor cursor = sqliteSelect.rawQuery("select * from friend", null);
		if (cursor.moveToFirst()) {
			for (int j = 0; j < cursor.getCount(); j++) {
				cursor.moveToPosition(j);
				// ��ȡ��¼��
				String loginName = cursor.getString(1);
				// ��ȡ�ǳ�
				String name = cursor.getString(2);
				
				LumpSeacherList.add(new LumpShow(name + "@" + loginName,
						"2����ǰ", "0.1km"));
			}
		}
		System.out.println(cursor.getCount());
		if (sqliteSelect != null) {
			sqliteSelect.close();
		}
		if (dbHelper != null) {
			dbHelper.close();
		}
		if(cursor!=null)
			cursor.close();
		return LumpSeacherList;
		
	}

	// ������������
	public void savaShoolFriData(String str) {
		String strName;
		String strGanQing;
		String strXinBie;
		String strYuanXi;
		String strNianJi;
		int tag;
		// ����DatabaseHelper����
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		// �������
		SQLiteDatabase dbDelete = dbHelper.getWritableDatabase();
		dbDelete.execSQL("delete from friend");

		// ��������
		SQLiteDatabase sqliteInsert = dbHelper.getWritableDatabase();
		JSONArray array;
		try {
			array = new org.json.JSONArray(str);
			for (int i = 0; i < array.length(); i++) {
				strLoginName = array.getJSONObject(i).getString("loginName");
				strName = array.getJSONObject(i).getString("name");
				strGanQing =array.getJSONObject(i).getString("ganQing");
				strXinBie =array.getJSONObject(i).getString("xinBie");
				strYuanXi =array.getJSONObject(i).getString("yuanXi");
				strNianJi =array.getJSONObject(i).getString("nianJi");
				
				String strInsert = "insert into friend (loginName,name,ganQing,xinBie,yuanXi,nianJi) values ("
						+ "'" + strLoginName + "'" + ","+"'" + strName + "'"+","+"'"+strGanQing+"'"+","+"'"+strXinBie+"'"+","+"'"+strYuanXi+"'"+","+"'"+strNianJi+"')";
				sqliteInsert.execSQL(strInsert);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		
		finally{
			if(dbHelper!=null)
				dbHelper.close();
			if(dbDelete!=null)
				dbDelete.close();
			if(sqliteInsert!=null)
				sqliteInsert.close();
		}
	}

	@Override
	protected void onPostExecute(List result) {
		View view;
		LumpShowListAdapter adapter = new LumpShowListAdapter(context,
				R.layout.schoolfriendlump, result);
		schoolFriendList.setAdapter(adapter);
		schoolFriendList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				LumpShow lump = LumpSeacherList.get(position);
				String str = lump.getXueYuan().substring(0,lump.getXueYuan().indexOf("@"));
				Intent intent = new Intent(context, friendpager.class);
				intent.putExtra("name", str);
				intent.putExtra("loginName", strLoginName);
				context.startActivity(intent);
			}
		});
		
	}


}
