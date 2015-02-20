package com.example.inclass07;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class GalleryActivity extends Activity {

	final static String CONSUMER_KEY = "XLdrnKGVdI2AyqwYqrPnU8wAjxccAJYN7llmboIj";
	final static String BASE_URL = "https://api.500px.com/v1/photos/search";
	final static String IMAGESIZE = "4";
	final static String RPP = "50";

	ProgressDialog progressDialog;
	DatabaseDataManager dm;

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

		PhotoAdapter adapter = new PhotoAdapter(this, R.layout.row_item_layout,
				result);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				Intent intent = new Intent(GalleryActivity.this,
						DetailsActivity.class);
				intent.putExtra("photoObj", result.get(position));
				startActivity(intent);
			}
		});

		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				dm = new DatabaseDataManager(GalleryActivity.this);
				Photo photo = result.get(position);
				ImageView starImage = (ImageView) view
						.findViewById(R.id.starImage);
				if (dm.get(photo.get_id()) == null) {
					dm.savephoto(photo);
					starImage.setImageResource(R.drawable.favorites_yellow);
					Toast.makeText(GalleryActivity.this,
							"The Photo item is added into the database",
							Toast.LENGTH_SHORT).show();
				} else {
					dm.delete(photo);
					starImage.setImageResource(R.drawable.favorites_grey);
					Toast.makeText(GalleryActivity.this,
							"The Photo item is deleted from the database",
							Toast.LENGTH_SHORT).show();
				}
				Log.d("demo", dm.getAllphotos().toString());
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
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
