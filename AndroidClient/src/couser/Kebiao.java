package com.stdu.edu.italk.couser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.color;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.resource.Course;

public class Kebiao extends Activity implements OnClickListener {

	int start;
	int end;
	String start1 = "";
	String end1 = "";
	String time = "";
	String Space = "";
	String name = "";
	String number = "";
	String week = "";
	String teacher = "";
	String week1 = "����һ";
	String week2 = "���ڶ�";
	String week3 = "������";
	String week4 = "������";
	String week5 = "������";
	LinearLayout weekPanels[] = new LinearLayout[7];
	List courseData[] = new ArrayList[7];
	List al[] = new ArrayList[7];
	int itemHeight;
	int marTop, marLeft;
	String result = "";
	UserData data;
	ImageView back;
	TextView title;
	Handler lsr1 = new Handler() {

		@Override
		public void handleMessage(Message msg)// ��д����
		{
			al = (List[]) msg.obj;

			for (int i = 0; i < weekPanels.length; i++) {
				weekPanels[i] = (LinearLayout) findViewById(R.id.weekPanel_1
						+ i);
				initWeekPanel(weekPanels[i], al[i]);
			}
			// String password = pwd1.getText().toString().trim();

		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.kebiao);
		itemHeight = getResources().getDimensionPixelSize(
				R.dimen.weekItemHeight);
		marTop = getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
		marLeft = getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);
		back = (ImageView) findViewById(R.id.Image_left_course);
		back.setBackground(getResources().getDrawable(R.drawable.jiantouback));
		title = (TextView) findViewById(R.id.textview_center_course);
		back.setOnClickListener(this);
		title.setText("�ҵĿα�");
		new Thread(new Runnable() {

			@Override
			public void run() {
				data = (UserData) getApplication();
				getremoteInfo(data.getUserName(), data.getUserPassword());
				arr();
				
				Message msg = lsr1.obtainMessage();

				msg.obj = courseData;
				lsr1.sendMessage(msg);
			}
		}).start();
	}

	public void initWeekPanel(LinearLayout ll, List<Course> data) {
		if (ll == null || data == null || data.size() < 1)
			return;
		Log.i("Msg", "��ʼ�����");
		Course pre = data.get(0);
		for (int i = 0; i < data.size(); i++) {
			Course c = data.get(i);
			TextView tv = new TextView(this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT, itemHeight
							* c.getStep() + marTop * (c.getStep() - 1));
			if (i > 0) {
				lp.setMargins(marLeft,
						(c.getStart() - (pre.getStart() + pre.getStep()))
								* (itemHeight + marTop) + marTop, 0, 0);
			} else {
				lp.setMargins(marLeft, (c.getStart() - 1)
						* (itemHeight + marTop) + marTop, 0, 0);
			}
			tv.setLayoutParams(lp);
			tv.setGravity(Gravity.TOP);
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			tv.setTextSize(12);
			tv.setTextColor(getResources().getColor(R.color.courseTextColor));
			tv.setText(c.getName() + "\n" + c.getRoom() + "\n" + c.getTeach());
			tv.setBackgroundColor(getResources().getColor(R.color.classIndex));
			// �������0~1֮�����,�Ӷ������������ɫ
			int x = (int) (Math.random() * 10);
			if (x == 0)
				tv.setBackground(getResources().getDrawable(R.drawable.p1));
			else if (x == 1)
				tv.setBackground(getResources().getDrawable(R.drawable.p2));
			else if (x == 2)
				tv.setBackground(getResources().getDrawable(R.drawable.p3));
			else if (x == 3)
				tv.setBackground(getResources().getDrawable(R.drawable.p4));
			else if (x == 4)
				tv.setBackground(getResources().getDrawable(R.drawable.p5));
			else if (x == 5)
				tv.setBackground(getResources().getDrawable(R.drawable.p6));
			else if (x == 6)
				tv.setBackground(getResources().getDrawable(R.drawable.p7));
			else if (x == 7)
				tv.setBackground(getResources().getDrawable(R.drawable.p8));
			else if (x == 8)
				tv.setBackground(getResources().getDrawable(R.drawable.p9));
			else if (x == 9)
				tv.setBackground(getResources().getDrawable(R.drawable.p10));
			else if (x == 10)
				tv.setBackground(getResources().getDrawable(R.drawable.p1));
			ll.addView(tv);
			pre = c;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login_my, menu);
		return true;
	}

	public void arr() {
		// Inflate the menu; this adds items to the action bar if it is present.
		List<Course> list4 = new ArrayList<Course>();
		List<Course> list3 = new ArrayList<Course>();
		List<Course> list5 = new ArrayList<Course>();
		List<Course> list2 = new ArrayList<Course>();

		List<Course> list1 = new ArrayList<Course>();
		String nameSpace = "http://common/";
		String methodName = "getStuCourse";
		String endPoint = "http://jwservice.stdu.edu.cn/JWQueryWebService/jwqueryPort?wsdl";
		String soapAction = "http://common/getStuCourse";
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		rpc.addProperty("arg0", data.getUserName());
		rpc.addProperty("arg1", "2015");
		rpc.addProperty("arg2", "0");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;

		envelope.setOutputSoapObject(rpc);

		System.out.println("rpc:" + rpc);
		System.out.println("enevlope:" + envelope);
		System.out.println("����������������������ϣ����濪ʼ���÷���");
		try {
			HttpTransportSE transport = new HttpTransportSE(endPoint);
			System.out.println("����2��dsdasda");
			transport.call(null, envelope);
			System.out.println("pokoooooooooooooooxxxxx");
			// if(envelope.getResponse()!=null)
			// {
			// SoapSerializationEnvelope�����bodyIn���Է���һ��SoapObject����
			// �ö���ʹ�����WebService�ķ�����Ϣ��
			// WebService�ڷ������˷���ֵ��String���͵���ֵ��ʱ��ʹ��Object����SoapObject
			SoapObject object = (SoapObject) envelope.bodyIn;
			System.out.println("����5������÷������ݳɹ�");
			int count = object.getPropertyCount();
			System.out.println(count);
			result = object.getProperty(0).toString();
			JSONArray arr = new JSONArray(result);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				time = temp.getString("courseJc");
				Space = temp.getString("courseJs");
				name = temp.getString("courseName");
				teacher = temp.getString("courseTeacher");
				number = temp.getString("courseNo");
				week = temp.getString("courseXq");
				start1 = time.substring(0, 1);
				end1 = time.substring(2, 3);
				start = Integer.parseInt(start1);
				end = Integer.parseInt(end1);
				System.out.println(start);
				System.out.println(week);
				if (week.equals(week1)) {
					list1.add(new Course(name, teacher, start, 2, Space, number));
					// System.out.println(list1);
				}
				if (week.equals(week2)) {
					Course c1 = new Course(name, teacher, start, 2, Space,
							number);
					System.out.println(c1.getName());
					list2.add(c1);
				}
				if (week.equals(week3)) {
					list3.add(new Course(name, teacher, start, 2, Space, number));
				}
				if (week.equals(week4)) {
					list4.add(new Course(name, teacher, start, 2, Space, number));
				}
				if (week.equals(week5)) {
					list5.add(new Course(name, teacher, start, 2, Space, number));
				}

			}

			courseData[0] = list1;
			// System.out.println(courseData[0]);
			courseData[1] = list2;
			courseData[2] = list3;
			courseData[3] = list4;
			courseData[4] = list5;

			// }
			/*
			 * else { result="qq"; }
			 */

			System.out.println("����3��������webservice����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����4��������webservice����ʧ��");
		}

	}

	public void getremoteInfo(String user, String password) {
		// Inflate the menu; this adds items to the action bar if it is present.
		String nameSpace = "http://common/";
		String methodName = "AuthLogin";
		String endPoint = "http://jwservice.stdu.edu.cn/JWQueryWebService/jwqueryPort?wsdl";
		String soapAction = "http://common/AuthLogin";
		SoapObject rpc = new SoapObject(nameSpace, methodName);
		rpc.addProperty("arg0", user);
		rpc.addProperty("arg1", password);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false;

		envelope.setOutputSoapObject(rpc);
		String user11;
		String user111;

		System.out.println("rpc:" + rpc);
		System.out.println("enevlope:" + envelope);
		System.out.println("����2������������������ϣ����濪ʼ���÷���");

		try {
			HttpTransportSE transport = new HttpTransportSE(endPoint);

			System.out.println("����2��dsdasda");
			transport.call(null, envelope);

			System.out.println("allalla");
			// SoapSerializationEnvelope�����bodyIn���Է���һ��SoapObject����
			// �ö���ʹ�����WebService�ķ�����Ϣ��
			// WebService�ڷ������˷���ֵ��String���͵���ֵ��ʱ��ʹ��Object����SoapObject
			SoapObject object = (SoapObject) envelope.bodyIn;
			System.out.println("����5������÷������ݳɹ�");
			result = object.getProperty(0).toString();
			JSONObject stu = new JSONObject(result);
			user11 = stu.getString("realname");
			user111 = stu.getString("userid");

			System.out.println("����3��������webservice����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("����4��������webservice����ʧ��");
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Image_left_course:
			finish();
			break;
		}
	}

}
