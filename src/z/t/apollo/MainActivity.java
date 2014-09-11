package z.t.apollo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;

	// public static final String ACTION = "z.t.apollo.MainActivity";

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
		adapter.add(new ListCellData(this, "ActionBar", new Intent(
				ActionBarActivity.ACTION)));
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data = adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}

	private long clicktime = 0;

	@Override
	public void onBackPressed() {
		if (clicktime <= 0) {
			Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
			clicktime = System.currentTimeMillis();
		}else{
			long currentTime = System.currentTimeMillis();
			if(currentTime-clicktime<1000){
				finish();
			}else{
				Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
				clicktime=currentTime;
			}
		}
	}
}
