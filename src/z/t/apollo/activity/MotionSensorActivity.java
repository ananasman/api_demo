package z.t.apollo.activity;

import java.util.List;

import z.t.apollo.fragment.SensorDetilFragment;
import z.t.apollo.fragment.SensorListFragment;
import z.t.apollo.fragment.SensorListFragment.CallBack;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MotionSensorActivity extends Activity implements
		SensorEventListener, CallBack {
	public static final String ACTION = "z.t.apollo.activity.MotionSersorActivity";
	private SensorManager manager;
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.activity_motion_sersor);
	}

	private void init() {
		manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
		// 创建传感器列表资源
		String[] str = new String[sensors.size()];
		// Sensor[] str = new Sensor[sensors.size()];
		for (Sensor sensor : sensors) {
			switch (sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				// str[sensors.indexOf(sensor)] = "加速度传感器";
				break;
			case Sensor.TYPE_LIGHT:
				// str[sensors.indexOf(sensor)] = "光线传感器";
				break;
			case Sensor.TYPE_GRAVITY:
				// str[sensors.indexOf(sensor)] = "重力传感器";
				break;
			case Sensor.TYPE_LINEAR_ACCELERATION:
				// str[sensors.indexOf(sensor)] = "线性加速传感器";
				break;
			case Sensor.TYPE_SIGNIFICANT_MOTION:
				// str[sensors.indexOf(sensor)] = "SIGNIFICANT_MOTION";
				break;
			case Sensor.TYPE_GAME_ROTATION_VECTOR:
				// str[sensors.indexOf(sensor)] = "GAME_ROTATION_VECTOR";
				break;
			case Sensor.TYPE_ORIENTATION:
				// str[sensors.indexOf(sensor)] = "方向传感器";
				break;
			default:
				// str[sensors.indexOf(sensor)] = sensor.getName();
				break;
			}
			System.out.println(sensor.toString());
			// str[sensors.indexOf(sensor)] = sensor.getName();
			// Log.v("当前位置", sensors.indexOf(sensor) + "");
			// Log.v("当前元素", str[sensors.indexOf(sensor)]);
		}
		// 使用setArgument像fragment中传递参数
		fragmentManager = getFragmentManager();
		transaction = fragmentManager.beginTransaction();
		SensorListFragment listFragment = new SensorListFragment();
		Bundle bundle = new Bundle();
		bundle.putStringArray("sensor", str);
		listFragment.setArguments(bundle);
		transaction.replace(R.id.sensor_list, listFragment);
		// transaction.add(R.id.container, listFragment);
		transaction.commit();
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
	public void showDetil() {
		getFragmentManager().beginTransaction()
				.replace(R.id.sensor_detil, new SensorDetilFragment()).commit();

	}

}
