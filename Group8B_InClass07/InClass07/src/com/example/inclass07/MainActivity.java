package com.example.inclass07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String searchValue;

	public static String TERM = "term";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText editText = (EditText) findViewById(R.id.editTextMain);
		findViewById(R.id.mainbutton).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						searchValue = editText.getText().toString();
						if (!searchValue.isEmpty()) {
							Intent intent = new Intent(MainActivity.this,
									GalleryActivity.class);
							intent.putExtra(TERM, searchValue);
							startActivity(intent);
						} else
							Toast.makeText(MainActivity.this,
									"No Value entered", Toast.LENGTH_LONG)
									.show();

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
