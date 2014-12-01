package z.t.apollo.fragment;

import z.t.apollo.activity.R;
import z.t.apollo.utils.BookContent;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookDetilFragment extends Fragment {
	public static final String ITEM_ID = "item_id";
	BookContent.Book book;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 如果启动fragment是包含了item_id参数
		if (getArguments().containsKey(ITEM_ID)) {
			book = BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
		}
	}

	// 重写该方法，该方法返回的view将作为fragment显示组件
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.fragment_book_detail,
				container, false);
		if (book != null) {
			((TextView) rootview.findViewById(R.id.book_title))
					.setText(book.title);
			((TextView) rootview.findViewById(R.id.book_desc))
					.setText(book.desc);
		}
		return rootview;
	};

	@Override
	public void onPause() {
		super.onPause();
	}
}
