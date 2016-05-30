package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        finish();
    }

    public void delete (View v){
        Intent i = getIntent();
        String selectedContactName = i.getExtras().getString("name");
        String selectedContactPhone = i.getExtras().getString("phone");
        Contact selectedContact = new Contact(contactID,selectedContactName,selectedContactPhone);
        daocontact.deleteContact(selectedContact);
        finish();
    }

    public void deleteAlert(final View v){
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setTitle("¿Seguro que deseas borrar?");
        deleteDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(v);
            }
        });
        deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        deleteDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ModifyContactActivity.this,ListContactActivity.class));
    }
}
