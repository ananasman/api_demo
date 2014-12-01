package z.t.apollo.utils;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MyFragment extends Fragment {
	private static final String KEY_CONTENT = "MyFragment:Content";
	private static List<String> mContent;

	public static MyFragment newInstance(String content) {
		MyFragment fragment = new MyFragment();
		mContent = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			mContent.add(content);
		}
		fragment.mContent = mContent;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 恢复到之前的状态
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getStringArrayList(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// 创建Tab中的内容
		// TextView text = new TextView(getActivity());
		// text.setGravity(Gravity.CENTER);
		// text.setText(mContent);
		// DisplayMetrics用来获取屏幕参数
		// text.setTextSize(20 * getResources().getDisplayMetrics().density);
		// text.setPadding(20, 20, 20, 20);
		PullToRefreshListView mListView = new PullToRefreshListView(
				getActivity());
		ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
				android.R.layout.simple_list_item_1, mContent);
		mListView.setAdapter(adapter);
		mListView.setPadding(10, 0, 10, 0);

		LinearLayout layout = new LinearLayout(getActivity());
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout.setGravity(Gravity.CENTER);
		// layout.addView(text);
		layout.addView(mListView);
		return layout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putString(KEY_CONTENT, mContent);
		Log.v("",
				outState.getString(KEY_CONTENT) + " "
						+ outState.getString(getTag()));
		outState.putStringArrayList(KEY_CONTENT, (ArrayList<String>) mContent);
	}
}
