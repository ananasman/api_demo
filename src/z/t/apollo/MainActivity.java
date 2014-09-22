package z.t.apollo;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;
	private SharedPreferences sp;
	/**
	 * 返回按键的点击时间
	 */
	private long clicktime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		// 显式启动
		// adapter.add(new ListCellData(this, "布局", new
		// Intent(MainActivity.this,LayoutActivity.class)));
		// 隐式启动
		adapter.add(new ListCellData(this, "布局", new Intent(
				LayoutActivity.ACTION)));
		adapter.add(new ListCellData(this, "UI组件（四）", new Intent(
				AdapterViewActivity.ACTION)));
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

	/**
	 * 列表选项点击事件
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ListCellData data = adapter.getItem(position);
		data.startActivity();
		super.onListItemClick(l, v, position, id);
	}

	/**
	 * 返回按键点击事件
	 */
	@Override
	public void onBackPressed() {
		if (clicktime <= 0) {
			Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
			clicktime = System.currentTimeMillis();
		} else {
			long currentTime = System.currentTimeMillis();
			// 两次按键时间小于1秒就退出
			if (currentTime - clicktime < 1000) {
				finish();
			} else {
				Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
				clicktime = currentTime;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_option, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.exit:
			finish();
			break;
		case R.id.about:
			new AlertDialog.Builder(this).setTitle("关于")
					.setMessage("当前版本 V1.0").show();
			break;
		case R.id.orientation:
			sp = getSharedPreferences("mysp", Context.MODE_PRIVATE);
			Log.v("a",String.valueOf(item.isChecked()));
			item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Editor e = sp.edit();
					e.putBoolean("orientation", item.isChecked());
					
					Log.v("b",String.valueOf(item.isChecked()));
					e.commit();
					return false;
				}
			});
			item.setChecked(sp.getBoolean("orientation", true));
			if (item.isChecked()) {
				Log.v("c",String.valueOf(item.isChecked()));
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
