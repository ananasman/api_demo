package z.t.apollo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteTextViewActivity extends Activity {
	public final static String ACTION = "z.t.apollo.AutoCompleteTextViewActivity";
	private AutoCompleteTextView actv;
	private MultiAutoCompleteTextView mactv;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_complete_text_view);
		actv = (AutoCompleteTextView) findViewById(R.id.actv);
		mactv = (MultiAutoCompleteTextView) findViewById(R.id.mactv);
		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line);
		adapter.addAll(getResources().getStringArray(R.array.res));
		actv.setAdapter(adapter);
		mactv.setAdapter(adapter);
	}
}
