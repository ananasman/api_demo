package z.t.apollo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LinearLayoutActivity extends Activity {
	public final static String ACTION = "z.t.apollo.LinearLayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout);
	}
}
