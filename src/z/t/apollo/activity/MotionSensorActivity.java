package z.t.apollo.activity;

import z.t.apollo.fragment.SensorListFragment.CallBack;
import z.t.apollo.utils.SensorContent;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MotionSensorActivity extends Activity implements
		SensorEventListener, CallBack {
	public static final String ACTION = "z.t.apollo.activity.MotionSersorActivity";
	SensorManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		new SensorContent(manager);
		setContentView(R.layout.activity_motion_sersor);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onItemSelected(int id) {
	}
}
