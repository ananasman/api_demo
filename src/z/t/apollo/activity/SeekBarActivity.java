package z.t.apollo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarActivity extends Activity {
	public static final String ACTION = "z.t.apollo.activity.SeekBarActivity";
	private SeekBar seekBar1, seekBar2, seekBar3, seekBar4, seekBar5;
	private ProgressBar progressBar;
	private int progress1 = 0, progress2 = 0, progress3 = 0, progress4 = 0,
			progress5 = 0, count = 0;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seek_bar);
		seekBar1 = (SeekBar) findViewById(R.id.sbpercent1);
		seekBar2 = (SeekBar) findViewById(R.id.sbpercent2);
		seekBar3 = (SeekBar) findViewById(R.id.sbpercent3);
		seekBar4 = (SeekBar) findViewById(R.id.sbpercent4);
		seekBar5 = (SeekBar) findViewById(R.id.sbpercent5);
		progressBar = (ProgressBar) findViewById(R.id.pbpercent);
		textView = (TextView) findViewById(R.id.tvpercent);
		progressBar.setMax(100);
		seekBar1.setMax(20);
		seekBar2.setMax(20);
		seekBar3.setMax(20);
		seekBar4.setMax(20);
		seekBar5.setMax(20);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress1 = progress;
				count = progress1 + progress2 + progress3 + progress4
						+ progress5;
				progressBar.setProgress(count);
				textView.setText(String.format("%d", count) + "%");
			}
		});
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress2 = progress;
				count = progress1 + progress2 + progress3 + progress4
						+ progress5;
				progressBar.setProgress(count);
				textView.setText(String.format("%d", count) + "%");
			}
		});
		seekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress3 = progress;
				count = progress1 + progress2 + progress3 + progress4
						+ progress5;
				progressBar.setProgress(count);
				textView.setText(String.format("%d", count) + "%");
			}
		});
		seekBar4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress4 = progress;
				count = progress1 + progress2 + progress3 + progress4
						+ progress5;
				progressBar.setProgress(count);
				textView.setText(String.format("%d", count) + "%");
			}
		});
		seekBar5.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress5 = progress;
				count = progress1 + progress2 + progress3 + progress4
						+ progress5;
				progressBar.setProgress(count);
				textView.setText(String.format("%d", count) + "%");
			}
		});
	}
}
