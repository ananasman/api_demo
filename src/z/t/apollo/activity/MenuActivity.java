package z.t.apollo.activity;

import z.t.apollo.utils.MainUI;
import android.app.Activity;
import android.os.Bundle;

public class MenuActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.MenuActivity";
	private MainUI mainUI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainUI = new MainUI(this);
		setContentView(mainUI);
	}
}
