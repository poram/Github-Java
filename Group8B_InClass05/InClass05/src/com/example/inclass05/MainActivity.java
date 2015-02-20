/**
 * Assignment: In Class 05
 * FileName: MainActivity.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass05;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {

	final static String API_KEY = "0bac0a5feb9af76c529388029a7040ac";
	final static String METHOD = "flickr.photos.search";
	final static String URL = "https://api.flickr.com/services/rest/";
	static ArrayList<String> photoUrlList = new ArrayList<String>();
	static int current = 0;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText searchText = (EditText) findViewById(R.id.srchtxt);
		final Switch selectParser = (Switch) findViewById(R.id.switch1);
		
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Loading Image");

		ImageView nextButton = (ImageView) findViewById(R.id.nextimgview);
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (current == photoUrlList.size()) {
					new GetImage().execute(photoUrlList.get(0));
					current = 0;
				} else {
					new GetImage().execute(photoUrlList.get(current + 1));
					current++;
				}
			}
		});
		ImageView previousButton = (ImageView) findViewById(R.id.previmgview);
		previousButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (current == 0) {
					new GetImage().execute(photoUrlList.get(photoUrlList.size() - 1));
					current = photoUrlList.size();
				} else {
					new GetImage().execute(photoUrlList.get(current - 1));
					current--;
				}

			}
		});

		findViewById(R.id.go_btn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (isConnectedOnline()) {
							RequestParams params = new RequestParams("GET", URL);
							params.addParam("method", METHOD);
							params.addParam("api_key", API_KEY);
							params.addParam("text", searchText.getText()
									.toString());
							params.addParam("extras", "url_m");
							params.addParam("per_page", "20");
							params.addParam("format", "rest");
							GetPhotoAsyncTask fetchPhotoUrls = new GetPhotoAsyncTask();
							String[] parametersForList = new String[2];
							String value = "";
							parametersForList[0] = params.getEncodedURL();
							if (selectParser.isChecked())
								value = "s";
							else
								value = "p";
							parametersForList[1] = value;
							fetchPhotoUrls.execute(parametersForList);
						} else {
							Toast.makeText(MainActivity.this,
									"This is Not Connected", Toast.LENGTH_SHORT)
									.show();
						}
					}
				});
	}

	public class GetImage extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPostExecute(Bitmap result) {
			ImageView im = (ImageView) findViewById(R.id.flickrimg);
			im.setImageBitmap(result);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			InputStream in = null;
			try {
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setRequestMethod("GET");
				in = con.getInputStream();
				Bitmap image = BitmapFactory.decodeStream(in);
				return image;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			return null;
		}

	}

	private boolean isConnectedOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	class GetPhotoAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

		@Override
		protected ArrayList<String> doInBackground(String... params) {
			try {
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.connect();
				int statusCode = con.getResponseCode();
				if (statusCode == HttpURLConnection.HTTP_OK) {
					InputStream in = con.getInputStream();
					if (params[1].equalsIgnoreCase("s"))
						return PhotoUtils.PhotoSAXParser.parsePhoto(in);
					else if (params[1].equalsIgnoreCase("p"))
						return PhotoUtils.PhotoPullParser.getPhotosUrls(in);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			super.onPostExecute(result);
			if (result != null)
				Log.d("Result", result.toString());
			else
				Log.d("Result", "No Result");
			progressDialog.dismiss();
			photoUrlList = result;
			if (photoUrlList != null)
				new GetImage().execute(photoUrlList.get(0));
			current = 0;
		}

	}
}
