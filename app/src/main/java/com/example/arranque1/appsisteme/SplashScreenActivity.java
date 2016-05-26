package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private static String MENSAJE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onResume() {
        super.onResume();

        OneSignal.enableInAppAlertNotification(true);
        OneSignal.enableNotificationsWhenActive(true);
        OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
                        //OneSignal.sendTag("key", "value");
                        //AlertDialog.Builder aD = new AlertDialog.Builder(RoleSelectActivity.this);
                        Toast.makeText(SplashScreenActivity.this, message.toString(), Toast.LENGTH_LONG).show();
                        try {
                            Object n = additionalData.get("actionSelected");
                            Log.d("SELECCIONADO", n.toString());
                            Toast.makeText(SplashScreenActivity.this, n.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(SplashScreenActivity.this, VigiladoMainActivity.class));
                    }
                })
                .init();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, RoleSelectActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1500);
    }
}
