package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyViewActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.MyViewActivity";
	ListView listView;
	ArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_view);
		adapter = new ArrayAdapter(this, R.layout.itemview, new String[] {
				"绿茶表", "心机表", "苹果表", "宝马表", "小婊砸" });
		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}
}
