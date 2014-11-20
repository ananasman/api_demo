package z.t.apollo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ListviewRefresActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.ListviewRefresActivity";
	PullToRefreshListView mListView;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_refres);
		mListView = (PullToRefreshListView) findViewById(R.id.mlv);
		List<String> list = new ArrayList<String>();
		list.add("hehehe");
		list.add("hahaha");
		list.add("heyheyhey");
		adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
				list);
		mListView.setAdapter(adapter);
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.add("zte");
						mListView.onRefreshComplete();
						super.onPostExecute(result);
					}
				}.execute();
			}
		});
	}
}
