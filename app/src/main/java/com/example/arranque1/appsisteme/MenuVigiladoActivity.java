package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuVigiladoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vigilado);


    }

    public void state(View v) {startActivity(new Intent(this, StateActivity.class));}
    public void list(View v) {startActivity(new Intent(this, ContactActivity.class));}
    public void log(View v) {
        Toast.makeText(this, "No hay historial", Toast.LENGTH_SHORT).show();
    }

    public void out(View v) {
        Toast.makeText(this, "Vas a cerrar la aplicación", Toast.LENGTH_SHORT).show();
    }
}

