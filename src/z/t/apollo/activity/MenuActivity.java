package z.t.apollo.activity;

import android.os.Bundle;
import z.t.apollo.fragment.LeftMenuFragment;
import z.t.apollo.utils.MainUI;

public class MenuActivity extends android.support.v4.app.FragmentActivity {
	public static final String ACTION = "z.t.apollo.activity.MenuActivity";
	private MainUI mainUI;
	private LeftMenuFragment leftMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainUI = new MainUI(this);
		getActionBar().hide();// 隐藏掉整个ActionBar，包括下面的Tabs
		setContentView(mainUI);
		leftMenu = new LeftMenuFragment();
		getSupportFragmentManager().beginTransaction()
				.add(MainUI.LEFT_ID, leftMenu).commit();
	}
}
