package z.t.apollo.fragment;

import z.t.apollo.utils.BookContent;
import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookListFragment extends ListFragment {
	private Callbacks mCallbacks;

	// 该fragment通过这个接口和他所在的activity交互
	public interface Callbacks {
		public void onItemSelected(Integer id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, BookContent.ITEMS));
	}

	// 当Fragment被添加、显示到Activity时，回调该方法
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// 如果Activity没有实现callback接口，抛出异常
		if (!(activity instanceof Callbacks)) {
			try {
				throw new IllegalAccessException("Booklistbook所在的Activity必须实现");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		// 把Activity当成mcallbacks对象
		mCallbacks = (Callbacks) activity;
	}

	// 当该Fragment从他所属的Activity中被删除时回调该方法。
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	// 当点击某列表项的时候激发该方法
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mCallbacks.onItemSelected(BookContent.ITEMS.get(position).id);
	}

}
