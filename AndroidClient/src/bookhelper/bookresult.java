package com.stdu.edu.italk.bookhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.stdu.edu.italk.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class bookresult extends ListActivity
{
	private TextView text;
	String name = "";
	String lendnum = "";
	String cannum = "";
	String publish = "";
	String location = "";
	String author = "";
	String mac = "";
	String call_no = "";
	private EditText booknamEditText;
	private Button searchButton;
	private ListView lv;
	private TextView resultTextView;
	String strs = "11";
	String result = "";
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> map1 = new HashMap<String, Object>();

	Handler lsr1 = new Handler()
	{

		@Override
		public void handleMessage(Message msg)// 重写方法
		{

			// String password = pwd1.getText().toString().trim();
			list = new ArrayList<Map<String, Object>>();
			ArrayList<HashMap<String, Object>> a = (ArrayList<HashMap<String, Object>>) msg.obj;

			SimpleAdapter mSimpleAdapter = new SimpleAdapter(bookresult.this,
					a, R.layout.item, new String[]
					{ "itemj", "itemh", "itemp", "booknum", "bookplace",
							"borrownum", "location" }, new int[]
					{ R.id.itemj, R.id.itemh, R.id.itemp, R.id.booknum,
							R.id.bookplace, R.id.borrownum, R.id.location });

			lv.setAdapter(mSimpleAdapter);
			if (result.length() == 2)
			{
				Toast.makeText(bookresult.this, "对不起，暂无此图书！",
						Toast.LENGTH_SHORT).show();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		Intent intentb = getIntent();
		String bookp = intentb.getStringExtra("one");

		final ProgressDialog progressDialog = new ProgressDialog(
				bookresult.this);
		progressDialog.setTitle("网络连接");
		progressDialog.setMessage("正在搜索，请稍等......");
		progressDialog.setIndeterminate(true);
		// progressDialog=ProgressDialog.show(clswdy.this,
		// "网络连接","正在验证，请稍等......",true,true);
		progressDialog.setButton("取消", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int i)
			{
				progressDialog.cancel();

			}
		});
		progressDialog
				.setOnCancelListener(new DialogInterface.OnCancelListener()
				{
					public void onCancel(DialogInterface dialog)
					{
					}
				});
		progressDialog.show();

		searchButton = (Button) findViewById(R.id.search);
		lv = (ListView) findViewById(android.R.id.list);
		// resultTextView=(TextView)findViewById(R.id.resultry);
		booknamEditText = (EditText) findViewById(R.id.bokname);
		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				Intent intentb = getIntent();
				String bookp = intentb.getStringExtra("one");
				arr(bookp);
				progressDialog.dismiss();
				Message msg = lsr1.obtainMessage();
				msg.obj = list;
				lsr1.sendMessage(msg);
			}
		}).start();
		searchButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				final ProgressDialog progressDialog = new ProgressDialog(
						bookresult.this);
				progressDialog.setTitle("网络连接");
				progressDialog.setMessage("正在搜索，请稍等......");
				progressDialog.setIndeterminate(true);
				// progressDialog=ProgressDialog.show(clswdy.this,
				// "网络连接","正在验证，请稍等......",true,true);
				progressDialog.setButton("取消",
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int i)
							{
								progressDialog.cancel();

							}
						});
				progressDialog
						.setOnCancelListener(new DialogInterface.OnCancelListener()
						{
							public void onCancel(DialogInterface dialog)
							{
							}
						});
				progressDialog.show();
				new Thread(new Runnable()
				{

					@Override
					public void run()
					{
						Log.i("Thread11111", "into Thread");
						// TODO Auto-generated method stub
						// 查询手机号码（段）信息

						((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
								.hideSoftInputFromWindow(bookresult.this
										.getCurrentFocus().getWindowToken(),
										InputMethodManager.HIDE_NOT_ALWAYS);

						String bookp = booknamEditText.getText().toString()
								.trim();
						arr(bookp);
						progressDialog.dismiss();
						Message msg = lsr1.obtainMessage();

						msg.obj = list;
						lsr1.sendMessage(msg);
					}
				}).start();

			}

		});
	}

	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		if (position != -1)
		{
			text = (TextView) v.findViewById(R.id.location);
			String string1 = text.getText().toString().trim();
			// String lString= arr1(string1);
			// System.out.println(lString);
			// Toast.makeText(bookresult.this, lString,
			// Toast.LENGTH_SHORT).show();
			// System.out.println("aaaaaaaaaaaa"+string1);
			// System.out.println("aaaaaaaaaaaa"+mac);
			// System.out.println("aaaaaa");
			Intent intent = new Intent();
			intent.putExtra("BOOKNUM", string1);
			intent.setClass(bookresult.this, bookdetialmessage.class);
			bookresult.this.startActivity(intent);
		}

	}

	// 访问webservice

	public void arr(String bookp)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		List<String> tushu = new ArrayList<String>();
		// String nameSpace = "http://WebXml.com.cn/";
		String nameSpace = "http://common/";
		String methodName = "TitleQuery";
		String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";
		String soapAction = "http://common/TitleQuery";
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		rpc.addProperty("arg0", bookp);
		// rpc.addProperty("password",password);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;

		envelope.setOutputSoapObject(rpc);

		System.out.println("rpc:" + rpc);
		System.out.println("enevlope:" + envelope);
		System.out.println("××基××本服务设置完毕，下面开始调用服务");
		try
		{
			HttpTransportSE transport = new HttpTransportSE(endPoint);
			System.out.println("××2×dsdasda");
			transport.call(null, envelope);
			System.out.println("pokoooooooooooooooxxxxx");
			// if(envelope.getResponse()!=null)
			// {
			// SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，
			// 该对象就代表了WebService的返回消息。
			// WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject
			SoapObject object = (SoapObject) envelope.bodyIn;
			System.out.println("××5××获得服务数据成功");
			int count = object.getPropertyCount();
			System.out.println(count);
			result = object.getProperty(0).toString();
			JSONArray arr = new JSONArray(result);
			for (int i = 0; i < arr.length(); i++)
			{
				JSONObject temp = (JSONObject) arr.get(i);
				name = temp.getString("m_title");
				publish = temp.getString("m_publisher");
				author = temp.getString("m_author");
				lendnum = temp.getString("bookitemnum");
				cannum = temp.getString("can_lend_num");
				call_no = temp.getString("m_call_no");
				mac = temp.getString("marc_rec_no");
				System.out.println("asdffff" + mac);
				// if(i==1)
				// {
				// location=arr1(mac);
				// }
				map = new HashMap<String, Object>();

				map.put("itemj", name);
				map.put("itemh", publish);
				map.put("itemp", author);
				map.put("booknum", lendnum);
				map.put("bookplace", call_no);
				map.put("borrownum", cannum);
				map.put("location", mac);

				list.add(map);
			}

			// }
			/*
			 * else { result="qq"; }
			 */

			System.out.println("××3××调用webservice服务成功");
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("××4××调用webservice服务失败");
		}

	}

	/*
	 * private List<Map<String, Object>> getData() { List<Map<String, Object>>
	 * list = new ArrayList<Map<String, Object>>(); Map<String, Object> map =
	 * new HashMap<String, Object>(); dbHelper=new
	 * MyDatabaseHelper(this,"mv.db",null,1); SQLiteDatabase
	 * db=dbHelper.getWritableDatabase(); Cursor cur = db.query("Wei", null,
	 * null,null, null, null,null); while (cur.moveToNext()) { for (int i = 0; i
	 * < cur.getCount(); i++) { cur.moveToPosition(i);
	 * 
	 * String time =cur.getString(1); SimpleDateFormat sdf=new
	 * SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * try { date=sdf.parse(time); } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } //
	 * System.out.println("date="+date.toString()); String
	 * b=date.toString().substring(0,3);
	 * 
	 * String weight = cur.getString(2); map = new HashMap<String, Object>();
	 * map.put("time", time); map.put("weight", weight); list.add(map); } }
	 * return list; }
	 */
	/*
	 * private void parseJsonMulti(String strResult) { try {
	 * System.out.println("hahahahhah"); JSONArray jsonObjs = new
	 * JSONObject(strResult).getJSONArray(strResult);
	 * System.out.println("hahahahhah1"); for(int i = 0; i < jsonObjs.length() ;
	 * i++){ JSONObject jsonObj = ((JSONObject)jsonObjs.opt(i))
	 * .getJSONObject("");
	 * 
	 * name = jsonObj.getString("bookitemnum"); System.out.println(name);
	 * 
	 * }
	 * 
	 * } catch (JSONException e) { System.out.println("Jsons parse error !");
	 * e.printStackTrace(); } }
	 */
	public String arr1(String mac)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		String result1 = "";
		String macs = "";
		// String nameSpace = "http://WebXml.com.cn/";
		String nameSpace = "http://common/";
		String methodName = "MarcQuery";
		String endPoint = "http://jwservice.stdu.edu.cn/LibQueryWebServ/libqueryPort?WSDL";
		String soapAction = "http://common/MarcQuery";
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		rpc.addProperty("arg0", mac);
		// rpc.addProperty("password",password);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;

		envelope.setOutputSoapObject(rpc);

		System.out.println("rpc:" + rpc);
		System.out.println("enevlope:" + envelope);
		System.out.println("××基××本服务设置完毕，下面开始调用服务");
		try
		{
			HttpTransportSE transport = new HttpTransportSE(endPoint);
			System.out.println("××2×dsdasda");
			transport.call(null, envelope);
			System.out.println("pokoooooooooooo");
			// if(envelope.getResponse()!=null)
			// {
			// SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，
			// 该对象就代表了WebService的返回消息。
			// WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject

			SoapObject object = (SoapObject) envelope.bodyIn;
			System.out.println("××5××获得服务数据成功");
			int count = object.getPropertyCount();
			System.out.println(count);
			result1 = object.getProperty(0).toString();
			JSONArray arr1 = new JSONArray(result1);
			for (int i = 0; i < 1; i++)
			{
				JSONObject temp = (JSONObject) arr1.get(0);
				macs = temp.getString("location");
			}

			// }

			System.out.println("××3××调用webservice服务成功");
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("××4××调用webservice服务失败");
		}

		return macs;
	}

}
