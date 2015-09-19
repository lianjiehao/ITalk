package com.stdu.edu.italk.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.View.PersonerPager;
import com.stdu.edu.italk.View.ShowSelect;
import com.stdu.edu.italk.View.friendpager;
import com.stdu.edu.italk.adapter.LumpShowGridAdapter;
import com.stdu.edu.italk.adapter.LumpShowListAdapter;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.localsava.SchoolFriSQLiteOpenHelper;
import com.stdu.edu.italk.resource.LumpShow;

/**
 * 校友页的fragment
 * 
 * @author xianming 207-7-18
 *
 */
public class SchoolFriFragment extends Fragment implements OnClickListener {
	private ListView schoolFriendList;
	private GridView schoolFriendGrid;
	private List<LumpShow> LumpSeacherList = new ArrayList<LumpShow>();
	View view;
	View popWindow;
	LinearLayout pop_canver;
	ImageView selectorSeacherView;
	ImageView selectorSeacher;
	TextView text;
	LinearLayout selectdropdownright;
	LinearLayout selectdropdownleft;
	Intent intent;
	ImageView imageLeft;
	boolean isVisible = false;
	ImageButton imageButtonSeacher;
	View parentView;
	static boolean tag = false;
	int tags = 1;
	static int mTag = 1;
	int x = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab_schoolfri, container, false);
		initView();
		loadView();
		return view;
	}


	public void loadView() {
		selectorSeacherView = (ImageView) view.findViewById(R.id.selector_view);
		text = (TextView) view.findViewById(R.id.textdown);
		if (mTag == 1) {
			LoadList();
			selectorSeacherView
					.setBackgroundResource(R.drawable.slelector_view_grid);
			selectorSeacherView.setTag(R.drawable.slelector_view_grid);
			text.setText("网格视图");
		} else {
			LoadGrid();
			selectorSeacherView
					.setBackgroundResource(R.drawable.slelector_view_list);
			selectorSeacherView.setTag(R.drawable.slelector_view_list);
			text.setText("列表视图");
		}
	}

	// 加载GridView数据
	public void LoadGrid() {
		LumpShowGridAdapter adapter = new LumpShowGridAdapter(LumpSeacherList,
				getActivity());
		schoolFriendGrid.setAdapter(adapter);
		schoolFriendGrid.setVisibility(View.VISIBLE);
		schoolFriendList.setVisibility(View.GONE);
		tags = 0;
	}

	// 加载ListView数据
	public void LoadList() {
		UserData data = (UserData) getActivity().getApplicationContext();
		ClientServerConnection_SchoolFri connection = new ClientServerConnection_SchoolFri(
				getActivity(),schoolFriendList);
		connection.execute(data.getUserName());
		schoolFriendGrid.setVisibility(View.GONE);
		schoolFriendList.setVisibility(View.VISIBLE);
		tags = 1;
	}

	// 挡幕的侦听事件
	public void initView() {
		// 获取父窗口的空间
		schoolFriendGrid = (GridView) view.findViewById(R.id.schoolfriendgrid);
		schoolFriendList = (ListView) view.findViewById(R.id.schoolfriendlist);
		imageButtonSeacher = (ImageButton) getActivity().findViewById(
				R.id.imag_right);
		imageLeft = (ImageView) getActivity().findViewById(R.id.Image_left);
		pop_canver = (LinearLayout) view.findViewById(R.id.pop_canver);
		selectorSeacher = (ImageView) view.findViewById(R.id.selector_seacher);
		selectorSeacherView = (ImageView) view.findViewById(R.id.selector_view);
		selectdropdownleft = (LinearLayout) view
				.findViewById(R.id.selectdropdownleft);
		selectdropdownright = (LinearLayout) view
				.findViewById(R.id.selectdropdownright);
		pop_canver.setOnClickListener(this);
		selectorSeacher.setOnClickListener(this);
		selectorSeacherView.setOnClickListener(this);
		selectdropdownleft.setOnClickListener(this);
		selectdropdownright.setOnClickListener(this);
		imageLeft.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		MyFragmentActivity parentActivity = (MyFragmentActivity) getActivity();
		pop_canver = (LinearLayout) view.findViewById(R.id.pop_canver);
		popWindow = (View) view.findViewById(R.id.ll_popup_window);
		selectorSeacherView = (ImageView) view.findViewById(R.id.selector_view);
		text = (TextView) view.findViewById(R.id.textdown);

		switch (v.getId()) {
		case R.id.pop_canver: {

			pop_canver.setVisibility(View.GONE);
			popWindow.setVisibility(View.GONE);
			// “搜索”加载
			imageButtonSeacher.setImageResource(R.drawable.search_nopress);
			parentActivity.isVisible = false;
			isVisible = false;
			break;
		}
		case R.id.selectdropdownleft: {
			intent = new Intent(getActivity(), ShowSelect.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.in_from_bottom, 0);
			break;
		}
		case R.id.selector_seacher: {
			intent = new Intent(getActivity(), ShowSelect.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.in_from_bottom, 0);
			break;
		}
		case R.id.selectdropdownright: {
			if (tag == true) {
				LoadList();
				selectorSeacherView
						.setBackgroundResource(R.drawable.slelector_view_grid);
				text.setText("网格视图");
				tag = false;
				pop_canver.setVisibility(View.GONE);
				popWindow.setVisibility(View.GONE);
				imageButtonSeacher.setImageResource(R.drawable.search_nopress);
				parentActivity.isVisible = false;
			}

			else {
				LoadGrid();
				selectorSeacherView
						.setBackgroundResource(R.drawable.slelector_view_list);
				text.setText("列表视图");
				tag = true;
				pop_canver.setVisibility(View.GONE);
				popWindow.setVisibility(View.GONE);
				imageButtonSeacher.setImageResource(R.drawable.search_nopress);
				parentActivity.isVisible = false;
			}
			break;
		}

		case R.id.selector_view: {
			if (tag == true) {
				LoadList();
				selectorSeacherView
						.setBackgroundResource(R.drawable.slelector_view_grid);
				text.setText("网格视图");
				tag = false;
				pop_canver.setVisibility(View.GONE);
				popWindow.setVisibility(View.GONE);
				imageButtonSeacher.setImageResource(R.drawable.search_nopress);
				parentActivity.isVisible = false;
			}

			else {
				LoadGrid();
				selectorSeacherView
						.setBackgroundResource(R.drawable.slelector_view_list);
				text.setText("列表视图");
				tag = true;
				pop_canver.setVisibility(View.GONE);
				popWindow.setVisibility(View.GONE);
				imageButtonSeacher.setImageResource(R.drawable.search_nopress);
				parentActivity.isVisible = false;
			}
			break;
		}

		case R.id.Image_left: {
			intent = new Intent(getActivity(), PersonerPager.class);
			startActivity(intent);
			break;
		}

		}
	}

	@Override
	public void onPause() {
		super.onPause();
		pop_canver = (LinearLayout) view.findViewById(R.id.pop_canver);
		popWindow = (View) view.findViewById(R.id.ll_popup_window);
		imageButtonSeacher = (ImageButton) getActivity().findViewById(
				R.id.imag_right);
		text = (TextView) view.findViewById(R.id.textdown);
		pop_canver.setVisibility(View.GONE);
		popWindow.setVisibility(View.GONE);
		mTag = tags;
	}

}
