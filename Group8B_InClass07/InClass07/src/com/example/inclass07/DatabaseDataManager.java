package com.example.inclass07;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseDataManager {

	private Context mContext;
	private DatabaseOpenHelper dbOpenHelper;
	private SQLiteDatabase db;
	private PhotoDAO photoDAO;

	public DatabaseDataManager(Context mContext) {
		this.mContext = mContext;
		dbOpenHelper = new DatabaseOpenHelper(this.mContext);
		db = dbOpenHelper.getWritableDatabase();
		photoDAO = new PhotoDAO(db);
	}

	public void close() {
		if (db != null) {
			db.close();
		}
	}

	public PhotoDAO getphotoDAO() {
		return this.photoDAO;
	}

	public long savephoto(Photo photo) {
		return this.photoDAO.save(photo);

	}

	public boolean updatephoto(Photo photo) {
		return this.photoDAO.update(photo);
	}

	public boolean delete(Photo photo) {
		return this.photoDAO.delete(photo);
	}

	public List<Photo> getAllphotos() {
		return this.photoDAO.getAllPhotos();

	}

	public Photo get(long id) {
		return this.photoDAO.get(id);
	}
}
