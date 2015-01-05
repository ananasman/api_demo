package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RatingBarActivity extends Activity {
	static final String ACTION = "z.t.apollo.activity.RatingBarActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating_bar);
	}
}
