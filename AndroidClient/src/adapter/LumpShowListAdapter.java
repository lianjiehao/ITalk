package com.stdu.edu.italk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.resource.LumpShow;

/**
 * �б���ͼ��������
 * @author xianming 2015-7-26
 *
 */
public class LumpShowListAdapter extends ArrayAdapter<LumpShow> {
	private int resourceId;

	public LumpShowListAdapter(Context context, int textViewResourceId,
			List<LumpShow> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LumpShow lumpSeacher = getItem(position);
		View view;
		ViewHolderShow viewHolderShow;
		//����֮ǰ���ص�Item
		if(convertView==null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolderShow=new ViewHolderShow();
			viewHolderShow.xueYuan=(TextView)view.findViewById(R.id.yuanxi);
			viewHolderShow.zhuangtai=(TextView)view.findViewById(R.id.zhuangtai);
			viewHolderShow.juLi=(TextView)view.findViewById(R.id.juli);
			view.setTag(viewHolderShow); //��ViewHolder�洢��view��
		}
		else{
			view=convertView;
			viewHolderShow=(ViewHolderShow)view.getTag();
		}
		viewHolderShow.xueYuan.setText(lumpSeacher.getXueYuan());
		viewHolderShow.zhuangtai.setText(lumpSeacher.getZhuangTai());
		viewHolderShow.juLi.setText(lumpSeacher.getJuLi());
		return view;
	}
}

class ViewHolderShow {
	TextView xueYuan;
	TextView zhuangtai;
	TextView juLi;

}