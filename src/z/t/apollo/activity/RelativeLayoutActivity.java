package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;

public class RelativeLayoutActivity extends Activity {
	public final static String ACTION = "z.t.apollo.activity.RelativeLayoutActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relative_layout);
	}
}
