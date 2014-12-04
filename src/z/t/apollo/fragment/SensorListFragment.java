package z.t.apollo.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import z.t.apollo.activity.R;
import z.t.apollo.fragment.BookListFragment.Callbacks;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SensorListFragment extends Fragment {
	private CallBack mCallBack;

	/**
	 * 接收sensor数组。
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.sensor_list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ListView listView = (ListView) getView().findViewById(R.id.listView1);
		System.out.println(getActivity());
		Bundle bundle = getArguments();
		String[] str = bundle.getStringArray("sensor");
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < str.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sensor", str[i]);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
				R.layout.list_item, new String[] { "sensor" },
				new int[] { android.R.id.text1 });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCallBack.showDetil(position);
			}
		});
	}

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
		mCallBack = (CallBack) activity;
	}

	// 当该Fragment从他所属的Activity中被删除时回调该方法。
	@Override
	public void onDetach() {
		super.onDetach();
		mCallBack = null;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

	}

	public interface CallBack {
		public void showDetil(int position);
	}
}
