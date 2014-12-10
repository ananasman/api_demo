package z.t.apollo.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class MainUI extends FrameLayout {
	private Context context;
	private FrameLayout leftMenu;
	private FrameLayout middleMenu;
	private FrameLayout middleMask;
	private FrameLayout rightMenu;
	private Scroller mScroller;
	public static final int LEFT_ID = 0xaabbcc;
	public static final int MIDDLE_ID = 0xaabb;
	public static final int RIGHT_ID = 0xaa;

	public MainUI(Context context) {
		super(context);
		initView(context);
	}

	public MainUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		this.context = context;
		mScroller = new Scroller(context, new DecelerateInterpolator());
		leftMenu = new FrameLayout(context);
		middleMenu = new FrameLayout(context);
		middleMask = new FrameLayout(context);
		rightMenu = new FrameLayout(context);
		leftMenu.setBackgroundColor(Color.CYAN);
		middleMenu.setBackgroundColor(Color.BLUE);
		rightMenu.setBackgroundColor(Color.CYAN);
		middleMask.setBackgroundColor(0x88000000);
		leftMenu.setId(LEFT_ID);
		middleMenu.setId(MIDDLE_ID);
		rightMenu.setId(RIGHT_ID);
		addView(leftMenu);
		addView(middleMenu);
		// 添加蒙板
		addView(middleMask);
		addView(rightMenu);
		middleMask.setAlpha(0);
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		int curX = Math.abs(getScrollX());
		float scale = curX / (float) leftMenu.getMeasuredWidth();
		middleMask.setAlpha(scale);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获取到屏幕的真实宽度
		int realwidth = MeasureSpec.getSize(widthMeasureSpec);
		// 设置菜单的宽度
		// makeMeasureSpec第一个参数是要设置的宽度，第二个参数是设置的方式
		int tempWidth = MeasureSpec.makeMeasureSpec((int) (realwidth * 0.618f),
				MeasureSpec.EXACTLY);
		leftMenu.measure(tempWidth, heightMeasureSpec);
		middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
		middleMask.measure(widthMeasureSpec, heightMeasureSpec);
		rightMenu.measure(tempWidth, heightMeasureSpec);
	}

	// 设置菜单在屏幕中的位置
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
		middleMenu.layout(l, t, r, b);
		middleMask.layout(l, t, r, b);
		rightMenu.layout(l + middleMenu.getMeasuredWidth(), t,
				r + rightMenu.getMeasuredWidth(), b);
	}

	private boolean isTestComplete;
	private boolean isLeftRightEvent;

	// 滑动事件分发
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (!isTestComplete) {
			getEventType(ev);
			return true;
		}
		if (isLeftRightEvent) {
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_MOVE:
				int curScrollX = getScrollX();
				int dis_x = (int) (ev.getX() - point.x);
				int expectX = -dis_x + curScrollX;
				int finalX = 0;
				if (expectX < 0) {
					finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
				} else {
					finalX = Math.min(expectX, rightMenu.getMeasuredWidth());
				}
				scrollTo(finalX, 0);
				point.x = (int) ev.getX();
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				curScrollX = getScrollX();
				if (Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
					if (curScrollX < 0) {
						//
						mScroller.startScroll(curScrollX, 0,
								-leftMenu.getMeasuredWidth() - curScrollX, 0);
					} else {
						//
						mScroller.startScroll(curScrollX, 0,
								leftMenu.getMeasuredWidth() - curScrollX, 0);
					}
				} else {
					//
					mScroller.startScroll(curScrollX, 0, -curScrollX, 0);
				}
				// 重绘
				invalidate();
				isTestComplete = false;
				isLeftRightEvent = false;
				break;
			}
		} else {// 上下滑动操作后重新初始化
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_UP:
				isTestComplete = false;
				isLeftRightEvent = false;
				break;
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (!mScroller.computeScrollOffset()) {
			return;
		}
		int tempX = mScroller.getCurrX();
		scrollTo(tempX, 0);
	}

	private Point point = new Point();
	private static final int TEST_DIS = 20;

	public void getEventType(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			point.x = (int) ev.getX();
			point.y = (int) ev.getY();
			super.dispatchTouchEvent(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			// 左右滑动的距离
			int dX = Math.abs((int) (ev.getX() - point.x));
			// 向下滑动的距离
			int dY = Math.abs((int) (ev.getY() - point.y));
			if (dX >= TEST_DIS && dX > dY) {// 左右滑动
				isLeftRightEvent = true;
				isTestComplete = true;
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();
			} else if (dY >= TEST_DIS && dY > dX) {// 上下滑动
				isLeftRightEvent = false;
				isTestComplete = true;
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			super.dispatchTouchEvent(ev);
			isLeftRightEvent = false;
			isTestComplete = false;
			break;
		}
	}
}
