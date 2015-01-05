package z.t.apollo.activity;

import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ListviewRefresActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.ListviewRefresActivity";
	/**
	 * 下拉刷新列表
	 */
	private PullToRefreshListView mListView;
	/**
	 * 内容适配
	 */
	private ArrayAdapter<String> mAdapter;
	/**
	 * item内容集合
	 */
	private LinkedList<String> mItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_refres);
		mListView = (PullToRefreshListView) findViewById(R.id.mlv);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// 显示刷新时间
				String label = DateUtils.formatDateTime(
						getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				new GetDataTask().execute();
			}
		});
		// 真正的Listview
		ListView actualLV = mListView.getRefreshableView();
		// 添加显示数据
		mItems = new LinkedList<String>();
		mItems.add("历史数据1");
		mItems.add("历史数据2");
		mItems.add("历史数据3");
		mAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, mItems);
		// 设置刷新音效监听
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(
				this);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mListView.setOnPullEventListener(soundListener);
		actualLV.setAdapter(mAdapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mItems.addFirst("刷新后的数据");
			mAdapter.notifyDataSetChanged();
			mListView.onRefreshComplete();
		}
	}

}
