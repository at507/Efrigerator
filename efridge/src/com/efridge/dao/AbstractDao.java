package com.efridge.dao;

import android.database.sqlite.SQLiteDatabase;

public class AbstractDao {

	protected SQLiteDatabase db;

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
}
