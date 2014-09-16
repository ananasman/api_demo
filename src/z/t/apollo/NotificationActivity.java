package z.t.apollo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends Activity {
	public static final String ACTION = "z.t.apollo.NotificationActivity";
	private Button btnShowNotification;
	private NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		btnShowNotification = (Button) findViewById(R.id.btnShowNotification);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// 从通知栏消除也要用指定的那个id
		manager.cancel(R.layout.activity_notification);
		btnShowNotification.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				Notification notification = new Notification(
						android.R.drawable.ic_dialog_alert, "有个新消息", System
								.currentTimeMillis());
				notification.setLatestEventInfo(NotificationActivity.this,
						"苹果土豆一起来，为Lumia930送电", "苹果土豆一起来，为Lumia930送电",
						PendingIntent.getActivity(NotificationActivity.this, 1,
								getIntent(), 0));
				// 给notification设置一个唯一的id
				manager.notify(R.layout.activity_notification, notification);
			}
		});
	}
}
