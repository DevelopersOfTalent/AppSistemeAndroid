package com.example.arranque1.appsisteme;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
    ListAdapter adapter;
    DaoContacts dContacts;
    ImageButton settingsButton, addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        lv = (ListView)findViewById(R.id.contactListID);
        addContactButton = (ImageButton)findViewById(R.id.add_button);
        View inflater = getLayoutInflater().inflate(R.layout.list_view_row, null);
        dContacts = new DaoContacts(this);
        List<Contact> listContacts = dContacts.getListaContactos();
        adapter = new ListAdapter(this, listContacts);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListContactActivity.this, "Funciona", Toast.LENGTH_SHORT).show();
            }
        });
        //lv.setOnItemClickListener(new ListClickHandler());
        addContactButton.setOnClickListener(addListener);
    }

    View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ListContactActivity.this, AddActivity.class));
        }
    };

    public class ListAdapter extends BaseAdapter {

        Context context;
        List<Contact> contactList;
        LayoutInflater inflater;

        public ListAdapter(Context context, List<Contact> contactList) {
            this.context = context;
            this.contactList = contactList;
        }


        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public Contact getContact(int position){
            Contact contact = contactList.get(position);
            return contact;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            ImageButton settingsButton;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.list_view_row, parent, false);
            textView = (TextView)itemView.findViewById(R.id.contact_name);
            settingsButton = (ImageButton)itemView.findViewById(R.id.settings);

            final Contact selectedContact = getContact(position);
            textView.setText(selectedContact.getName());
            settingsButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ListContactActivity.this, ModifyContactActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", selectedContact.getId());
                    b.putString("name", selectedContact.getName());
                    b.putString("phone", selectedContact.getPhone());
                    i.putExtras(b);
                    startActivity(i);
                    finish();
                }
            });
            return itemView;
        }

    }

    /*public class ListClickHandler implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(ListContactActivity.this, "Ha llegado", Toast.LENGTH_SHORT).show();
            Log.d("position", String.valueOf(position));
        }
    }*/
}


