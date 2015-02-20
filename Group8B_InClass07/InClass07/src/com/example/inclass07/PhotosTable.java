package com.example.inclass07;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PhotosTable {
	static final String TABLENAME = "photos";
	static final String COLUMN_ID = "photoID";
	static final String COLUMN_TITLE = "photoName";
	static final String COLUMN_URL = "photoURL";
	static final String COLUMN_OWNER = "ownerName";

	static public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLENAME + "(");
		sb.append(COLUMN_ID + " integer primary key autoincrement, ");
		sb.append(COLUMN_TITLE + " text not null, ");
		sb.append(COLUMN_URL + " text not null, ");
		sb.append(COLUMN_OWNER + " text not null);");
		try {
			db.execSQL(sb.toString());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	static public void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLENAME);
		PhotosTable.onCreate(db);
	}
}
