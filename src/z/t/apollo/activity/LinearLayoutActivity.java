package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;

public class LinearLayoutActivity extends Activity {
	public final static String ACTION = "z.t.apollo.activity.LinearLayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linear_layout);
	}
}
