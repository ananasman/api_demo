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
	private Button button;
	private NotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		button = (Button) findViewById(R.id.btnshownotification);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(R.layout.activity_notification);
		button.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				@SuppressWarnings("deprecation")
				Notification notification = new Notification(
						android.R.drawable.ic_dialog_alert, "有个新消息", System
								.currentTimeMillis());
				notification.setLatestEventInfo(NotificationActivity.this,
						"苹果土豆一起来，为Lumia930送电", "苹果土豆一起来，为Lumia930送电",
						PendingIntent.getActivity(NotificationActivity.this, 1,
								getIntent(), 0));
				manager.notify(R.layout.activity_notification, notification);
			}
		});
	}
}
