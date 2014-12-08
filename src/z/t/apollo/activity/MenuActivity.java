package z.t.apollo.activity;

import z.t.apollo.fragment.LeftMenuFragment;
import z.t.apollo.utils.MainUI;
import android.os.Bundle;

public class MenuActivity extends android.support.v4.app.FragmentActivity {
	public static final String ACTION = "z.t.apollo.activity.MenuActivity";
	private MainUI mainUI;
	private LeftMenuFragment leftMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainUI = new MainUI(this);
		setContentView(mainUI);
		leftMenu = new LeftMenuFragment();
//getSupportFragmentManager().beginTransaction().add(777, leftMenu);
	}
}
