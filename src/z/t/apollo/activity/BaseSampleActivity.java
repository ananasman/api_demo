package z.t.apollo.activity;

import java.util.Random;

import z.t.apollo.adapter.FragmentAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.viewpagerindicator.PageIndicator;

public class BaseSampleActivity extends FragmentActivity {
	private static final Random RANDOM = new Random();
	FragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	PullToRefreshListView mListView;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		init();
	}

	private void init() {
		mAdapter.CONTENT.add("agent phil");
		mAdapter.CONTENT.add("agent skye");
		mAdapter.CONTENT.add("agent mei");
		mAdapter.CONTENT.add("agent nick");
		mAdapter.CONTENT.remove(3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.random:
			final int page = RANDOM.nextInt(mAdapter.getCount());
			Toast.makeText(this, "Changing to page " + page + 1,
					Toast.LENGTH_SHORT).show();
			mPager.setCurrentItem(page);
			return true;

		case R.id.add_page:
			if (mAdapter.getCount() < 10) {
				mAdapter.CONTENT.add("11111");
				mAdapter.notifyDataSetChanged();
				mIndicator.notifyDataSetChanged();
			}
			return true;
		case R.id.remove_page:
			if (mAdapter.getCount() > 1) {
				// mAdapter.setCount(mAdapter.getCount() - 1);
				mAdapter.CONTENT.remove(mPager.getCurrentItem());
				mAdapter.notifyDataSetChanged();
				mIndicator.notifyDataSetChanged();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
