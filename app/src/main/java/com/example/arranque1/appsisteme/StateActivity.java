package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StateActivity extends AppCompatActivity {

    private static String MENSAJE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
    }

    public void good (View v){
        MENSAJE = "BIEN";
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        OneSignal.sendTag("BIEN", ts);
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                    + MENSAJE
                    + "'}, 'include_player_ids': ['"
                    + Session.getInstance().getuIdVigilante()
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

    public void bad (View v){
        MENSAJE = "MAL";
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                            + MENSAJE
                            + "'}, 'include_player_ids': ['"
                            + Session.getInstance().getuIdVigilante()
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
    public void callme (View v){
        MENSAJE = "LLÁMAME";
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                            + MENSAJE
                            + "'}, 'include_player_ids': ['"
                            + Session.getInstance().getuIdVigilante()
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
