package z.t.apollo.activity;

import z.t.apollo.fragment.BookDetilFragment;
import z.t.apollo.fragment.BookListFragment.Callbacks;
import android.app.Activity;
import android.os.Bundle;

public class FragmentActivity extends Activity implements Callbacks {
	public static final String ACTION = "z.t.apollo.activity.FragmentActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
	}

	@Override
	public void onItemSelected(Integer id) {
		Bundle bundle = new Bundle();
		bundle.putInt(BookDetilFragment.ITEM_ID, id);
		BookDetilFragment fragment = new BookDetilFragment();
		fragment.setArguments(bundle);
		getFragmentManager().beginTransaction()
				.replace(R.id.book_detail_container, fragment).commit();
	}
}
