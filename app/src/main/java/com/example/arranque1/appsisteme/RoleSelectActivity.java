package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoleSelectActivity extends AppCompatActivity {

    Button vigilanteButton, vigiladoButton;
    private static String MENSAJE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_select);

        vigilanteButton = (Button)findViewById(R.id.vigilanteButton);
        vigiladoButton = (Button)findViewById(R.id.vigiladoButton);
        vigilanteButton.setOnClickListener(listenerVigilante);
        vigiladoButton.setOnClickListener(listenerVigilado);
    }

    View.OnClickListener listenerVigilante = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Session.getInstance().setuType(UserType.GUARDIAN);
            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                @Override
                public void idsAvailable(String userId, String registrationId) {
                    Toast.makeText(RoleSelectActivity.this, userId, Toast.LENGTH_SHORT).show();
                    Log.d("debug", "userID:" + userId);
                    Session.getInstance().setuIdVigilante(userId);
                    if (registrationId != null)
                        Log.d("debug", "registrationId:" + registrationId);
                }
            });
            perfil_vigilante();
            startActivity(new Intent(RoleSelectActivity.this, LoginActivity.class));
        }
    };

    View.OnClickListener listenerVigilado = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Session.getInstance().setuType(UserType.GUARDED);
            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                @Override
                public void idsAvailable(String userId, String registrationId) {
                    Toast.makeText(RoleSelectActivity.this, userId, Toast.LENGTH_SHORT).show();
                    Log.d("debug", "userID:" + userId);
                    Session.getInstance().setuIdVigilado(userId);
                    if (registrationId != null)
                        Log.d("debug", "registrationId:" + registrationId);
                }
            });
            perfil_vigilado();
            startActivity(new Intent(RoleSelectActivity.this, LoginActivity.class));
        }
    };
    public void perfil_vigilado(){
        SharedPreferences sp = this.getSharedPreferences(SplashScreenActivity.ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UserType",UserType.GUARDED.toString());
        editor.commit();
        finish();
    }
    public void perfil_vigilante(){
        SharedPreferences sp = this.getSharedPreferences(SplashScreenActivity.ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UserType",UserType.GUARDIAN.toString());
        editor.commit();
        finish();
    }
}
