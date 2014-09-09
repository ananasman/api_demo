package z.t.apollo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GridLayoutActivity extends Activity {
	public final static String ACTION = "z.t.apollo.GridLayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout);
	}
}
