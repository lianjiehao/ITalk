package com.stdu.edu.italk.localsava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SchoolFriSQLiteOpenHelper extends SQLiteOpenHelper{

	public SchoolFriSQLiteOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql_friend = "create table friend ("
				+ "id int PRIMARY KEY,"
				+ "loginName varchar(10),"
				+ "name varchar(20),"
				+ "ganQing varchar(10),"
				+ "xinBie varchar(10),"
				+ "yuanXi varchar(10),"
				+ "nianJi varchar(10)"
				+ ")";
		db.execSQL(sql_friend);
		
		String sql_person = "create table person ("
				+ "id int PRIMARY KEY,"
				+ "loginName varchar(10),"
				+ "loginPassword varchar(12),"
				+ "name varchar(20),"
				+ "ganQing varchar(10),"
				+ "xinBie varchar(10),"
				+ "yuanXi varchar(10),"
				+ "nianJi varchar(10)"
				+ ")";
		db.execSQL(sql_person);
		
		
		String sql_chatList = "create table chatList("
				+ "id int PRIMARY KEY,"
				+ "name varchar(20),"
				+ "belongs varchar(10),"
				+ "toLoginName varchar(20)"
				+ ")";
		db.execSQL(sql_chatList);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}
	
}
