package z.t.apollo.adapter;

import java.util.ArrayList;

import z.t.apollo.utils.MyFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {
	// 标签名称
	// public static final String[] CONTENT = new String[] { "wp之家", "win10之家",
	// "win7之家",
	// "xp之家" };
	public static ArrayList<String> CONTENT = new ArrayList<String>();
	private int mCount = CONTENT.size();

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
		// return MyFragment.newInstance(CONTENT[arg0 % CONTENT.length]);
//		return MyFragment.newInstance(CONTENT.get(arg0 % CONTENT.size()));
		return MyFragment.newInstance("");
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
//		return FragmentAdapter.CONTENT[position % CONTENT.length];
		return FragmentAdapter.CONTENT.get(position % CONTENT.size());
	}

	public void setCount(int count) {
		if (count > 0 && count <= 10) {
			mCount = count;
			notifyDataSetChanged();
		}
	}

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}
}
