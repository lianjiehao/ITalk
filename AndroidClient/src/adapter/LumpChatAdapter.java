package com.stdu.edu.italk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.resource.LumpChat;

/**
 * 聊天界面的适配器
 * @author xianming 2015-7-24
 *
 */
public class LumpChatAdapter extends ArrayAdapter<LumpChat>{
	private int resourceId;

	public LumpChatAdapter(Context context, int textViewResourceId,
			List<LumpChat> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LumpChat lumpChat = getItem(position);
		View view;
		ViewHolderChat viewHolderChat;
		//缓存之前加载的Item
		if(convertView==null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolderChat=new ViewHolderChat();
			viewHolderChat.byName=(TextView)view.findViewById(R.id.byname);
			viewHolderChat.sendTime=(TextView)view.findViewById(R.id.sendtime);
			viewHolderChat.message=(TextView)view.findViewById(R.id.content);
			viewHolderChat.qinMi=(TextView)view.findViewById(R.id.qingmizhi);
			viewHolderChat.messageIcon=(ImageView)view.findViewById(R.id.message_icon);
			view.setTag(viewHolderChat); //将ViewHolder存储在view中
		}
		else{
			view=convertView;
			viewHolderChat=(ViewHolderChat)view.getTag();
		}
		viewHolderChat.byName.setText(lumpChat.getByName());
		viewHolderChat.message.setText(lumpChat.getMessage());
		viewHolderChat.qinMi.setText(lumpChat.getQinMi());
		viewHolderChat.sendTime.setText(lumpChat.getSendTime());
		viewHolderChat.messageIcon.setImageResource(lumpChat.getMessageIconResource());
		return view;
	}
}

class ViewHolderChat {
	TextView byName;
	TextView sendTime;
	TextView message;
	TextView qinMi;
	ImageView messageIcon;
}