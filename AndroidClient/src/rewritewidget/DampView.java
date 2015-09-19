package com.stdu.edu.italk.rewritewidget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * 个人资料页滚动
 */

public class DampView extends ScrollView {
	private static final int LEN = 0xc8;
	private static final int DURATION = 500;
	private static final int MAX_DY = 200;
	private Scroller mScroller;
	TouchTool tool;
	int left, top;
	float startX, startY, currentX, currentY;
	int imageViewH;
	int rootW, rootH;
	ImageView imageView;
	boolean scrollerType;

	
	private View inner;// 孩子View
	private float y;// 点击时y坐标
	private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)
	private boolean isCount = false;// 是否开始计算
	
	
	
	/***
	 * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
	 * 方法，也应该调用父类的方法，使该方法得以执行.
	 */
	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);
		}
	}

	/***
	 * 监听touch
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (inner != null) {
			commOnTouchEvent(ev);
		}

		return super.onTouchEvent(ev);
	}

	/***
	 * 触摸事件
	 * 
	 * @param ev
	 */
	public void commOnTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			// 手指松开.
			if (isNeedAnimation()) {
				animation();
				isCount = false;
			}
			break;
			
		/***
		 * 排除出第一次移动计算，因为第一次无法得知y坐标， 在MotionEvent.ACTION_DOWN中获取不到，
		 * 因为此时是MyScrollView的touch事件传递到到了LIstView的孩子item上面.所以从第二次计算开始.
		 * 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0. 之后记录准确了就正常执行.
		 */
		case MotionEvent.ACTION_MOVE:
			final float preY = y;// 按下时的y坐标
			float nowY = ev.getY();// 时时y坐标
			int deltaY = (int) (preY - nowY);// 滑动距离
			if (!isCount) {
				deltaY = 0; // 在这里要归0.
			}

			y = nowY;
			// 当滚动到最上或者最下时就不会再滚动，这时移动布局
			if (isNeedMove()) {
				// 初始化头部矩形
				if (normal.isEmpty()) {
					// 保存正常的布局位置
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
				}
				// 移动布局
				inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
						inner.getRight(), inner.getBottom() - deltaY / 2);
			}
			isCount = true;
			break;

		default:
			break;
		}
	}

	/***
	 * 回缩动画
	 */
	public void animation() {
		// 开启移动动画
		TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
				normal.top);
		ta.setDuration(200);
		inner.startAnimation(ta);
		// 设置回到正常的布局位置
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);

		normal.setEmpty();

	}

	// 是否需要开启动画
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	/***
	 * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
	 * 
	 * getHeight()：获取的是屏幕的高度
	 * 
	 * @return
	 */
	public boolean isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		Log.e("jj", "scrolly=" + scrollY);
		// 0是顶部，后面那个是底部
		if ( scrollY == offset) {
			return true;
		}
		return false;
	}
	
	

	
	/**
	 * 图片回滚
	 */
	public DampView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public DampView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	public DampView(Context context) {
		super(context);

	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (!mScroller.isFinished()) {
			return super.onTouchEvent(event);
		}
		currentX = event.getX();
		currentY = event.getY();
		imageView.getTop();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			left = imageView.getLeft();
			top = imageView.getBottom();
			rootW = getWidth();
			rootH = getHeight();
			imageViewH = imageView.getHeight();
			startX = currentX;
			startY = currentY;
			tool = new TouchTool(imageView.getLeft(), imageView.getBottom(),
					imageView.getLeft(), imageView.getBottom() + LEN);
			break;
		case MotionEvent.ACTION_MOVE:
			if (imageView.isShown() && imageView.getTop() >= 0) {
				if (tool != null) {
					int t = tool.getScrollY(currentY - startY);
					if (t >= top && t <= imageView.getBottom() + LEN) {
						android.view.ViewGroup.LayoutParams params = imageView
								.getLayoutParams();
						params.height = t;
						imageView.setLayoutParams(params);
					}
				}
				scrollerType = false;
			}
			break;
		case MotionEvent.ACTION_UP:
			scrollerType = true;
			mScroller.startScroll(imageView.getLeft(), imageView.getBottom(),
					0 - imageView.getLeft(),
					imageViewH - imageView.getBottom(), DURATION);
			invalidate();
			break;
		}

		return super.dispatchTouchEvent(event);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int x = mScroller.getCurrX();
			int y = mScroller.getCurrY();
			imageView.layout(0, 0, x + imageView.getWidth(), y);
			invalidate();
			if (!mScroller.isFinished() && scrollerType && y > MAX_DY) {
				android.view.ViewGroup.LayoutParams params = imageView
						.getLayoutParams();
				params.height = y;
				imageView.setLayoutParams(params);
			}
		}
	}

	public class TouchTool {

		private int startX, startY;

		public TouchTool(int startX, int startY, int endX, int endY) {
			super();
			this.startX = startX;
			this.startY = startY;
		}

		public int getScrollX(float dx) {
			int xx = (int) (startX + dx / 2.5F);
			return xx;
		}

		public int getScrollY(float dy) {
			int yy = (int) (startY + dy / 2.5F);
			return yy;
		}
	}
}
