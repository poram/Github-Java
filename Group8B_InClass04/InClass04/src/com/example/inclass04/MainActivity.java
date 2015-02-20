/**
 * Assignment: In Class 04
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
	SeekBar seekbar;
	ProgressDialog progressDialog;
	TextView resultText;
	Handler handler;
	double generateNumber = 0;
	TextView seekBarText;
	Button asyncButton;
	Button threadButton;
	static String DIALOG_TEXT = "Retrieving the Number";
	static String TOAST_TEXT = "Complexity must be greater than Zero";
	ExecutorService threadPool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekbar = (SeekBar) findViewById(R.id.complex_seek);
		seekbar.setMax(10);
		resultText = (TextView) findViewById(R.id.result);

		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.setCancelable(false);
		progressDialog.setMessage(DIALOG_TEXT);

		seekBarText = (TextView) findViewById(R.id.seekText);
		asyncButton = (Button) findViewById(R.id.async);
		threadButton = (Button) findViewById(R.id.thread);
		threadPool = Executors.newFixedThreadPool(2);

		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			int progressValue = 0;

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				seekBarText.setText(progressValue + " "
						+ getResources().getString(R.string.seek_text));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				seekBarText.setText(progressValue + " "
						+ getResources().getString(R.string.seek_text));
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progressValue = progress;
			}
		});

		handler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {

				switch (msg.what) {
				case ThreadWork.STATUS_START:
					progressDialog.show();
					break;
				case ThreadWork.STATUS_STEP:
					progressDialog.setProgress((Integer) msg.obj);
					break;
				case ThreadWork.STATUS_DONE:
					resultText.setText(generateNumber + "");
					progressDialog.dismiss();
					break;
				default:
					break;
				}

				return false;
			}
		});

		asyncButton.setOnClickListener(this);
		threadButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class AsyncWork extends AsyncTask<Void, Integer, Void> {

		int numOfTimes;

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < numOfTimes; i++) {
				generateNumber = +HeavyWork.getNumber();
				publishProgress(i + 1);
			}
			generateNumber = generateNumber / seekbar.getProgress();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			resultText.setText(generateNumber + "");
			progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.show();
			numOfTimes = seekbar.getProgress();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);
		}
	}

	class ThreadWork implements Runnable {
		static final int STATUS_START = 0x00;
		static final int STATUS_STEP = 0x01;
		static final int STATUS_DONE = 0x02;
		private int numberOftimes;

		public ThreadWork(int setValue) {
			this.numberOftimes = setValue;
		}

		@Override
		public void run() {
			Message msg = new Message();
			msg.what = STATUS_START;
			handler.sendMessage(msg);

			for (int i = 0; i < numberOftimes; i++) {
				generateNumber = +HeavyWork.getNumber();
				msg = new Message();
				msg.what = STATUS_STEP;
				msg.obj = i + 1;
				handler.sendMessage(msg);
			}
			generateNumber = generateNumber / seekbar.getProgress();

			msg = new Message();
			msg.what = STATUS_DONE;
			handler.sendMessage(msg);

		}

	}

	@Override
	public void onClick(View v) {
		if (seekbar.getProgress() != 0) {
			if (v.getId() == R.id.thread) {
				threadPool.execute(new ThreadWork(seekbar.getProgress()));
			} else if (v.getId() == R.id.async) {
				new AsyncWork().execute();
			}
		} else {
			resultText.setText("");
			Toast.makeText(getApplicationContext(), TOAST_TEXT,
					Toast.LENGTH_SHORT).show();
		}
	}
}
