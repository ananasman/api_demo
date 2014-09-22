package z.t.apollo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SharedPreferencesActivity extends Activity {
	public static final String ACTION = "z.t.apollo.SharedPreferencesActivity";
	public static final String SHOW_ON_STARTED = "showdialog";
	private CheckBox checkBox;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);
		checkBox = (CheckBox) findViewById(R.id.Shared_Preferences_checkBox);
		sp = getSharedPreferences("mysp", Context.MODE_PRIVATE);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Editor e = sp.edit();
				e.putBoolean(SHOW_ON_STARTED, isChecked);
				e.commit();
			}
		});
		checkBox.setChecked(sp.getBoolean(SHOW_ON_STARTED, true));
		if (checkBox.isChecked()) {
			new AlertDialog.Builder(this).setTitle("checkbox选中状态")
					.setMessage("checkbox已被选中").setPositiveButton("关闭", null)
					.setCancelable(false).show();
		}

	}
}
