package com.stdu.edu.italk.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.application.UserData;
import com.stdu.edu.italk.resource.LumpChat;

/**
 * 聊天页的fragment
 * 
 * @author xianming
 *
 */
public class ChatFragment extends Fragment implements OnClickListener {
	private ListView chatList;
	private List<LumpChat> LumpChatList = new ArrayList<LumpChat>();
	private ImageView imageLeft;
	private ImageButton imageRight;
	View view;
	UserData data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab_chat, container, false);
		initView();
		loadView();
		LoadLump();
		initclick();
		return view;
	}

	private void initView() {
		imageLeft = (ImageView) getActivity().findViewById(R.id.Image_left);
		imageRight=(ImageButton) getActivity().findViewById(R.id.imag_right);
	}
	
	private void loadView(){
		imageRight.setImageResource(R.drawable.more_nopress);
	}

	private void initclick() {
		imageLeft.setOnClickListener(this);
		imageRight.setOnClickListener(this);
	}

	//加载listView
	public void LoadLump(){
		chatList = (ListView) view.findViewById(R.id.chatlist);
		RefleshChatFragment friend = new RefleshChatFragment(getActivity(),chatList);
		data = (UserData) view.getContext().getApplicationContext();
		friend.execute(data.getUserName());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Image_left:
			Toast.makeText(getActivity(), "系统消息", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imag_right:
			Toast.makeText(getActivity(), "消息处理", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
