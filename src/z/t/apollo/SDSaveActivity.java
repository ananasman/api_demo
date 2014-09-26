package z.t.apollo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SDSaveActivity extends Activity {
	private Button button;
	private EditText editText;
	public final static String ACTION = "z.t.apollo.SDSaveActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdsave);
		button = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.editText1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				File dir = Environment.getExternalStorageDirectory();
				Log.v("dir", dir.toString());
				if (dir == null||dir.toString()=="") {
					Toast.makeText(SDSaveActivity.this, "没有sd卡",
							Toast.LENGTH_SHORT).show();
				}
				File file = new File(dir, "readme.txt");
				try {
					if (!file.exists()) {
						file.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(editText.getText().toString().getBytes("utf-8"));
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
