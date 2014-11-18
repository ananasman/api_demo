package z.t.apollo.activity;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AssetsActivity extends Activity {
	public final static String ACTION = "z.t.apollo.activity.AssetsActivity";
	private TextView tv;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assets);
		tv = (TextView) findViewById(R.id.tvshowtxt);
		iv = (ImageView) findViewById(R.id.ivshowpgn);
		try {
			InputStream is = getAssets().open("readme.txt");
			byte[] bs = new byte[is.available()];
			is.read(bs);
			String str = new String(bs, "utf-8");
			is.close();
			tv.setText(str);
			is = getAssets().open("cantfound.png");
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			iv.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
