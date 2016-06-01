package com.example.arranque1.appsisteme.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arranque1.appsisteme.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio Cano on 30/05/2016.
 */
public class DaoLog {
    public static final String TABLE_NAME = "Log";
    public static final String ID = "_id";
    public static final String STATE = "state";
    public static final String DATE = "date";
    private LogDataBaseHelper logDBHelper;

    public DaoLog(Context context) {
        logDBHelper = new LogDataBaseHelper(context);
    }

    public void addLog(Log log){
        SQLiteDatabase sql = logDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STATE,log.getState());
        cv.put(DATE,log.getDate());
        sql.insert(TABLE_NAME,null,cv);
        sql.close();
    }

    public List<Log> getLogList(){
        List<Log> logList = new ArrayList<>();
        SQLiteDatabase sql = logDBHelper.getReadableDatabase();
        String[] columns = {ID,STATE,DATE};
        Cursor cursor = sql.query(TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            Log log = new Log(cursor.getString(0),
                    cursor.getString(1));
            logList.add(log);
        }
        return logList;
    }
}
