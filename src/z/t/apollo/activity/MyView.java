package z.t.apollo.activity;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	/**
	 * 画笔工具
	 */
	Paint mPaint;
	float textSize;
	int textColor;
	String text;

	/**
	 * 至少需要重载构造方法和onDraw方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.MyView, 0, 0);
		textColor = a.getColor(R.styleable.MyView_textColor, 0xff00ff00);
		textSize = a.getDimension(R.styleable.MyView_textSize, 36);
		text = a.getString(R.styleable.MyView_text);
		mPaint = new Paint();// 实例化画笔工具
		mPaint.setColor(Color.CYAN);// 设置画笔颜色
		mPaint.setAntiAlias(true);// 设置字体抗锯齿
		// mPaint.setTextSize(textSize);// 设置字体大小
		mPaint.setStrokeWidth(3);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		a.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// mPaint.setStyle(Style.FILL);
		// canvas.drawRect(10, 10, 100, 100, mPaint);
		// canvas.drawColor(Color.WHITE);
		// canvas.drawText("", 10, 120, mPaint);
		// Resources res = getResources();
		// BitmapDrawable bd = (BitmapDrawable) res
		// .getDrawable(R.drawable.btn_zoo_grey);
		// Bitmap bitmap = bd.getBitmap();
		// canvas.drawLine(0, 0, this.getWidth(), this.getHeight(), mPaint);
		// canvas.drawBitmap(bitmap, 20, 120, mPaint);
		// drawLine(canvas);
		mPaint.setStyle(Style.STROKE);
		// 将画板位置移动到屏幕中央
		canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		// drawCircle的三个参数分别为：X坐标，Y坐标，半径。
		canvas.drawCircle(0, 0, 200, mPaint);// 画圆
		// 使用path绘制路径文字
		canvas.save();
		canvas.translate(-100, -100);
		Path path = new Path();

		path.addArc(new RectF(0, 0, 150, 150), 0, 0);
		Paint citePaint = new Paint(mPaint);
		citePaint.setTextSize(20);
		citePaint.setStrokeWidth(1);
		canvas.drawTextOnPath(text, path, 28, 0, citePaint);
		canvas.restore();
		Paint tmpPaint = new Paint(mPaint);// 小刻度画笔对象
		tmpPaint.setStrokeWidth(2);
		tmpPaint.setTextSize(20);
		// 控制刻度的半径
		float y = 170;
		int count = 60;
		canvas.rotate(210);
		for (int i = 0; i < count; i++) {
			if (i % 5 == 0) {
				canvas.drawLine(0, y, 0, y + 12, mPaint);
				canvas.drawText(String.valueOf(i / 5 + 1), -5, y + 25, tmpPaint);
			} else {
				canvas.drawLine(0f, y, 0f, y + 5f, tmpPaint);
			}
			canvas.rotate(360 / count, 0f, 0f);
		}
	}

	public void drawLine(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(5);
		canvas.drawLine(0, 0, this.getWidth(), this.getHeight(), mPaint);
		canvas.drawLine(this.getWidth(), 0, 0, this.getHeight(), mPaint);
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
