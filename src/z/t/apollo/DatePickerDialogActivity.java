package z.t.apollo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerDialogActivity extends Activity {
	public static final String ACTION = "z.t.apollo.DatePickerDialogActivity";
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker_dialog);
		button = (Button) findViewById(R.id.btnDatePicker);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(DatePickerDialogActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								button.setText(String.format("%d:%s:%s", year,
										format(monthOfYear + 1),
										format(dayOfMonth)));
							}
						}, Integer.parseInt(button.getText().toString()
								.split(":")[0]), Integer.parseInt(button
								.getText().toString().split(":")[1])-1, Integer
								.parseInt(button.getText().toString()
										.split(":")[2])).show();
			}
		});
	}

	public String format(int value) {
		return value > 9 ? "" + value : "0" + value;
	}
}
