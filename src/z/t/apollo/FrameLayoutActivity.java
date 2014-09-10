package z.t.apollo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class FrameLayoutActivity extends Activity {
	public static final String ACTION = "z.t.apollo.FrameLayoutActivity";
	private int currentColor = 0;
	final int[] coclrs = new int[] { R.color.color1, R.color.color2,
			R.color.color3, R.color.color4, R.color.color5 };
	final int[] names = new int[] { R.id.textView1, R.id.textView2,
			R.id.textView3, R.id.textView4, R.id.textView5 };
	TextView[] view = new TextView[names.length];
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				for (int i = 0; i < names.length; i++) {
					view[i].setBackgroundResource(coclrs[(i + currentColor)
							% names.length]);
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
		for (int i = 0; i < names.length; i++) {
			view[i] = (TextView) findViewById(names[i]);
		}
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 200);
	}
}
