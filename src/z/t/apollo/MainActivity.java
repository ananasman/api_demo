package z.t.apollo;

import android.R.string;
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
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private ArrayAdapter<ListCellData> adapter;
	private SharedPreferences sp;
	final int EXIT = 0, SETTING = 1, ABOUT = 2, ORIENTATION = 3;
	/**
	 * 返回按键的点击时间
	 */
	private long clicktime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<ListCellData>(this,
				android.R.layout.simple_list_item_1);
		sp = getSharedPreferences("mysp", Context.MODE_PRIVATE);
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
		SubMenu menu2 = menu.addSubMenu(1, SETTING, 0, getResources()
				.getString(R.string.setting).toString());
		menu.add(0, ABOUT, 0, getResources().getString(R.string.about)
				.toString());
		menu.add(0, EXIT, 0, getResources().getString(R.string.exit).toString());
		menu2.add(1, ORIENTATION, 0,
				getResources().getString(R.string.orientation).toString())
				.setChecked(sp.getBoolean("option", false)).setCheckable(true);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case EXIT:
			finish();
			break;
		case ABOUT:
			new AlertDialog.Builder(this).setTitle("关于")
					.setMessage("当前版本 V1.0").show();
			break;
		case ORIENTATION:
			Editor e = sp.edit();
			e.putBoolean("option", !item.isChecked());
			e.commit();
			item.setChecked(sp.getBoolean("option", false));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
