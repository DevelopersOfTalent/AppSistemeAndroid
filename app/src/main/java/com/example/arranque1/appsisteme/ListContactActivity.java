package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;

import java.util.List;

public class ListContactActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        lv = (ListView)findViewById(R.id.list);
        DaoContacts dContacts = new DaoContacts(this);
        List<Contact> listContacts = dContacts.getListaContactos();
        ArrayAdapter<Contact> aAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, listContacts);
        lv.setAdapter(aAdapter);
    }
}
