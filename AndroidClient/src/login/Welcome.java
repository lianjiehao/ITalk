package com.stdu.edu.italk.login;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * 开场图片
 * 
 * @author xianming 2015-07-05
 */
public class Welcome extends Activity {

	private ImageView img;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Welcome.this, LoginPager.class);
				startActivity(intent);
				finish();
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
				// 防iphone退出动画
				// overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
			}
		}, 2500);
	}
}
