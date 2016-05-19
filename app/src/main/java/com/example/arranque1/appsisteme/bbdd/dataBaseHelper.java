package com.example.arranque1.appsisteme.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by Arranque 1 on 18/05/2016.
 */
public class dataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contacts";
    public static final int VERSION = 1;
    public static final String CREATE_TABLE_CONTACTS =
            "CREATE TABLE contacts (_id integer primary key autoincrement," +
                    "name text,phone text)";
    public static final String DATABASE_DELETE_CONTACTS = "DROP TABLE IF EXIST contacts";

    public dataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
