package z.t.apollo.activity;

import z.t.apollo.utils.ListCellData;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SensorActivity extends ListActivity {
	public final static String ACTION = "z.t.apollo.activity.SensorActivity";
	private ArrayAdapter<ListCellData> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		adapter.add(new ListCellData(this, "动作传感器", new Intent(
				MotionSensorActivity.ACTION)));
		setListAdapter(adapter);
	}

	/**
	 * 列表选项点击事件
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data = adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}
}
