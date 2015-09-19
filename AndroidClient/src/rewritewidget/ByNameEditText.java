package com.stdu.edu.italk.rewritewidget;

import android.content.Context;
import android.database.Cursor;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * Í«≥∆ ‰»ÎøÚ÷ÿ–¥
 * 
 * @author xianming 2015-7-11
 */

public class ByNameEditText extends EditText {

	private Context mContext;

	public ByNameEditText(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public ByNameEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public ByNameEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	@Override
	public void setCursorVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setCursorVisible(visible);

	}

	public void init() {
		//
		// setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// boolean ss = false;
		// if(event.getAction() == MotionEvent.ACTION_HOVER_EXIT){
		// setCursorVisible(false);//EditText  ß»•Ωπµ„
		// clearFocus();
		// setInputType(InputType.TYPE_NULL);
		// ss=false;
		// }
		// else if(event.getAction() == MotionEvent.ACTION_HOVER_ENTER){
		// setCursorVisible(true);
		// requestFocus();
		// ss=true;
		// }
		// return ss;
		// }
		// });

		// setOnFocusChangeListener(new OnFocusChangeListener() {
		//
		// @Override
		// public void onFocusChange(View v, boolean hasFocus) {
		// // TODO Auto-generated method stub
		// if (hasFocus == false)
		// setCursorVisible(false);
		// else
		// setCursorVisible(true);
		// }
		// });
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
