package z.t.apollo;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XmlActivity extends Activity {
	public final static String ACTION = "z.t.apollo.XmlActivity";
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		XmlResourceParser p = getResources().getXml(R.xml.users);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		// LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
		// LinearLayout.LayoutParams.MATCH_PARENT,
		// LinearLayout.LayoutParams.WRAP_CONTENT);
		try {
			while (p.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if (p.getEventType() == XmlResourceParser.START_TAG) {
					if (p.getName().equals("user")) {
						String name = p.getAttributeValue(null, "name");
						String age = p.getAttributeValue(null, "age");
						tv = new TextView(this);
						tv.setText(String.format("姓名:%s,年龄:%s", name, age));
						ll.addView(tv);
					}
				}
				p.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentView(ll);
	}
}
