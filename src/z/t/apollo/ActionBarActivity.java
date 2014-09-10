package z.t.apollo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActionBarActivity extends Activity {
	public static final String ACTION = "z.t.apollo.ActionBarActivity";
	private Button show, hide;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar);
		show = (Button) findViewById(R.id.btnshowactionbar);
		hide = (Button) findViewById(R.id.btnhideactionbar);
		actionBar = getActionBar();
		show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				actionBar.show();
			}
		});
		hide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				actionBar.hide();
			}
		});
	}
}
