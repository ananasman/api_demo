package z.t.apollo;

import java.util.zip.Inflater;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
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
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.my_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(MainActivity.ACTION));
			this.finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
