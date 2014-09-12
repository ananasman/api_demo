package z.t.apollo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DatePickerDialogActivity extends Activity {
	public static final String ACTION = "z.t.apollo.DatePickerDialogActivity";
	private Button btnDataPicker, btnTimePicker;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker_dialog);
		btnDataPicker = (Button) findViewById(R.id.btnDatePicker);
		btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

		// 设置按钮的文本为今天的日期
		btnDataPicker.setText((new SimpleDateFormat("yyyy:MM:dd"))
				.format(new Date(System.currentTimeMillis())));
		btnTimePicker.setText((new SimpleDateFormat("HH:mm")).format(new Date(
				System.currentTimeMillis())));
		btnDataPicker.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(DatePickerDialogActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// 在按钮上显示选择的时间
								btnDataPicker.setText(String.format("%d:%s:%s",
										year, format(monthOfYear + 1),
										format(dayOfMonth)));
							}
							// 将DatePickerDialog的默认时间设置为上次选择的
						}, Integer.parseInt(btnDataPicker.getText().toString()
								.split(":")[0]), Integer.parseInt(btnDataPicker
								.getText().toString().split(":")[1]) - 1,
						Integer.parseInt(btnDataPicker.getText().toString()
								.split(":")[2])).show();
			}
		});
		btnTimePicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new TimePickerDialog(DatePickerDialogActivity.this,
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								// 在按钮上显示选择的时间
								btnTimePicker.setText(String.format("%s:%s",
										format(hourOfDay), format(minute)));
								// btnTimePicker.setText(format(hourOfDay) + ":"
								// + format(minute));
							}
							// 将TimePickerDialog的默认时间设置为上次选择的
						}, Integer.parseInt(btnTimePicker.getText().toString()
								.split(":")[0]), Integer.parseInt(btnTimePicker
								.getText().toString().split(":")[1]), true)
						.show();
			}
		});
	}

	// 字符格式化，数字只有个位的时候前面补个0
	public String format(int value) {
		return value > 9 ? "" + value : "0" + value;
	}
}
