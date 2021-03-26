package com.example.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.sqliteapp.DbConstants.EMAIL;
import static com.example.sqliteapp.DbConstants.NAME;
import static com.example.sqliteapp.DbConstants.TABLE_NAME;
import static com.example.sqliteapp.DbConstants.TEL;

public class DBHelper extends SQLiteOpenHelper {
        private final static String DATABASE_NAME="demo.db";
        private final static int DATABASE_VERSION=1;   //資料庫版本


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
    final String INIT_TABLE="create table " +TABLE_NAME+"("+_ID+" integer primary key autoincrement,"+NAME+" char,"+TEL+" char,"+EMAIL+" char)";
        db.execSQL(INIT_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE="drop table if exists "+TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}