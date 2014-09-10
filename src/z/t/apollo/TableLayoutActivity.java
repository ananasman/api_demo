package z.t.apollo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TableLayoutActivity extends Activity {
	public static final String ACTION = "z.t.apollo.TableLayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_layout);
	}
}
