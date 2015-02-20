package com.example.inclass07;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoAdapter extends ArrayAdapter<Photo> {

	Context mContext;
	List<Photo> mData;
	int mResource;
	ImageView starImage;

	public PhotoAdapter(Context context, int resource, List<Photo> objects) {
		super(context, resource, objects);
		this.mContext = context;
		this.mResource = resource;
		this.mData = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(mResource, parent, false);
		}

		Photo photo = mData.get(position);

		ImageView photoImage = (ImageView) convertView
				.findViewById(R.id.photoImage);
		Picasso.with(mContext).load(photo.getUrl()).into(photoImage);

		TextView photoTitle = (TextView) convertView
				.findViewById(R.id.photoTitle);
		photoTitle.setText(photo.getName());

		return convertView;
	}

}
