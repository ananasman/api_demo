package z.t.apollo.activity;

import z.t.apollo.activity.utils.ListCellData;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DialogActivity extends ListActivity {
	public static final String ACTION = "z.t.apollo.activity.DialogActivity";
	private ArrayAdapter<ListCellData> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		adapter.add(new ListCellData(this,
				"DatePickerDialog\n\t\t\t\tAnd\nTimePickerDialog", new Intent(
						DatePickerDialogActivity.ACTION)));
		adapter.add(new ListCellData(this, "ProgressDialog", new Intent(
				ProgressDialogActivity.ACTIOIN)));
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
