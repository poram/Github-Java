/*
 * Assignment 3
 * FileName: DisplayActivity.Java
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
import android.widget.TextView;
import android.view.View;

public class DisplayActivity extends Activity implements View.OnClickListener {

	final static String SEARCH = " Searchable";
	final static String UN_SEARCH = " Unsearchable";
	final static String POSITIVE = " % Positive";
	Student student;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		student = (Student) getIntent().getExtras().getSerializable(
				MainActivity.STUDENT);
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(name.getText() + student.getName());
		TextView email = (TextView) findViewById(R.id.email);
		email.setText(email.getText() + student.getEmailAddress());
		TextView lang = (TextView) findViewById(R.id.lang);
		lang.setText(lang.getText() + student.getLanguage());
		TextView acctState = (TextView) findViewById(R.id.account);
		if (student.isAccountState()) {
			acctState.setText(acctState.getText() + SEARCH);
		} else
			acctState.setText(acctState.getText() + UN_SEARCH);

		TextView mood = (TextView) findViewById(R.id.moodValue);
		String moodValue = student.getMood() + POSITIVE;
		mood.setText(mood.getText() + moodValue);
		
		findViewById(R.id.ImageView01).setOnClickListener(this);
		findViewById(R.id.ImageView02).setOnClickListener(this);
		findViewById(R.id.ImageView03).setOnClickListener(this);
		findViewById(R.id.ImageView04).setOnClickListener(this);
		findViewById(R.id.ImageView05).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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

	@Override
	public void onClick(View v) {
		Intent intent = new Intent("com.example.inclass03.EditActivity.intent.action.VIEW");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(MainActivity.STUDENT, student);
		intent.putExtra("ImageView", v.getId());
		startActivity(intent);
	}
}
