package com.example.arranque1.appsisteme;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.arranque1.appsisteme.bbdd.DaoContacts;
import com.onesignal.OneSignal;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    public static String MENSAJE = "";
    EditText etPhone,etName;
    Button bien, mal, llamame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        bien = (Button)findViewById(R.id.bien);
        bien.setOnClickListener((View.OnClickListener) MainActivity.this);
        mal = (Button)findViewById(R.id.mal);
        mal.setOnClickListener((View.OnClickListener) MainActivity.this);
        llamame = (Button)findViewById(R.id.llamame);
        llamame.setOnClickListener((View.OnClickListener) MainActivity.this);
        OneSignal.enableInAppAlertNotification(true);
        OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
                        //OneSignal.sendTag("key", "value");
                        JSONArray json_array = additionalData.optJSONArray("actionButtons");
                        try {
                            Object n = additionalData.get("actionSelected");
                            Log.d("SELECCIONADO", n.toString());
                            Toast.makeText(MainActivity.this, n.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .init();
    }
    public void iniciar(View v) {startActivity(new Intent(this, AddActivity.class));}
    public void ver (View v) {startActivity(new Intent(this, ListContactActivity.class));}
    public void notificar (View v) {
        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("debug", tags.toString());
            }
        });
    }
    public void onClick (View v)
    {
        if (v.getId() == R.id.bien){
            MENSAJE = "BIEN";
        }
        if (v.getId() == R.id.mal){
            MENSAJE = "MAL";
        }
        if (v.getId() == R.id.llamame){
            MENSAJE = "LLAMAME";
        }

        try {
            String userId = "8f0d8d21-e754-4794-94fb-ee21ee316d8d";
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + MENSAJE + "'}, 'include_player_ids': ['" + userId + "']}"), new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("Result", "Exito");
                }
                @Override
                public void onFailure(JSONObject response) {
                    Log.d("Result", "Fracaso");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getNotificacion (View v){
        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("debug", tags.toString());
            }
        });
    }
    public void bien (View v){
        MENSAJE = "BIEN";
        try {
            String userId = "8f0d8d21-e754-4794-94fb-ee21ee316d8d";
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + MENSAJE + "'}, 'include_player_ids': ['" + userId + "']}"), new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("Result", "Exito");
                }
                @Override
                public void onFailure(JSONObject response) {
                    Log.d("Result", "Fracaso");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void mal (View v){
        MENSAJE = "MAL";
        try {
            String userId = "8f0d8d21-e754-4794-94fb-ee21ee316d8d";
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + MENSAJE + "'}, 'include_player_ids': ['" + userId + "']}"), new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("Result", "Exito");
                }
                @Override
                public void onFailure(JSONObject response) {
                    Log.d("Result", "Fracaso");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void llamame (View v){
        MENSAJE = "LLÁMAME";
        try {
            String userId = "8f0d8d21-e754-4794-94fb-ee21ee316d8d";
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'" + MENSAJE + "'}, 'include_player_ids': ['" + userId + "']}"), new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("Result", "Exito");
                }
                @Override
                public void onFailure(JSONObject response) {
                    Log.d("Result", "Fracaso");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
