package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arranque1.appsisteme.bbdd.DaoContacts;

public class MainActivity extends AppCompatActivity {
    EditText etPhone,etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);

    }

    public void iniciar(View v) {startActivity(new Intent(this, AddActivity.class));}
    public void ver (View v) {startActivity(new Intent(this,ListContactActivity.class));}
}
