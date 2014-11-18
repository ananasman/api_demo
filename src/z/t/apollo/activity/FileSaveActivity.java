package z.t.apollo.activity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileSaveActivity extends Activity {
	public final static String ACTION = "z.t.apollo.activity.FileSaveActivity";
	private Button button;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_save);
		button = (Button) findViewById(R.id.savetext);
		editText = (EditText) findViewById(R.id.editText1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveCurrenttext();
			}
		});
		readText();
	}

	public void readText() {
		try {
			InputStream is = openFileInput("text");
			try {
				byte[] bs = new byte[is.available()];
				is.read(bs);
				String str = new String(bs, "utf-8");
				is.close();
				editText.setText(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveCurrenttext() {
		try {
			OutputStream os = openFileOutput("text", Context.MODE_PRIVATE);
			os.write(editText.getText().toString().getBytes("utf-8"));
			os.flush();
			os.close();
			Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
