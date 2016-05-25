package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestActivity extends AppCompatActivity {

    private static final String MENSAJE = "Hola, ¿qué tal estás? :)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }
    public void request(View v) {
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                            + MENSAJE
                            + "'}, 'include_player_ids': ['"
                            + Session2.getInstance().getuIdVigilado()
                            + "']}"),
                    new OneSignal.PostNotificationResponseHandler() {
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
