package com.example.arranque1.appsisteme;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;

import java.util.List;

public class ListContactActivity extends AppCompatActivity{
    ListView lv;
    DaoContacts dContacts;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        View inflateView = getLayoutInflater().inflate(R.layout.list_view_row, (ViewGroup)getWindow().getDecorView().getRootView());
        lv = (ListView)findViewById(R.id.contactList);
        settingsButton = (Button)findViewById(R.id.settings);
        dContacts = new DaoContacts(this);
        List<Contact> listContacts = dContacts.getListaContactos();
        ArrayAdapter<Contact> aAdapter = new ArrayAdapter<Contact>(this, R.layout.list_view_row, R.id.listText, listContacts);
        lv.setAdapter(aAdapter);
        lv.setOnItemClickListener(new ListClickHandler());
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = lv.getSelectedItemPosition();
                Contact selectedContact = (Contact)lv.getAdapter().getItem(position);
                Intent intent = new Intent(ListContactActivity.this,ModifyContactActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", selectedContact.getId());
                b.putString("name", selectedContact.getName());
                b.putString("phone", selectedContact.getPhone());
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
    }

    public class ListClickHandler implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(ListContactActivity.this, "Ha llegado", Toast.LENGTH_SHORT).show();
            Log.d("position", String.valueOf(position));
        }
    }
}


