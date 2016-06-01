package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AssociationActivity extends AppCompatActivity {

    EditText associatedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association);

        associatedAccount = (EditText) findViewById(R.id.associatedAccount);
    }

    public void associate(View v) {
        /*String account = associatedAccount.getText().toString();
        //código
        startActivity(new Intent(AssociationActivity.this, WaitingActivity.class));
        */
        /*if (Session.getInstance().getuType() == UserType.GUARDIAN){
            Session.getInstance().setuIdVigilado("59c38a4b-2599-4961-9edd-6cae010ccb43");

        }else if (Session.getInstance().getuType() == UserType.GUARDED){
            Session.getInstance().setuIdVigilante("59c38a4b-2599-4961-9edd-6cae010ccb43");

        }


        startActivity(new Intent(AssociationActivity.this, WaitingActivity.class));
    }*/
    }
}
