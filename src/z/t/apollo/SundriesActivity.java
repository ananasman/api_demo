package z.t.apollo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SundriesActivity extends ListActivity {
	public static final String ACTION = "z.t.apollo.SundriesActivity";
	private ArrayAdapter<ListCellData> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		adapter.add(new ListCellData(this, "Notification", new Intent(
				NotificationActivity.ACTION)));
		adapter.add(new ListCellData(this, "按钮点击效果", new Intent(
				ButtonActivity.ACTION)));
		adapter.add(new ListCellData(this, "读取XML原始数据", new Intent(
				XmlActivity.ACTION)));
		adapter.add(new ListCellData(this, "SharedPreferences保存基本设置", new Intent(
				SharedPreferencesActivity.ACTION)));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data = adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}
}
