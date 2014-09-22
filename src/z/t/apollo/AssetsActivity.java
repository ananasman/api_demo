package z.t.apollo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AssetsActivity extends Activity {
	public final static String ACTION = "z.t.apollo.AssetsActivity";
	private TextView tv;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assets);
		
	}
}
