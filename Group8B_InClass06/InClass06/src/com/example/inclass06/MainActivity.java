/**
 * Assignment: In Class 06
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
						if(!searchValue.isEmpty()){
							Intent intent = new Intent(MainActivity.this,
									GalleryActivity.class);
							intent.putExtra(TERM, searchValue);
							startActivity(intent);
						}else
							Toast.makeText(MainActivity.this, "No Value entered", Toast.LENGTH_LONG).show();
						
					}
				});
	}

}
