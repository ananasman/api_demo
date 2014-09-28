package z.t.apollo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SDSaveActivity extends Activity {
	private Button button;
	private EditText editText, editText2;
	public final static String ACTION = "z.t.apollo.SDSaveActivity";
	final int list = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdsave);
		button = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				write();
			}
		});
	}

	public void write() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File dir = Environment.getExternalStorageDirectory();
			File targetfile = new File(dir, editText2.getText().toString()
					+ ".txt");
			try {
				if (!targetfile.exists()) {
					System.out.println("create");
					targetfile.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(targetfile);
				System.out.println("开始写入文件");
				fos.write(editText.getText().toString().getBytes("utf-8"));
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				Log.v("有错误", "不能写入文件");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, list, 0, "查看文件列表");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == list) {
			Intent intent = new Intent(FileListActivity.ACTION);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
