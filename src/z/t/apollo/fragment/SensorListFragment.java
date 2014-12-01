package z.t.apollo.fragment;

import java.util.ArrayList;

import z.t.apollo.utils.SensorContent;
import android.app.Activity;
import android.app.ListFragment;
import android.hardware.Sensor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SensorListFragment extends ListFragment {
	private CallBack mCallBack;
	private SensorContent mSensorContent;
	private ArrayList<Sensor> sensors;

	public interface CallBack {
		public void onItemSelected(int id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sensors = (ArrayList<Sensor>) mSensorContent.getManager()
				.getSensorList(Sensor.TYPE_ALL);
		setListAdapter(new ArrayAdapter<Sensor>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, sensors));
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof CallBack)) {
			try {
				throw new IllegalAccessException("Booklistbook所在的Activity必须实现");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		mCallBack = (CallBack) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallBack = null;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mCallBack.onItemSelected(sensors.get(position).getType());
		Log.v("传感器", sensors.get(position).toString());
	}
}
