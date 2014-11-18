package z.t.apollo.activity;

import java.util.Timer;
import java.util.TimerTask;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class FrameLayoutActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.FrameLayoutActivity";
	private int currentColor = 0;
	// 存放颜色id的数组
	final int[] colors = new int[] { R.color.color1, R.color.color2,
			R.color.color3, R.color.color4, R.color.color5 };
	// 存放的Textview id的数组
	final int[] ids = new int[] { R.id.textView1, R.id.textView2,
			R.id.textView3, R.id.textView4, R.id.textView5 };
	// 存放的Textview的数组
	private TextView[] tv = new TextView[ids.length];
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				for (int i = 0; i < ids.length; i++) {
					// 设置Textview的背景色
					tv[i].setBackgroundResource(colors[(i + currentColor)
							% ids.length]);
				}
				currentColor++;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame_layout);
		for (int i = 0; i < ids.length; i++) {
			tv[i] = (TextView) findViewById(ids[i]);
		}
		// 每0.2秒变换一次颜色
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 200);
	}
}
