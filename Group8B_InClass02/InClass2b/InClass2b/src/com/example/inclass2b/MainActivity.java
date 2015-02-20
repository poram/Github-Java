/*
 * Assignment 2
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 *  Pete Oram
 *  Sushmitha Yalla
 */
package com.example.inclass2b;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends Activity    {
	RadioGroup rg;
	double amount = 0.00;
	double result = 0.00;
	TextView tv;
	EditText textField;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=(RadioGroup) findViewById(R.id.radioGroup1);
        rg.check(R.id.radioButton1);
        textField = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textView1);
		tv.setTextColor(Color.GRAY);
        Button b= (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String currency = "";
				amount = Double.parseDouble(textField.getText().toString());
				if (rg.getCheckedRadioButtonId() == R.id.radioButton5) {
					textField.setText("");
					tv.setText("Result:");
					tv.setTextColor(Color.GRAY);
				} else {
					if (rg.getCheckedRadioButtonId() == R.id.radioButton1) {
						currency = "EUR";
						result = amount * 0.849282;
					} else if (rg.getCheckedRadioButtonId() == R.id.radioButton2) {
						currency = "CAD";
						result = amount * 1.19;
					} else if (rg.getCheckedRadioButtonId() == R.id.radioButton3) {
						currency = "GBP";
						result = amount * 0.65;
					} else if (rg.getCheckedRadioButtonId() == R.id.radioButton4) {
						currency = "JPY";
						result = amount * 117.62;
					}
					String finalResult = String.format("%.2f", result);
					tv.setText(amount + " USD = " + finalResult + " " + currency);
					tv.setTextColor(Color.BLACK);
				}
			}
		});
    }

}
