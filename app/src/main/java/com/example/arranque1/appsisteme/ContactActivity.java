package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void iniciar(View v) {startActivity(new Intent(this, AddActivity.class));}
    public void ver (View v) {startActivity(new Intent(this, ListContactActivity.class));}
}
