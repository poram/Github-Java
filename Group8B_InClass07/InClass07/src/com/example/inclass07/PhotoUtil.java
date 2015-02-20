/**
 * Assignment: In Class 06
 * FileName: PhotoUtil.Java
 * Names:
 * 	Udipta Roy
 * 	Sushmitha Yalla	
 *	Peter Oram 
 */

package com.example.inclass07;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhotoUtil {

	static public class PhotoJSONParser {
		static ArrayList<Photo> parsePhoto(String in) throws JSONException {
			ArrayList<Photo> photoList = new ArrayList<Photo>();

			JSONObject root = new JSONObject(in);
			JSONArray photoJSONArray = root.getJSONArray("photos");
			for (int i = 0; i < photoJSONArray.length(); i++) {
				JSONObject photoJSONObject = photoJSONArray.getJSONObject(i);
				Photo photo = new Photo();
				photo.set_id(photoJSONObject.getInt("id"));
				if (photoJSONObject.has("image_url"))
					photo.setUrl(photoJSONObject.getString("image_url"));
				else
					photo.setUrl("");
				photo.setName(photoJSONObject.getString("name"));

				photo.setFullName(photoJSONObject.getJSONObject("user")
						.getString("fullname"));

				photoList.add(photo);
			}

			return photoList;
		}

	}

}
