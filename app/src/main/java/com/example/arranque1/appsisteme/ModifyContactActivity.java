package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;
import com.example.arranque1.appsisteme.bbdd.dataBaseHelper;

public class ModifyContactActivity extends AppCompatActivity {
    EditText EditName, EditPhone;
    DaoContacts daocontact;
    int contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);
        EditName=(EditText)findViewById(R.id.EditName);
        EditPhone=(EditText)findViewById(R.id.EditPhone);
        Intent i = getIntent();
        daocontact = new DaoContacts(this);
        contactID = i.getExtras().getInt("id");
        String contactName = i.getExtras().getString("name");
        String contactPhone = i.getExtras().getString("phone");
        EditName.setText(contactName);
        EditPhone.setText(contactPhone);
    }

    public void edit (View v){
        String updatedContactName = EditName.getText().toString();
        String updatedContactPhone = EditPhone.getText().toString();
        Contact editedContact = new Contact(contactID,updatedContactName,updatedContactPhone);
        daocontact.editContact(editedContact);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void delete (View v){
        Intent i = getIntent();
        String selectedContactName = i.getExtras().getString("name");
        String selectedContactPhone = i.getExtras().getString("phone");
        Contact selectedContact = new Contact(contactID,selectedContactName,selectedContactPhone);
        daocontact.deleteContact(selectedContact);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
