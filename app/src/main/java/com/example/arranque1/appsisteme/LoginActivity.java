package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView username, password;
    TextView remindPassword, registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (AutoCompleteTextView)findViewById(R.id.username);
        password = (AutoCompleteTextView)findViewById(R.id.password);
        remindPassword = (TextView)findViewById(R.id.remindPassword);
        registration = (TextView)findViewById(R.id.registration);
    }

    public void login(View v){
        /*
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        //código
        startActivity(new Intent(LoginActivity.this, AsociationActivity.class));
        */
        startActivity(new Intent(LoginActivity.this, AsociationActivity.class));
    }
}
