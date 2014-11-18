package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;

public class ButtonActivity extends Activity {
	public final static String ACTION = "z.t.apollo.activity.ButtonActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);
	}
}
