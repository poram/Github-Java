package com.example.inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	static final String DB_NAME = "photoList.db";
	static final int DB_VERSION = 1;

	public DatabaseOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		PhotosTable.onCreate(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		PhotosTable.onUpgrade(db, oldVersion, newVersion);

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

}
