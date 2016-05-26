package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AsociationActivity extends AppCompatActivity {

    EditText associatedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asociation);

        associatedAccount = (EditText)findViewById(R.id.associatedAccount);
    }

    public void associate(){
        String account = associatedAccount.getText().toString();
        //código
        startActivity(new Intent(AsociationActivity.this, WaitingActivity.class));
    }
}
