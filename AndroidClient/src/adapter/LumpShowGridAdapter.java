package com.stdu.edu.italk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.resource.LumpShow;

/**
 * Õ¯∏Ò ”Õºµƒ  ≈‰∆˜
 * @author xianming 2015-7-26
 *
 */
public class LumpShowGridAdapter extends BaseAdapter {
	List<LumpShow> list;
	Context context;
	View view;

	public LumpShowGridAdapter(List<LumpShow> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderGrid viewholdergrid = new ViewHolderGrid();
		LumpShow lumpshow = list.get(position);
		view = LayoutInflater.from(context).inflate(R.layout.lumpschoolgrid,
				null);
		if (convertView == null) {
			viewholdergrid.xueYuan = (TextView) view
					.findViewById(R.id.xueyungrid);

			viewholdergrid.timereflash = (TextView) view
					.findViewById(R.id.timegrid);

			viewholdergrid.juli = (TextView) view.findViewById(R.id.juligrid);

			view.setTag(viewholdergrid);
		} else {
			view = convertView;
			viewholdergrid = (ViewHolderGrid) view.getTag();
		}
		viewholdergrid.xueYuan.setText(lumpshow.getXueYuan());
		viewholdergrid.timereflash.setText(lumpshow.getZhuangTai());
		viewholdergrid.juli.setText(lumpshow.getJuLi());
		return view;
	}
}

class ViewHolderGrid {
	TextView xueYuan;
	TextView timereflash;
	TextView juli;
}
