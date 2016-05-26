package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class WaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(Session.getInstance().getuType() == UserType.GUARDIAN){
            //código
            startActivity(new Intent(WaitingActivity.this, VigilanteMainActivity.class));
        }
        if(Session.getInstance().getuType() == UserType.GUARDED){
            //código
            startActivity(new Intent(WaitingActivity.this, VigiladoMainActivity.class));
        }
    }
}
