package z.t.apollo.fragment;

import z.t.apollo.activity.ActionBarActivity;
import z.t.apollo.activity.AdapterViewActivity;
import z.t.apollo.activity.DialogActivity;
import z.t.apollo.activity.FragmentActivity;
import z.t.apollo.activity.LayoutActivity;
import z.t.apollo.activity.ProgressBarActivity;
import z.t.apollo.activity.R;
import z.t.apollo.activity.SensorActivity;
import z.t.apollo.activity.SundriesActivity;
import z.t.apollo.utils.ListCellData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MiddleMenuFragment extends Fragment {
	private ArrayAdapter<ListCellData> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.middle_menu, container, false);
		adapter = new ArrayAdapter<>(getActivity(),
				android.R.layout.simple_list_item_1);
		adapter.add(new ListCellData(getActivity(), "布局", new Intent(
				LayoutActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "UI组件（四）", new Intent(
				AdapterViewActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "对话框", new Intent(
				DialogActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "ProgressBar", new Intent(
				ProgressBarActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "杂项", new Intent(
				SundriesActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "传感器", new Intent(
				SensorActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "ActionBar", new Intent(
				ActionBarActivity.ACTION)));
		adapter.add(new ListCellData(getActivity(), "Fragment", new Intent(
				FragmentActivity.ACTION)));
		ListView listView = (ListView) view.findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListCellData data = adapter.getItem(position);
				data.startActivity();
			}
		});
		return view;
	}
}
