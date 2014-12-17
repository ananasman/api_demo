package z.t.apollo.activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SocketActivity extends Activity {
	static final String ACTION = "z.t.apollo.activity.SocketActivity";
	EditText editText1, editText2;
	TextView textView;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socket);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		textView = (TextView) findViewById(R.id.textView1);
		button = (Button) findViewById(R.id.button2);
		final LinearLayout layout = (LinearLayout) findViewById(R.id.container);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				send();
			}
		});
		// 让EditText所在的layout获得焦点
		layout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				layout.setFocusable(true);
				layout.setFocusableInTouchMode(true);
				layout.requestFocus();
				return false;
			}
		});
		// 给EditText添加一个TextWatcher
		// editText1.addTextChangedListener(new SearchWatcher(editText1));
		editText1.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String text = editText1.getText().toString();
				// isFocused()默认为true
				if (!editText1.isFocused()) {
					Toast.makeText(SocketActivity.this, "失去焦点",
							Toast.LENGTH_SHORT).show();
					// 失去焦点后隐藏软键盘
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
					if (!"".equals(text.trim()) || text != null
							|| text.trim().length() != 0) {
						connect();
					}
				}
			}
		});
		editText2.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!editText2.isFocused()) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(editText1.getWindowToken(), 0);
				}
			}
		});
	}

	// -----------------------------------------------------------------------------
	class SearchWatcher implements TextWatcher {
		private EditText editText;

		public SearchWatcher(EditText editText) {
			this.editText = editText;
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String text = editText.getText().toString();
			String str = stringFilter(text);
			if (!text.equals(str)) {
				editText.setText(str);
				editText.setSelection(str.length());
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {

		}

	}

	public static String stringFilter(String str) {
		System.out.println(str);
		// 只允许输入ip
		String regEx = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			System.out.println(m.matches());
			return str;
		}
		return "";
	}

	// -----------------------------------------------------------------------------
	Socket socket = null;
	BufferedWriter writer = null;
	BufferedReader reader = null;

	public void connect() {

		AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					socket = new Socket(editText1.getText().toString(), 65534);
					writer = new BufferedWriter(new OutputStreamWriter(
							socket.getOutputStream()));
					reader = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					publishProgress("@success");
				} catch (UnknownHostException e) {
					Toast.makeText(SocketActivity.this, "无法建立连接！",
							Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					Toast.makeText(SocketActivity.this, "无法建立连接！",
							Toast.LENGTH_SHORT).show();
				}
				try {
					String line;
					while ((line = reader.readLine()) != null) {
						publishProgress(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onProgressUpdate(String... values) {
				if (values[0].equals("@success")) {
					Toast.makeText(SocketActivity.this, "连接成功！",
							Toast.LENGTH_SHORT).show();
				}
				textView.append("别人说：" + values[0] + "\n");
				super.onProgressUpdate(values);
			}
		};
		read.execute();

	}

	public void send() {
		try {
			textView.append("我说：" + editText2.getText().toString() + "\n");
			writer.write(editText2.getText().toString() + "\n");
			writer.flush();
			editText2.setText("");
		} catch (Exception e) {
		}
	}
}
