package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoleSelectActivity extends AppCompatActivity {

    Button vigilanteButton, vigiladoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);

        vigilanteButton = (Button)findViewById(R.id.vigilanteButton);
        vigiladoButton = (Button)findViewById(R.id.vigiladoButton);
        vigilanteButton.setOnClickListener(listenerVigilante);
        vigiladoButton.setOnClickListener(listenerVigilado);
    }

    View.OnClickListener listenerVigilante = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RoleSelectActivity.this, WaitingActivity.class);
            intent.putExtra("role","Vigilante");
            startActivity(new Intent(RoleSelectActivity.this, LoginActivity.class));
        }
    };

    View.OnClickListener listenerVigilado = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RoleSelectActivity.this, WaitingActivity.class);
            intent.putExtra("role","Vigilado");
            startActivity(new Intent(RoleSelectActivity.this, LoginActivity.class));
        }
    };
}
