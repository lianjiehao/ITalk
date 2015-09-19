package com.stdu.edu.italk.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.stdu.edu.italk.R;
import com.stdu.edu.italk.resource.LumpFind;


/**
 * 发现界面的适配器
 * @author xianming 2015-7-26
 *
 */
public class LumpFindAdapter extends BaseAdapter implements ViewFactory,
		OnTouchListener {

	List<LumpFind> list = new ArrayList<LumpFind>();
	private Context context;
	private final static int TYPE_1 = 0;
	private final static int TYPE_2 = 1;

	/**
	 * ImagaSwitcher 的引用
	 */
	private ImageSwitcher mImageSwitcher;
	/**
	 * 图片id数组m
	 */
	private int[] imgIds;
	/**
	 * 当前选中的图片id序号
	 */
	private int currentPosition;
	/**
	 * 按下点的X坐标
	 */
	private float downX;
	/**
	 * 装载点点的容器
	 */
	private LinearLayout linearLayout;
	/**
	 * 点点数组
	 */
	private ImageView[] tips;

	public LumpFindAdapter(List<LumpFind> list, Context context) {
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
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return list.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	@Override
	public View makeView() {
		final ImageView i = new ImageView(context);
		i.setBackgroundColor(0xff000000);
		i.setScaleType(ImageView.ScaleType.CENTER_CROP);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			// 手指按下的X坐标
			downX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float lastX = event.getX();
			// 抬起的时候的X坐标大于按下的时候就显示上一张图片
			if (lastX > downX) {
				if (currentPosition > 0) {
					// 设置动画，这里的动画比较简单，不明白的去网上看看相关内容
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(
							context, R.anim.left_in));
					mImageSwitcher.setOutAnimation(AnimationUtils
							.loadAnimation(context, R.anim.right_out));
					currentPosition--;
					mImageSwitcher.setImageResource(imgIds[currentPosition
							% imgIds.length]);
					setImageBackground(currentPosition);
				}
			}

			if (lastX < downX) {
				if (currentPosition < imgIds.length - 1) {
					mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(
							context, R.anim.right_in));
					mImageSwitcher.setOutAnimation(AnimationUtils
							.loadAnimation(context, R.anim.lift_out));
					currentPosition++;
					mImageSwitcher.setImageResource(imgIds[currentPosition]);
					setImageBackground(currentPosition);
				}
			}
		}

			break;
		}

		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int type = getItemViewType(position);
		View view = null;
		viewHolderFind viewholderfind = new viewHolderFind();
		if (convertView == null) {
			switch (type) {
			case TYPE_2: {
				// view = LayoutInflater.from(context).inflate(
				// R.layout.tab_find_up, null);

				break;
			}

			case TYPE_1: {
				view = LayoutInflater.from(context).inflate(
						R.layout.tab_find_item, null);
				viewholderfind.imageLeft = (ImageView) view
						.findViewById(R.id.imageleft);
				viewholderfind.imageRight = (ImageView) view
						.findViewById(R.id.imageright);
				viewholderfind.itemName = (TextView) view
						.findViewById(R.id.itemname);
				view.setTag(viewholderfind);
				break;
			}

			}
		} else {
			view = convertView;
			viewholderfind = (viewHolderFind) view.getTag();
		}

		if (type == TYPE_1) {
			viewholderfind.imageLeft.setImageResource(list.get(position)
					.getImageLeft());
			viewholderfind.imageRight.setImageResource(list.get(position)
					.getImageRight());
			viewholderfind.itemName.setText(list.get(position).getItemName());
		}

		// if(type==TYPE_2){
		// // 实例化ImageSwitcher
		// mImageSwitcher = (ImageSwitcher)
		// view.findViewById(R.id.imageSwitcher1);
		// // 设置Factory
		// mImageSwitcher.setFactory(this);
		// // 设置OnTouchListener，我们通过Touch事件来切换图片
		// mImageSwitcher.setOnTouchListener(this);
		//
		// linearLayout = (LinearLayout) view.findViewById(R.id.viewGroup);
		//
		// tips = new ImageView[imgIds.length];
		// for (int i = 0; i < imgIds.length; i++) {
		// ImageView mImageView = new ImageView(context);
		// tips[i] = mImageView;
		// LinearLayout.LayoutParams layoutParams = new
		// LinearLayout.LayoutParams(
		// new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// layoutParams.rightMargin = 3;
		// layoutParams.leftMargin = 3;
		//
		// mImageView
		// .setBackgroundResource(R.drawable.page_indicator_unfocused);
		// linearLayout.addView(mImageView, layoutParams);
		// }
		//
		// // 这个我是从上一个界面传过来的，上一个界面是一个GridView
		// currentPosition = 0;
		// mImageSwitcher.setImageResource(imgIds[currentPosition]);
		//
		// setImageBackground(currentPosition);
		//
		// }

		return view;
	}

}

class viewHolderFind {
	ImageView imageLeft;
	TextView itemName;
	ImageView imageRight;
}