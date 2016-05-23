package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.arranque1.appsisteme.bbdd.DaoContacts;

public class AddActivity extends AppCompatActivity {
    EditText etPhone,etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
    }

    public void guardar (View v){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        DaoContacts daoLibros = new DaoContacts(this);
        Contact contact = new Contact(null,name,phone);
        daoLibros.addContact(contact);
        Toast.makeText(this, "Se ha dado de alta el contacto.", Toast.LENGTH_SHORT).show();
        Log.d(name, phone);
    }

}