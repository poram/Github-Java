/*
 * Assignment 2
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 *  Pete Oram
 *  Sushmitha Yalla
 */

package com.example.inclass2a;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	double amount = 0.00;
	double result = 0.00;
	TextView tv;
	EditText textField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.clearall);
		textField = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView1);
		tv.setTextColor(Color.GRAY);
		btn.setOnClickListener(this);
		findViewById(R.id.euro).setOnClickListener(this);
		findViewById(R.id.canadian).setOnClickListener(this);
		findViewById(R.id.british).setOnClickListener(this);
		findViewById(R.id.japan).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String currency = "";
		amount = Double.parseDouble(textField.getText().toString());
		if (v.getId() == R.id.clearall) {
			textField.setText("");
			tv.setText("Result:");
			tv.setTextColor(Color.GRAY);
		} else {
			if (v.getId() == R.id.euro) {
				currency = "EUR";
				result = amount * 0.849282;
			} else if (v.getId() == R.id.canadian) {
				currency = "CAD";
				result = amount * 1.19;
			} else if (v.getId() == R.id.british) {
				currency = "GBP";
				result = amount * 0.65;
			} else if (v.getId() == R.id.japan) {
				currency = "JPY";
				result = amount * 117.62;
			}
			String finalResult = String.format("%.2f", result);
			tv.setText(amount + " USD = " + finalResult + " " + currency);
			tv.setTextColor(Color.BLACK);
		}
		
	}
}
