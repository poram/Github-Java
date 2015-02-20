package com.example.inclass07;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		if (getIntent().hasExtra("photoObj")) {

			Photo photo = (Photo) getIntent().getSerializableExtra("photoObj");
			TextView photoname = (TextView) findViewById(R.id.photoname);
			photoname.setText(photo.getName());

			TextView ownername = (TextView) findViewById(R.id.ownername);
			ownername.setText("By: " + photo.getFullName());

			ImageView image = (ImageView) findViewById(R.id.image);

			if(!photo.getUrl().isEmpty())
				Picasso.with(this).load(photo.getUrl()).into(image);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
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
