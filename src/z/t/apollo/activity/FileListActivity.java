package z.t.apollo.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	public final static String ACTION = "z.t.apollo.activity.FileListActivity";
	private ListView listView;
	private TextView textView;
	private File currentParent;
	private Button button;
	File[] currentFiles;
	// 获取sd卡路径
	final String path = "/";

	// final String path = Environment.getExternalStorageDirectory().toString();

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
					currentFiles[position].getName().toString().endsWith("");
					// Intent intent = new Intent(SDSaveActivity.ACTION);
					//如果选中一个文件，就根据文件的类型去用相应的应用去打开。
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.putExtra("path", currentFiles[position].toString());
					intent.setType(getMIMEType(new File(currentFiles[position]
							.toString())));
					// intent.createChooser(intent, "选择");
					startActivity(Intent.createChooser(intent, "选择"));
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
						Toast.makeText(FileListActivity.this, "已是根目录", Toast.LENGTH_SHORT)
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

	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 */
	private String getMIMEType(File file) {
		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) {
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * MIME：全称Multipurpose Internet Mail Extensions，多功能Internet
	 * 邮件扩充服务。它是一种多用途网际邮件扩充协议，在1992年最早应用于电子邮件系统，但后来也应用到浏览器。MIME类型就是设定某种扩展名的文件用一
	 * 种应用程序来打开的方式类型，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。多用于指定一些客户端自定义的文件名，以及一些媒体文件
	 * 打开方式。
	 * 
	 * 在Android中通过文件的MIME类型来判断有哪些应用程序可以处理这些文件，并使用其中的某一个应用程序（如果有多个可选的应用程序，
	 * 则用户必须指定一个）处理之。
	 * 
	 * 我在写android资源管理器（文件浏览器）的时候，希望能在资源管理器的中实现打开文件的操作，此时就需要用到文件的MIME类型。
	 */
	private final String[][] MIME_MapTable = {
			// {后缀名， MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" },
			{ ".c", "text/plain" }, { ".class", "application/octet-stream" },
			{ ".conf", "text/plain" }, { ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".exe", "application/octet-stream" }, { ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" },
			{ ".h", "text/plain" }, { ".htm", "text/html" },
			{ ".html", "text/html" }, { ".jar", "application/java-archive" },
			{ ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" },
			{ ".log", "text/plain" }, { ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" }, { ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" }, { ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".prop", "text/plain" },
			{ ".rar", "application/x-rar-compressed" },
			{ ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
			{ ".rtf", "application/rtf" }, { ".sh", "text/plain" },
			{ ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" },
			// {".xml", "text/xml"},
			{ ".xml", "text/plain" }, { ".z", "application/x-compress" },
			{ ".zip", "application/zip" }, { "", "*/*" } };

}
