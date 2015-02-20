/*
 * Assignment 3
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 *  Pete Oram
 *  Sushmitha Yalla
 */

package com.example.inclass03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends Activity {

	final static String STUDENT = "Student";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText name = (EditText) findViewById(R.id.name);
		final EditText email = (EditText) findViewById(R.id.emailText);
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		final Switch accountState = (Switch) findViewById(R.id.switch1);
		final SeekBar mood = (SeekBar) findViewById(R.id.mood);
		mood.setProgress(0);
		mood.setMax(100);
		rg.check(R.id.radio_java);
		findViewById(R.id.submit).setOnClickListener(
				new View.OnClickListener() {
					Student student;

					@Override
					public void onClick(View v) {
						student = new Student();

						if (check()) {
							Intent intent = new Intent(MainActivity.this,
									DisplayActivity.class);
							intent.putExtra(STUDENT, student);
							startActivity(intent);
						}
					}

					private Boolean check() {
						Boolean check = false;
						if (!name.getText().toString().isEmpty()
								&& !email.getText().toString().isEmpty()) {
							student.setName(name.getText().toString());
							student.setEmailAddress(email.getText().toString());
							check = true;
						} else {
							Toast.makeText(MainActivity.this,
									"All Fields are required",
									Toast.LENGTH_LONG).show();
						}

						if (rg.getCheckedRadioButtonId() == R.id.radio_java) {
							student.setLanguage(getResources().getString(
									R.id.radio_java));
						} else if (rg.getCheckedRadioButtonId() == R.id.radio_c) {
							student.setLanguage(getResources().getString(
									R.id.radio_c));
						} else {
							student.setLanguage(getResources().getString(
									R.id.radio_c1));
						}
						if (accountState.isChecked())
							student.setAccountState(true);
						else
							student.setAccountState(false);

						student.setMood(mood.getProgress());

						return check;
					}
				});
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
}
