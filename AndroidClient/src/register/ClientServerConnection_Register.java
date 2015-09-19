package com.stdu.edu.italk.register;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.fragment.MyFragmentActivity;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

//发送数据至服务器存储，将个人信息本地存储
public class ClientServerConnection_Register extends
		AsyncTask<String, Void, String> {

	Context context;

	public ClientServerConnection_Register(Context context) {
		this.context = context;
	}

	@Override
	protected String doInBackground(String... params) {
		// 存储数据至本地
		// 创建DatabaseHelper对象
		SchoolFriSQLiteOpenHelper dbHelper = new SchoolFriSQLiteOpenHelper(
				context, "dbLocal", null, 1);
		
		//清空数据
		SQLiteDatabase dbDelete = dbHelper.getWritableDatabase();
		dbDelete.execSQL("delete from person");
		
		//插入数据
		SQLiteDatabase dbInsert = dbHelper.getWritableDatabase();
		String sql = "insert into person(loginName,loginPassword,name,ganQing,xinBie,yuanXi,nianJi) values ("
				+"'"+ params[0]+"'"+","
				+"'"+ params[1]+"'"+","
				+"'"+params[2] +"'"+","+"'"+params[3] +"'"+","+"'"+params[4] +"'"+","+"'"+params[5] +"'"+","+"'"+params[6] +"'"+ ")";
		dbInsert.execSQL(sql);
		UserData data=(UserData) context.getApplicationContext();
		// post发送数据至服务器
		HttpClient httpClient = new DefaultHttpClient();
		String result = null;
		data = (UserData) context.getApplicationContext();
		String uri = data.getIP()+"/ITalkServer/Register.do";
		HttpPost httpPost = new HttpPost(uri);
		NameValuePair userName = new BasicNameValuePair("userName", params[0]);
		NameValuePair passWord = new BasicNameValuePair("LoginPassword",
				params[1]);
		NameValuePair name = new BasicNameValuePair("name", params[2]);
		NameValuePair ganQing = new BasicNameValuePair("ganQing", params[3]);
		NameValuePair xinBie = new BasicNameValuePair("xinBie", params[4]);
		NameValuePair yuanXi = new BasicNameValuePair("yuanXi", params[5]);
		NameValuePair nianJi = new BasicNameValuePair("nianJi", params[6]);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(passWord);
		list.add(userName);
		list.add(ganQing);
		list.add(name);
		list.add(nianJi);
		list.add(xinBie);
		list.add(yuanXi);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
			// 获取相应数据
			HttpResponse response = httpClient.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@Override
	protected void onPostExecute(String result) {
		if (result.equals("true")) {
			Intent intent = new Intent(context, MyFragmentActivity.class);
			context.startActivity(intent);
			((Activity) context).overridePendingTransition(
					R.anim.in_from_right, R.anim.out_from_left);
		} else if (result.equals("false")) {
			Toast.makeText(context, "该昵称已被注册", Toast.LENGTH_SHORT).show();
		}
		System.out.println(result);
	}
}
