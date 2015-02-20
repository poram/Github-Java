/**
 * Assignment: In Class 06
 * FileName: GalleryActivity.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */


package com.example.inclass06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GalleryActivity extends Activity {

	final static String CONSUMER_KEY = "s13jeYBaKgKjoVEvvL4kOyfyF7UaOnfQPurUx5uk";
	final static String BASE_URL = "https://api.500px.com/v1/photos/search";
	final static String IMAGESIZE = "4";
	final static String RPP = "50";

	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);

		progressDialog = new ProgressDialog(GalleryActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Loading Results");
		
		
		if (getIntent().hasExtra("term")) {
			String searchValue = getIntent().getExtras().getString("term");
			new GetPhotoAsyncTask().execute(searchValue);
		}

	}

	class GetPhotoAsyncTask extends AsyncTask<String, Void, ArrayList<Photo>> {

		@Override
		protected ArrayList<Photo> doInBackground(String... params) {

			RequestParams requestParams = new RequestParams("GET", BASE_URL);
			requestParams.addParam("consumer_key", CONSUMER_KEY);
			requestParams.addParam("term", params[0]);
			requestParams.addParam("image_size", IMAGESIZE);
			requestParams.addParam("rpp", RPP);
			try {
				HttpURLConnection con = requestParams.setupConnection();
				con.connect();
				if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					while (line != null) {
						sb.append(line);
						line = br.readLine();
					}
					return PhotoUtil.PhotoJSONParser.parsePhoto(sb.toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
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
		protected void onPostExecute(ArrayList<Photo> result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			createPhotoList(result);
		}
	}

	public void createPhotoList(final ArrayList<Photo> result) {
		ListView listView = (ListView) findViewById(R.id.photoList);
		final ArrayAdapter<Photo> adapter = new ArrayAdapter<Photo>(GalleryActivity.this,
				android.R.layout.simple_list_item_1, result);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(GalleryActivity.this,
						DetailsActivity.class);
				intent.putExtra("photoObj", result.get(position));
				startActivity(intent);
			}
		});

	}

}
