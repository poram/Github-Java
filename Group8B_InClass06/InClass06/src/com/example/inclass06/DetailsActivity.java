/**
 * Assignment: In Class 06
 * FileName: DetailsActivity.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */


package com.example.inclass06;

import android.app.Activity;
import android.os.Bundle;
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
}