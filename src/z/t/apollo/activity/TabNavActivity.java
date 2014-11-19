package z.t.apollo.activity;

import com.viewpagerindicator.PageIndicator;

import z.t.apollo.adapter.FragmentAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class TabNavActivity extends BaseSampleActivity {

	public static final String ACTION = "z.t.apollo.activity.TabNavActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tab_nav);
		mAdapter = new FragmentAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (PageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		mIndicator.setCurrentItem(mAdapter.getCount() - 1);
	}
}
