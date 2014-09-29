package z.t.apollo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FileListActivity extends Activity {
	public final static String ACTION = "z.t.apollo.FileListActivity";
	private ListView listView;
	private TextView textView;
	private File currentParent;
	private Button button;
	File[] currentFiles;
	// 获取sd卡路径
	final String path = Environment.getExternalStorageDirectory().toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_list);
		listView = (ListView) findViewById(R.id.fileslist);
		textView = (TextView) findViewById(R.id.tvfilepath);
		File root = new File(path);
		// 判断路径是否存在
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			inflateListView(currentFiles);
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (currentFiles[position].isFile()) {
					Toast.makeText(
							FileListActivity.this,
							"选中了" + currentFiles[position].getName().toString()
									+ "路径是" + currentFiles[position],
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SDSaveActivity.ACTION);
					intent.putExtra("path", currentFiles[position]);
					startActivity(intent);
					finish();
				} else {
					File[] tmp = currentFiles[position].listFiles();
					if (tmp == null || tmp.length == 0) {
						// Toast.makeText(
						// FileListActivity.this,
						// Environment.getExternalStorageDirectory()
						// .toString(), Toast.LENGTH_SHORT).show();
						Toast.makeText(FileListActivity.this,
								"当前路径不可访问或该路径下没有文件", Toast.LENGTH_SHORT).show();
					} else {
						currentParent = currentFiles[position];
						currentFiles = tmp;
						inflateListView(currentFiles);
					}

				}
			}
		});
		button = (Button) findViewById(R.id.btnroot);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (!currentParent.getCanonicalPath().equals(path)) {
						currentParent = currentParent.getParentFile();
						currentFiles = currentParent.listFiles();
						inflateListView(currentFiles);
					} else {
						Toast.makeText(FileListActivity.this, "已是根目录", 0)
								.show();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void inflateListView(File[] file) {
		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < file.length; i++) {
			Map<String, Object> listitem = new HashMap<String, Object>();
			if (file[i].isDirectory()) {
				listitem.put("icon", R.drawable.folder);
			} else {
				listitem.put("icon", R.drawable.file);
			}
			listitem.put("filename", file[i].getName());
			listitems.add(listitem);
		}
		SimpleAdapter sa = new SimpleAdapter(this, listitems, R.layout.line,
				new String[] { "icon", "filename" }, new int[] { R.id.folder,
						R.id.filename });
		listView.setAdapter(sa);
		try {
			textView.setText("当前路径为:" + currentParent.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
