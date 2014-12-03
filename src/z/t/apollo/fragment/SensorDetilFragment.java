package z.t.apollo.fragment;

import z.t.apollo.activity.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SensorDetilFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sensor_detil, container, false);
		((TextView) view.findViewById(R.id.textView1)).setText("4444444444");
		return view;
	}
}
