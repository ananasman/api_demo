package z.t.apollo.utils;

import android.hardware.SensorManager;

public class SensorContent {
	private SensorManager manager;

	public SensorContent(SensorManager manager) {
		this.manager = manager;
	}

	public SensorManager getManager() {
		return manager;
	}

	public void setManager(SensorManager manager) {
		this.manager = manager;
	}
}
