package com.stdu.edu.italk.bookhelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.couser.Kebiao;

public class MainActivity extends Activity implements OnClickListener {
	public String num =" ";
	public ImageButton imagebutton1=null;
	public ImageButton imagebutton2=null;
	public ImageButton imagebutton3=null;
	public ImageButton imagebutton4=null;
	public ImageButton imagebutton5=null;
	public ImageButton imagebutton6=null;
	public EditText bookname;
	public Button search;
	public TextView runlight;
	public Button zhuxiao;
    public TextView loginname;
    ImageView back;
	TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.menu);
        bookname = (EditText)findViewById(R.id.search_edit_username);
        bookname.setHint("请输入书名进行查询");
        back = (ImageView) findViewById(R.id.Image_left_course);
      		back.setBackground(getResources().getDrawable(R.drawable.jiantouback));
      		title = (TextView) findViewById(R.id.textview_center_course);
      		back.setOnClickListener(this);
      		title.setText("图书馆助手");
        imagebutton1 = (ImageButton)findViewById(R.id.imageButton1);
        imagebutton1.setOnClickListener(new imageButton1Listener());
        imagebutton2 = (ImageButton)findViewById(R.id.imageButton2);
        imagebutton2.setOnClickListener(new imageButton2Listener());
        imagebutton3 = (ImageButton)findViewById(R.id.imageButton3);
        imagebutton3.setOnClickListener(new imageButton3Listener());
        imagebutton4 = (ImageButton)findViewById(R.id.imageButton4);
        imagebutton4.setOnClickListener(new imageButton4Listener());
        imagebutton5 = (ImageButton)findViewById(R.id.imageButton5);
        imagebutton5.setOnClickListener(new imageButton5Listener());
        imagebutton6 = (ImageButton)findViewById(R.id.imageButton6);
        imagebutton6.setOnClickListener(new imageButton6Listener());
        search=(Button)findViewById(R.id.search_button);
        search.setOnClickListener(new searchButton());
//        runlight = (TextView)findViewById(R.id.runlight);
//        runlight.requestFocus();
//        zhuxiao = (Button)findViewById(R.id.zhuxiao);
//        zhuxiao.setOnClickListener(new zhuxiaoButtonListener());
//        //取得用户名
//       loginname = (TextView)findViewById(R.id.loginname);
//       Intent intent=getIntent();  
//       Bundle bundle=intent.getExtras();  
//       String str=bundle.getString("KEY_USERNAME");
//       num=bundle.getString("KEY_NUM");
//       loginname.setText(str+"您好");
//       System.out.println("name"+str);
       
    }
    class imageButton1Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Intent intent1=getIntent();  
	        Bundle bundle=intent1.getExtras();  
	        String str=bundle.getString("KEY_USERNAME");
	        System.out.println("aaa"+str);
			Intent intent =new Intent();
			intent.putExtra("USERNAME", str);
			intent.setClass(MainActivity.this,ruguan.class);
			MainActivity.this.startActivity(intent);
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    class searchButton implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
//			
		    	String Book= bookname.getText().toString();
		    	if((Book != null && !Book.isEmpty() ))
		    	{
		    	Intent intent = new Intent();
		    	intent.putExtra("one",Book);
		    	intent.setClass(MainActivity.this, bookresult.class);
				MainActivity.this.startActivity(intent);
		    	}
		    	else
		    	{
		    		Toast.makeText(MainActivity.this, "图书名字不能为空！", Toast.LENGTH_SHORT).show();
		    	}
//				
		}
    	
    }
    class imageButton2Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Intent intent1=getIntent();  
	        Bundle bundle=intent1.getExtras();  
	        String str=bundle.getString("KEY_NUM");
	        System.out.println("aaa"+str);
			Intent intent =new Intent();
			intent.putExtra("USERNAME", str);
			intent.setClass(MainActivity.this,borrowmessage.class);
			MainActivity.this.startActivity(intent);
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    class imageButton3Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Intent intent1=getIntent();  
	        Bundle bundle=intent1.getExtras();  
	        String str=bundle.getString("KEY_USERNAME");
	        System.out.println("aaa"+str);
			Intent intent =new Intent();
			intent.putExtra("USERNAME", str);
			intent.setClass(MainActivity.this,advice.class);
			MainActivity.this.startActivity(intent);
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    class imageButton4Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "暂无此功能！", Toast.LENGTH_SHORT).show();
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    class imageButton5Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "暂无此功能！", Toast.LENGTH_SHORT).show();
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    class imageButton6Listener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "暂无此功能！", Toast.LENGTH_SHORT).show();
			//实现页面跳转并把用户名封装入USERNAME
		}
    
    }
    //点击注销之后，将原有用户名密码清空，跳转到登录界面
    class zhuxiaoButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			//TODO Auto-generated method stub
			SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
			//SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.remove("USER_NAME");
			editor.remove("PASSWORD");
			editor.remove("KEY_USERNAME");
			//SharedPreferences.Editor editor1 = sp1.edit();
			//editor1.remove("username");
			//editor1.remove("pass");
			editor.commit();
			Intent intent2 =new Intent(MainActivity.this,Kebiao.class);
            startActivity(intent2);
            finish();
		}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);}

//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    //按两次返回键直接退出
//    private long exitTime = 0;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
//            if((System.currentTimeMillis()-exitTime) > 2000){  
//                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
//                exitTime = System.currentTimeMillis();   
//            } else {
//               finish();
//            }
//            return true;   
//        }
//        return super.onKeyDown(keyCode, event);
//   }

    @Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Image_left_course:
			finish();
			break;
		}
	}

}
