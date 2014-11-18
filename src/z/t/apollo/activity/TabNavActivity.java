package z.t.apollo.activity;

import z.t.apollo.fragment.DummyFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class TabNavActivity extends Activity implements ActionBar.TabListener {
	private static final String SELECTED_ITEM = "selected_item";
	public static final String ACTION = "z.t.apollo.activity.TabNavActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_nav);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("热点").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("科技").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("八卦").setTabListener(this));
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// 在一个Activity进入不可见状态，被onStop之前，系统会调用onSavedInstanceState来保存状态
		if (savedInstanceState.containsKey(SELECTED_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(SELECTED_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Fragment fragment = new DummyFragment();
		Bundle args = new Bundle();
		args.putInt(DummyFragment.SECTION_NUMBER, tab.getPosition() + 1);
		fragment.setArguments(args);
		ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment);
		ft.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}
}
