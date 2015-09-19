package com.stdu.edu.italk.fragment;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;

/**
 * Fragment�����л�����
 * 
 * @author xianming
 *
 */
public class MyFragmentActivity extends FragmentActivity implements
		OnClickListener {

	LinearLayout linSchoolFri;
	LinearLayout LinNews;
	LinearLayout LinFind;
	ImageButton imgSchoolFri;
	ImageButton imgNews;
	ImageButton imgFind;
	ImageView imageLeft;
	Fragment TabSchoolFri;
	Fragment TabNews;
	Fragment TabFind;
	Fragment temp=null;
	ImageButton imageButtonSeacher;
	TextView textViewCenter;
	View popWindow;
	LinearLayout pop_canver;
	public static boolean isVisible = false;
	View viewChild;
	public static int tag=0;
	static int x=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_main);
		loadview();
		initview();
		setselect(0);
	}

	@Override
	protected void onResume() {
				imageButtonSeacher = (ImageButton) findViewById(R.id.imag_right);
				if(x==0)
				imageButtonSeacher.setImageResource(R.drawable.search_nopress);
				else if(x==1)
				imageButtonSeacher.setImageResource(R.drawable.more_nopress);
		super.onResume();
	}
	
	// ��ʼ���ؼ�
	public void loadview() {
		linSchoolFri = (LinearLayout) findViewById(R.id.linearlayout_left);
		LinNews = (LinearLayout) findViewById(R.id.linearlayout_center);
		LinFind = (LinearLayout) findViewById(R.id.linearlayout_right);

		imgSchoolFri = (ImageButton) findViewById(R.id.imagebutton_left);
		imgNews = (ImageButton) findViewById(R.id.imagebutton_center);
		imgFind = (ImageButton) findViewById(R.id.imagebutton_right);

		imageButtonSeacher = (ImageButton) findViewById(R.id.imag_right);
		textViewCenter = (TextView) findViewById(R.id.textview_center);
		imageLeft=(ImageView)findViewById(R.id.Image_left);
	}

	// ��������¼�
	public void initview() {
		linSchoolFri.setOnClickListener(this);
		LinNews.setOnClickListener(this);
		LinFind.setOnClickListener(this);
		imgSchoolFri.setOnClickListener(this);
		imgNews.setOnClickListener(this);
		imgFind.setOnClickListener(this);
	}

	// �����¼�����
	public void mClick() {
		imageButtonSeacher.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:{
					// TODO Auto-generated method stub
					pop_canver = (LinearLayout) findViewById(R.id.pop_canver);
					popWindow = (View)findViewById(R.id.ll_popup_window);
					if (isVisible) {
						// ����pop_canvers�Լ�popwindow
						pop_canver.setVisibility(View.GONE);
						popWindow.setVisibility(View.GONE);
						// ������������
						imageButtonSeacher.setImageResource(R.drawable.search_press_down);
						
					} else{
						pop_canver.setVisibility(View.VISIBLE);
						popWindow.setVisibility(View.VISIBLE);
						// ������������
						imageButtonSeacher.setImageResource(R.drawable.search_nopress_down);
						
					}
					break;
				}
				case MotionEvent.ACTION_UP:{
					Handler handler=new Handler();
					if (isVisible) {
						handler.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								imageButtonSeacher.setImageResource(R.drawable.search_nopress);
								isVisible = false;
							}
						}, 50);
					}
					else
					{
	             handler.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								imageButtonSeacher.setImageResource(R.drawable.search_press);
								isVisible = true;
							}
						}, 50);
					}
					break;
				}
				}
				
				return true;
			}
		});
	}

	public void setselect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// ��ͼƬ����Ϊ����
		// ������������
		switch (i) {
		case 0:
			TabSchoolFri = new SchoolFriFragment();
			transaction.add(R.id.id_content, TabSchoolFri);
			// ���µײ�ͼƬ
			imgSchoolFri.setImageResource(R.drawable.schoolfriend_press);
			// ������������
			imageButtonSeacher.setImageResource(R.drawable.search_nopress);
			imageLeft.setImageResource(R.drawable.paobaoying);
			if(temp!=null)
			transaction.remove(temp);
			temp=TabSchoolFri;
			//��������¼�
			mClick();
			// ������ʾ
			textViewCenter.setText("ͬУ����");
			x=0;
			break;
		case 1:
			TabNews = new ChatFragment();
			transaction.add(R.id.id_content, TabNews);
		
			// ���µײ�ͼƬ
			imgNews.setImageResource(R.drawable.news_press);
			// �����ࡱ����,����ontouch�¼�
			imageButtonSeacher.setImageResource(R.drawable.more_nopress);
			imageButtonSeacher.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			imageLeft.setImageResource(R.drawable.xitongtishi);
			if(temp!=null)
			transaction.remove(temp);
			temp=TabNews;
			// ������ʾ
			textViewCenter.setText("��Ϣ");
			x=1;
			break;
		case 2:
			TabFind = new FindFragment();
			transaction.add(R.id.id_content, TabFind);
			
			// ���µײ�ͼƬ
			imgFind.setImageResource(R.drawable.find_press);
			transaction.remove(temp);
			temp=TabFind;
			// ������ʾ
			textViewCenter.setText("����");
			
			//����ontoucn��onclick�¼���Ӧ
			imageLeft.setImageResource(R.drawable.nullimage);
			imageLeft.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			imageButtonSeacher.setImageResource(R.drawable.nullimage);
			imageButtonSeacher.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			imageButtonSeacher.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			break;
		default:
			break;
		}

		transaction.commit();

	}

	// ע�����¼�
	@Override
	public void onClick(View V) {
		resetImgs();
		switch (V.getId()) {
		case R.id.linearlayout_left: {
			setselect(0);
			break;

		}
		case R.id.linearlayout_center: {
			setselect(1);
			break;
		}
		case R.id.linearlayout_right: {
			setselect(2);
			break;
		}
		
		case R.id.imagebutton_left: {
			setselect(0);
			break;

		}
		case R.id.imagebutton_center: {
			setselect(1);
			break;
		}
		case R.id.imagebutton_right: {
			setselect(2);
			break;
		}
		
		default:
			break;
		}
	}

	// ��������fragment
	private void hideFragment(FragmentTransaction transaction) {
		if (TabSchoolFri != null) {
			transaction.hide(TabSchoolFri);
		}
		if (TabNews != null) {
			transaction.hide(TabNews);
		}
		if (TabFind != null) {
			transaction.hide(TabFind);
		}
	}

	/**
	 * �л�ͼƬ����ɫ
	 */
	private void resetImgs() {
		imgSchoolFri.setImageResource(R.drawable.schoolfriend_nopress);
		imgNews.setImageResource(R.drawable.news_nopress);
		imgFind.setImageResource(R.drawable.find_nopress);
	}

}
