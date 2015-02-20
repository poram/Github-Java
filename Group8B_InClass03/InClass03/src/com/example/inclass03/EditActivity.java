/*
 * Assignment 3
 * FileName: EditActivity.Java
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

public class EditActivity extends Activity {

	Student student;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		final EditText name = (EditText) findViewById(R.id.name);
		name.setVisibility(View.INVISIBLE);
		final EditText email = (EditText) findViewById(R.id.emailText);
		email.setVisibility(View.INVISIBLE);
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rg.setVisibility(View.INVISIBLE);
		final Switch accountState = (Switch) findViewById(R.id.switch1);
		accountState.setVisibility(View.INVISIBLE);
		final SeekBar mood = (SeekBar) findViewById(R.id.mood);
		mood.setVisibility(View.INVISIBLE);
		findViewById(R.id.language).setVisibility(View.INVISIBLE);
		findViewById(R.id.textView3).setVisibility(View.INVISIBLE);

		student = (Student) getIntent().getExtras().getSerializable(
				MainActivity.STUDENT);
		name.setText(student.getName());
		email.setText(student.getEmailAddress());
		if (student.getLanguage().equalsIgnoreCase(
				getResources().getString(R.id.radio_java))) {
			rg.check(R.id.radio_java);
		} else if (student.getLanguage().equalsIgnoreCase(
				getResources().getString(R.id.radio_c))) {
			findViewById(R.id.language).setVisibility(View.VISIBLE);
			rg.check(R.id.radio_c);
		} else if (student.getLanguage().equalsIgnoreCase(
				getResources().getString(R.id.radio_c1))) {
			findViewById(R.id.textView3).setVisibility(View.VISIBLE);
			rg.check(R.id.radio_c1);
		}

		accountState.setChecked(student.isAccountState());
		mood.setProgress(student.getMood());
		int imageId = getIntent().getExtras().getInt("ImageView");
		if (imageId == R.id.ImageView01) {
			name.setVisibility(View.VISIBLE);
		} else if (imageId == R.id.ImageView02) {
			email.setVisibility(View.VISIBLE);
		} else if (imageId == R.id.ImageView03) {
			rg.setVisibility(View.VISIBLE);
		} else if (imageId == R.id.ImageView04) {
			accountState.setVisibility(View.VISIBLE);
		} else if (imageId == R.id.ImageView05) {
			mood.setVisibility(View.VISIBLE);
		}

		findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
			Student student;

			@Override
			public void onClick(View v) {
				student = new Student();

				if (check()) {
					Intent intent = new Intent(EditActivity.this,
							DisplayActivity.class);
					intent.putExtra(MainActivity.STUDENT, student);
					startActivityForResult(intent, 100);
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
					Toast.makeText(EditActivity.this,
							"All Fields are required", Toast.LENGTH_LONG)
							.show();
				}

				if (rg.getCheckedRadioButtonId() == R.id.radio_java) {
					student.setLanguage(getResources().getString(
							R.id.radio_java));
				} else if (rg.getCheckedRadioButtonId() == R.id.radio_c) {
					student.setLanguage(getResources().getString(R.id.radio_c));
				} else {
					student.setLanguage(getResources().getString(R.id.radio_c1));
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
		getMenuInflater().inflate(R.menu.edit, menu);
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
