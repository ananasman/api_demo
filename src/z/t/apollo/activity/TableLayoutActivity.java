package z.t.apollo.activity;
import android.app.Activity;
import android.os.Bundle;

public class TableLayoutActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.TableLayoutActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_layout);
	}
}
