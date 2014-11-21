package z.t.apollo.adapter;

import z.t.apollo.activity.utils.MyFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.viewpagerindicator.IconPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {
	// 标签名称
	static final String[] CONTENT = new String[] { "wp之家", "win10之家", };

	private int mCount = CONTENT.length;

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getIconResId(int index) {
		return 0;
	}

	@Override
	public Fragment getItem(int arg0) {
		// 想不通在什么情况下arg0会等于CONTENT.length
//		 return MyFragment.newInstance(CONTENT[arg0 % CONTENT.length]);
		 return MyFragment.newInstance(CONTENT[arg0 % CONTENT.length]);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return FragmentAdapter.CONTENT[position % CONTENT.length];
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}
