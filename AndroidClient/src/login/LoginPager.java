package com.stdu.edu.italk.login;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.R.drawable;
import com.stdu.edu.italk.R.id;
import com.stdu.edu.italk.R.layout;
import com.stdu.edu.italk.fragment.MyFragmentActivity;
import com.stdu.edu.italk.register.RegisterBaseData;
import com.stdu.edu.italk.login.ClientServerConnection_Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * µÇÂ¼Ò³
 * 
 * @author xianming 2015-07-06
 */
public class LoginPager extends Activity {
	private Button login;
	private TextView register;
	private EditText editUserName;
	private EditText editPassword;
	Intent intentRegister;
	private TextView textVieworgetPassword;
	Intent intentForgetPassword;
	Intent intentFragment;
	
	String userName;
	String mpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loginpager);
		login = (Button) findViewById(R.id.login);
		editPassword=(EditText) findViewById(R.id.password);
		editUserName=(EditText) findViewById(R.id.usersname);
		

		// µÇÂ¼Ìø×ªÊÂ¼þ
		login.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					login.setBackgroundResource(R.drawable.login_2);
					break;
				}
				case MotionEvent.ACTION_UP: {
					userName=editUserName.getText().toString();
					mpassword=editPassword.getText().toString();
					if((!userName.equals(""))&&(!mpassword.equals(""))){
					ClientServerConnection_Login connection=new ClientServerConnection_Login(LoginPager.this);
					login.setBackgroundResource(R.drawable.login_1);
					connection.execute(userName,mpassword);
					}
					break;
				}
				}
				return true;
			}

		});
	}
}
