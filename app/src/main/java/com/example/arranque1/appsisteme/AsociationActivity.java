package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AsociationActivity extends AppCompatActivity {

    EditText associatedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asociation);

        associatedAccount = (EditText)findViewById(R.id.associatedAccount);
    }

    public void associate(View v){
        /*String account = associatedAccount.getText().toString();
        //código
        startActivity(new Intent(AsociationActivity.this, WaitingActivity.class));
        */
        if (Session.getInstance().getuType() == UserType.GUARDIAN){
            Session.getInstance().setuIdVigilado("5a8439c6-52a3-4677-98d2-744dc05d31cd");

        }else if (Session.getInstance().getuType() == UserType.GUARDED){
            Session.getInstance().setuIdVigilante("59c38a4b-2599-4961-9edd-6cae010ccb43");

        }
        startActivity(new Intent(AsociationActivity.this, WaitingActivity.class));
    }
}
