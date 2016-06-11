package com.example.arranque1.appsisteme;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;

import java.util.List;

public class ListContactActivity extends AppCompatActivity {
    ListView lv;
    ListAdapter adapter;
    DaoContacts dContacts;
    ImageButton addContactButton;
    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        lv = (ListView) findViewById(R.id.contactListID);

        headerText = (TextView) findViewById(R.id.headerText);
        if (Session.getInstance().getuType() == UserType.GUARDIAN) {
            String perfil = "VIGILANTE";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilante));
        } else if (Session.getInstance().getuType() == UserType.GUARDED) {
            String perfil = "VIGILADO";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilado));
        }

        addContactButton = (ImageButton) findViewById(R.id.add_button);
        dContacts = new DaoContacts(this);
        List<Contact> listContacts = dContacts.getListaContactos();
        adapter = new ListAdapter(this, listContacts);
        lv.setAdapter(adapter);
        addContactButton.setOnClickListener(addListener);

        final TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("PETICIÓN");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", getDrawable(R.drawable.icono_peticion));
        host.addTab(spec);

        spec = host.newTabSpec("TELÉFONOS");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getDrawable(R.drawable.icono_telefonos));
        host.addTab(spec);

        spec = host.newTabSpec("LOG");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", getDrawable(R.drawable.icono_menu));
        host.addTab(spec);

        spec = host.newTabSpec("SALIR");
        spec.setContent(R.id.tab4);
        spec.setIndicator("", getDrawable(R.drawable.icono_salir));
        host.addTab(spec);

        host.setCurrentTab(1);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if (tabId.equals("PETICIÓN")) {
                    if(Session.getInstance().getuType()==UserType.GUARDED){
                        startActivity(new Intent(ListContactActivity.this, VigiladoMainActivity.class));
                    }else if(Session.getInstance().getuType()==UserType.GUARDIAN){
                        startActivity(new Intent(ListContactActivity.this, VigilanteMainActivity.class));
                    }
                    finish();
                }

                if(tabId.equals("LOG")){
                    startActivity(new Intent(ListContactActivity.this,LogActivity.class));
                    finish();
                }

                if(tabId.equals("SALIR")){
                    startActivity(new Intent(ListContactActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Session.getInstance().getuType() == UserType.GUARDIAN) {
            String perfil = "VIGILANTE";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilante));
        } else if (Session.getInstance().getuType() == UserType.GUARDED) {
            String perfil = "VIGILADO";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilado));
        }
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
            ImageView contactImage;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.list_view_row_contacts, parent, false);
            textView = (TextView) itemView.findViewById(R.id.contact_name);
            settingsButton = (ImageButton)itemView.findViewById(R.id.settings);
            contactImage = (ImageView)itemView.findViewById(R.id.contact_photo);

            final Contact selectedContact = (Contact) getItem(position);
            textView.setText(selectedContact.getName());

            Bitmap contactPhoto = BitmapFactory.decodeFile(selectedContact.getImageSource());

            contactImage.setImageBitmap(contactPhoto);

            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ListContactActivity.this, ModifyContactActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", selectedContact.getId());
                    b.putString("name", selectedContact.getName());
                    b.putString("phone", selectedContact.getPhone());
                    b.putString("imageSource", selectedContact.getImageSource());
                    i.putExtras(b);
                    startActivity(i);
                    finish();
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (ActivityCompat.checkSelfPermission(ListContactActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + selectedContact.getPhone() + "")));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            return itemView;
        }
    }
}


