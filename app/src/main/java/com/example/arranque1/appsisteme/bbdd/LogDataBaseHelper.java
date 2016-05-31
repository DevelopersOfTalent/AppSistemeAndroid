package com.example.arranque1.appsisteme.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergio Cano on 30/05/2016.
 */
public class LogDataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Log";
    public static final int VERSION = 1;
    public static final String CREATE_LOG_TABLE =
            "CREATE TABLE Log (_id integer primary key autoincrement," +
                    "state text,date text)";
    public static final String DATABASE_DELETE_LOG = "DROP TABLE IF EXIST Log";

    public LogDataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
