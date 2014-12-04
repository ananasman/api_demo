package z.t.apollo.fragment;

import java.util.List;

import z.t.apollo.activity.R;
import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SensorDetilFragment extends Fragment implements
		SensorEventListener {
	List<Sensor> sensors;
	SensorManager manager;
	int position;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.sensor_detil, container, false);
		Bundle bundle = getArguments();
		position = bundle.getInt("position");
		manager = (SensorManager) getActivity().getSystemService(
				Activity.SENSOR_SERVICE);
		sensors = manager.getSensorList(Sensor.TYPE_ALL);
		// for (Sensor sensor : sensors) {
		((TextView) view.findViewById(R.id.textView1)).append(position + "\n");
		// }
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		// switch (position) {
		// case 0:
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				manager.SENSOR_DELAY_GAME);
		// break;
		// case 1:
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_LIGHT),
				manager.SENSOR_DELAY_GAME);
		// break;
		// case 2:
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_GRAVITY),
				manager.SENSOR_DELAY_GAME);
		// break;
		// case 3:
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
				manager.SENSOR_DELAY_GAME);
		// break;
		// case 4:
		manager.registerListener(this,
				manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				manager.SENSOR_DELAY_GAME);
		// break;
		// }
	}

	@Override
	public void onStop() {
		manager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onPause() {
		manager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] fs = event.values;
		StringBuilder builder = new StringBuilder();
		switch (position) {
		case 0:
			builder.append("X:" + fs[0]);
			builder.append("\nY:" + fs[1]);
			builder.append("\nZ:" + fs[2]);
			((TextView) view.findViewById(R.id.textView1)).setText(builder
					.toString());
			break;
		case 1:
			builder.append("强度:" + fs[0]);
			((TextView) view.findViewById(R.id.textView1)).setText(builder
					.toString());
			break;
		case 2:
			((TextView) view.findViewById(R.id.textView1)).setText(position
					+ "");
			break;
		case 3:
			((TextView) view.findViewById(R.id.textView1)).setText(position
					+ "");
			break;

		case 4:
			builder.append("绕Z轴转过的角度:" + fs[0]);
			builder.append("\n绕X轴转过的角度:" + fs[1]);
			builder.append("\n绕Y轴转过的角度:" + fs[2]);
			((TextView) view.findViewById(R.id.textView1)).setText(builder
					.toString());
			break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}
