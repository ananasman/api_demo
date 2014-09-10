package z.t.apollo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProgressDialogActivity extends Activity {
	private Button button;
	private ProgressDialog progressDialog;
	public static final String ACTIOIN = "z.t.apollo.ProgressDialogActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_dialog);
		button = (Button) findViewById(R.id.btnshowprogressdialog);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				progressDialog = ProgressDialog.show(
						ProgressDialogActivity.this, "wait", "......");
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						progressDialog.dismiss();
						super.run();
					}
				}.start();
			}
		});
	}
}
