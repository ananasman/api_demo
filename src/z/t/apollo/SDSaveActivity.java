package z.t.apollo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import android.widget.Toast;

public class SDSaveActivity extends Activity {
	private Button button;
	private EditText editText, editText2;
	public final static String ACTION = "z.t.apollo.SDSaveActivity";
	final int list = 1;
	File targetfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdsave);
		String content = ""; // 文件内容字符串
		button = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				write();
			}
		});
		Intent intent = getIntent();
		String path = intent.getStringExtra("path");
		if (!path.equals(null) || path != "") {
			targetfile = new File(path);
			try {
				InputStream instream = new FileInputStream(targetfile);
				if (instream != null) {
					InputStreamReader inputreader = new InputStreamReader(
							instream);
					BufferedReader buffreader = new BufferedReader(inputreader);
					String line;
					// 分行读取
					while ((line = buffreader.readLine()) != null) {
						content += line + "\n";
					}
					instream.close();
					editText.setText(content);
					editText2.setText(targetfile.getName());
				}
			} catch (java.io.FileNotFoundException e) {
				Log.d("TestFile", "The File doesn't not exist.");
			} catch (IOException e) {
				Log.d("TestFile", e.getMessage());
			}
		}
	}

	public void write() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File dir = Environment.getExternalStorageDirectory();
			targetfile = new File(dir, editText2.getText().toString() + ".txt");
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
				Toast.makeText(this, "已保存", 0).show();
			} catch (FileNotFoundException e) {
				Log.v("有错误", "不能写入文件");
				e.printStackTrace();
			} catch (IOException e) {
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
