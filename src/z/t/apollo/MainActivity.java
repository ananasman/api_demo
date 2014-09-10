package z.t.apollo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		// adapter.add(new ListCellData(this, "布局", new
		// Intent(MainActivity.this,
		// LayoutActivity.class)));
		// 隐式启动
		adapter.add(new ListCellData(this, "布局", new Intent(
				LayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "对话框", new Intent(
				DialogActivity.ACTION)));
		adapter.add(new ListCellData(this, "ProgressBar", new Intent(
				ProgressBarActivity.ACTION)));
		adapter.add(new ListCellData(this, "杂项", new Intent(
				SundriesActivity.ACTION)));
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data = adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}

}
