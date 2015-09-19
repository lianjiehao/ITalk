package com.stdu.edu.italk.adapter;

import java.util.List;

import android.R.integer;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;


/**
 * ����ͼƬ��������
 * @author xianming 2015-7-24
 *
 */
public class ImagePagerAdapter extends PagerAdapter {

	private List <View> list; 
	View view;
	
	public ImagePagerAdapter(List <View> list) {
		super();
		// TODO Auto-generated constructor stub
		this.list=list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10004;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	//ʵ����ҳ��
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
        position %= list.size();  
        if (position<0){  
            position = list.size()+position;  
        }  
         view =  list.get(position); 
        //���View�Ѿ���֮ǰ��ӵ���һ����������������remove��������׳�IllegalStateException��  
        ViewParent vp =view.getParent();  
        if (vp!=null){  
            ViewGroup parent = (ViewGroup)vp;  
            parent.removeView(view);  
        }  
        container.addView(view);    
        //add listeners here if necessary  
        return view;    
		
		
	}
	
	//����ҳ��
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		// TODO Auto-generated method stub
	}
	


}
