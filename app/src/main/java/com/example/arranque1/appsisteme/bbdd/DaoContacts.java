package com.example.arranque1.appsisteme.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arranque1.appsisteme.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arranque 1 on 18/05/2016.
 */
public class DaoContacts {

    private static final String TABLE_NAME = "Contacts";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String PHONENUMBER = "phone";
    private dataBaseHelper dbHelper;

    public DaoContacts (Context context){dbHelper = new dataBaseHelper(context);}

    public void addContact (Contact contact){
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,contact.getName());
        cv.put(PHONENUMBER,contact.getPhone());
        sql.insert(TABLE_NAME, null, cv);
        sql.close();
    }

    public void editContact (Contact contact){
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, contact.getName());
        cv.put(PHONENUMBER,contact.getPhone());
        sql.update(TABLE_NAME, cv, "_id = " + String.valueOf(contact.getId()) , null);
        sql.close();
    }

    public void deleteContact (Contact contact){
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        sql.delete(TABLE_NAME, "_id = " + String.valueOf(contact.getId()), null);
        sql.close();
    }

    public List<Contact> getListaContactos(){
        List<Contact> listContacts = new ArrayList<>();
        SQLiteDatabase sql = dbHelper.getReadableDatabase();
        String[] columnasABuscar = {ID,NAME,PHONENUMBER};
        Cursor cursor = sql.query(TABLE_NAME, columnasABuscar, null, null, null, null, null);
        while(cursor.moveToNext()){
              Contact contact = new Contact(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            listContacts.add(contact);
        }
        return listContacts;
    }

}
