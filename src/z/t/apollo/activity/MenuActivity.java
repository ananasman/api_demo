package z.t.apollo.activity;

import android.os.Bundle;
import android.widget.Toast;
import z.t.apollo.fragment.LeftMenuFragment;
import z.t.apollo.fragment.MiddleMenuFragment;
import z.t.apollo.utils.MainUI;

public class MenuActivity extends android.support.v4.app.FragmentActivity {
	public static final String ACTION = "z.t.apollo.activity.MenuActivity";
	private MainUI mainUI;
	private LeftMenuFragment leftMenu;
	private MiddleMenuFragment middleMenu;
	private long clicktime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainUI = new MainUI(this);
		getActionBar().hide();// 隐藏掉整个ActionBar，包括下面的Tabs
		setContentView(mainUI);
		leftMenu = new LeftMenuFragment();
		middleMenu = new MiddleMenuFragment();
		getSupportFragmentManager().beginTransaction()
				.add(MainUI.LEFT_ID, leftMenu).commit();
		getSupportFragmentManager().beginTransaction()
				.add(MainUI.MIDDLE_ID, middleMenu).commit();
	}

	/**
	 * 列表选项点击事件
	 */
	@Override
	public void onBackPressed() {
		if (clicktime <= 0) {
			Toast.makeText(this, "再按一下退出1", Toast.LENGTH_SHORT).show();
			clicktime = System.currentTimeMillis();
		} else {
			long currentTime = System.currentTimeMillis();
			// 两次按键时间小于1秒就退出
			if (currentTime - clicktime < 1000) {
				Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
				finish();
			} else {
				Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
				clicktime = currentTime;
			}
		}
	}
}
