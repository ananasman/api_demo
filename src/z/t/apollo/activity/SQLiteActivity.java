package z.t.apollo.activity;

import z.t.apollo.utils.Db;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteActivity extends Activity implements OnClickListener {
	public final static String ACTION = "z.t.apollo.activity.SQLiteActivity";
	private static EditText username, password;
	public Button login, register;
	Db db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(this);
		login.setOnClickListener(this);
		db = new Db(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			try {
				Toast.makeText(this, login() + " success", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.register:
			register();
			try {
				register.setVisibility(View.GONE);
				login.setVisibility(View.VISIBLE);
				Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	public String login() {
		String s = "";
		SQLiteDatabase dbReader = db.getReadableDatabase();
		Cursor cursor = dbReader.query("users", null,
				"username=? and password=?", new String[] {
						username.getText().toString(),
						password.getText().toString() }, null, null, null);
		while (cursor.moveToNext()) {
			s = cursor.getString(cursor.getColumnIndex("username"));
		}
		return s;
	}

	public void register() {
		SQLiteDatabase dbWrite = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("username", username.getText().toString());
		values.put("password", password.getText().toString());
		dbWrite.insert("users", null, values);
		db.close();
	}

}
