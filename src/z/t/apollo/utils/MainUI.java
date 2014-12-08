package z.t.apollo.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class MainUI extends FrameLayout {
	private Context context;
	/**
	 * 左菜单
	 */
	private FrameLayout leftMenu;
	private FrameLayout middleMenu;
	/**
	 * 右菜单
	 */
	private FrameLayout rightMenu;
	private FrameLayout middleMask;
	private Scroller mScroller;
	public static final int LEFT_ID = 0xaabbcc;
	public static final int MIDEELE_ID = 0xaabbcc;
	public static final int RIGHT_ID = 0xaabbcc;
	private static final int num = 20;

	public MainUI(Context context) {
		super(context);
		initView(context);
	}

	public MainUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	// 初始化界面
	private void initView(Context context) {
		this.context = context;
		leftMenu = new FrameLayout(context);
		middleMenu = new FrameLayout(context);
		rightMenu = new FrameLayout(context);
		middleMask = new FrameLayout(context);
		mScroller = new Scroller(context);
		leftMenu.setBackgroundColor(Color.RED);
		middleMenu.setBackgroundColor(Color.CYAN);
		rightMenu.setBackgroundColor(Color.RED);
		middleMask.setBackgroundColor(0x88000000);
		leftMenu.setId(LEFT_ID);
		middleMenu.setId(MIDEELE_ID);
		rightMenu.setId(RIGHT_ID);
		addView(leftMenu);
		addView(middleMenu);
		addView(middleMask);
		addView(rightMenu);
		middleMask.setAlpha(0);
	}

	public float onMiddleMask() {
		return middleMask.getAlpha();
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		onMiddleMask();
		int curX = Math.abs(getScrollX());
		float scale = curX / (float) leftMenu.getMeasuredWidth();
		middleMask.setAlpha(scale);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
		middleMask.measure(widthMeasureSpec, heightMeasureSpec);
		// 屏幕的真实宽度
		int realWidth = MeasureSpec.getSize(widthMeasureSpec);
		int tempMeasureSpec = MeasureSpec.makeMeasureSpec(
				(int) (realWidth * 0.6f), MeasureSpec.EXACTLY);
		leftMenu.measure(tempMeasureSpec, heightMeasureSpec);
		rightMenu.measure(tempMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		middleMenu.layout(l, t, r, b);
		middleMask.layout(l, t, r, b);
		leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
		rightMenu.layout(
				l + middleMenu.getMeasuredWidth(),
				t,
				l + middleMenu.getMeasuredWidth()
						+ rightMenu.getMeasuredWidth(), b);
	}

	private boolean isTestCompete;
	private boolean isLeftRightEvent;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (!isTestCompete) {
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
					finalX = Math.max(expectX, leftMenu.getMeasuredWidth());
				}
				scrollTo(finalX, 0);
				point.x = (int) ev.getX();
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				curScrollX = getScrollX();
				if (Math.abs(curScrollX) > Math.abs(curScrollX) >> 1) {
					if (curScrollX < 0) {
						mScroller.startScroll(curScrollX, 0,
								-leftMenu.getMeasuredWidth() - curScrollX, 0,
								200);
					} else {
						mScroller.startScroll(curScrollX, 0,
								leftMenu.getMeasuredWidth() - curScrollX, 0,
								200);
					}
				} else {
					mScroller.startScroll(curScrollX, 0, -curScrollX, 0, 200);
				}
				invalidate();
				isLeftRightEvent = false;
				isTestCompete = false;
				break;
			}
		} else {
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_UP:
				isLeftRightEvent = false;
				isTestCompete = false;
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

	private void getEventType(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			point.x = (int) event.getX();
			point.y = (int) event.getY();
			super.dispatchTouchEvent(event);
			break;
		case MotionEvent.ACTION_MOVE:
			int dX = Math.abs((int) event.getX() - point.x);
			int dY = Math.abs((int) event.getY() - point.y);
			if (dX >= num && dX > dY) {// 左右滑动
				isTestCompete = true;
				isLeftRightEvent = true;
				point.x = (int) event.getX();
				point.y = (int) event.getY();
			} else if (dY >= num && dY > dX) {// 上下滑动
				isTestCompete = true;
				isLeftRightEvent = false;
				point.x = (int) event.getX();
				point.y = (int) event.getY();

			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			super.dispatchTouchEvent(event);
			isLeftRightEvent = false;
			isTestCompete = false;
			break;
		}
	}
}
