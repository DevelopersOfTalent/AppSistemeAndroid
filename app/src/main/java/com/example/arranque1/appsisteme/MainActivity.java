package com.example.arranque1.appsisteme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.arranque1.appsisteme.bbdd.DaoContacts;
import com.onesignal.OneSignal;


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText etPhone,etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);


        OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
                        //OneSignal.sendTag("key", "value");
                        try {
                            String casa = additionalData.get("key").toString();
                            Log.d("hola",casa);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }})
                .init();



    }



    public void iniciar(View v) {startActivity(new Intent(this, AddActivity.class));}
    public void ver (View v) {startActivity(new Intent(this,ListContactActivity.class));}

    public void notificar (View v) {
        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("debug", tags.toString());

            }
        });
    }

}
