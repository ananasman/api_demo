package z.t.apollo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LayoutActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;
	public final static String ACTION = "z.t.apollo.LayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		adapter.add(new ListCellData(this, "LinearLayout", new Intent(
				LinearLayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "TableLayout", new Intent(
				TableLayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "FrameLayout", new Intent(
				FrameLayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "RelativeLayout", new Intent(
				RelativeLayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "GridLayout", new Intent(
				GridLayoutActivity.ACTION)));
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
