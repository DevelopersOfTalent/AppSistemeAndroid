package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VigiladoMainActivity extends AppCompatActivity {
    ImageView good, bad, call_me;
    private static String MENSAJE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilado_main);

        good = (ImageView)findViewById(R.id.good);
        bad = (ImageView)findViewById(R.id.bad);
        call_me = (ImageView)findViewById(R.id.call_me);

        good.setOnClickListener(myhandler);
        bad.setOnClickListener(myhandler);
        call_me.setOnClickListener(myhandler);

        OneSignal.enableInAppAlertNotification(true);
        OneSignal.enableNotificationsWhenActive(true);
        OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {

                    @Override
                    public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {

                        JSONArray json_array = additionalData.optJSONArray("actionButtons");

                        //AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
                        Toast.makeText(VigiladoMainActivity.this, message.toString(), Toast.LENGTH_LONG).show();
                        final AlertDialog alert = new AlertDialog.Builder(VigiladoMainActivity.this).create();
                        alert.setTitle("Hola");
                        alert.setMessage("¿Cómo estás?");
                        alert.setButton(DialogInterface.BUTTON_POSITIVE, "BIEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(VigiladoMainActivity.this, "has pulsado bien", Toast.LENGTH_LONG).show();
                                MENSAJE = "BIEN";
                                Long tsLong = System.currentTimeMillis() / 1000;
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
                                alert.cancel();
                            }
                        });
                        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "MAL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(VigiladoMainActivity.this, "has pulsado mal", Toast.LENGTH_LONG).show();
                                alert.cancel();
                            }
                        });
                        alert.setButton(DialogInterface.BUTTON_NEUTRAL, "LLÁMAME", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(VigiladoMainActivity.this, "has pulsado llamame", Toast.LENGTH_LONG).show();
                                alert.cancel();
                            }
                        });
                        alert.show();
                        try {
                            Object n = additionalData.get("actionSelected");
                            Log.d("SELECCIONADO", n.toString());
                            Toast.makeText(VigiladoMainActivity.this, n.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("PETICIÓN");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", getDrawable(R.drawable.icono_peticion));
        host.addTab(spec);

        spec = host.newTabSpec("TELÉFONOS");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getDrawable(R.drawable.icono_telefonos));
        host.addTab(spec);

        spec = host.newTabSpec("LOG");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", getDrawable(R.drawable.icono_menu));
        host.addTab(spec);

        spec = host.newTabSpec("SALIR");
        spec.setContent(R.id.tab4);
        spec.setIndicator("", getDrawable(R.drawable.icono_salir));
        host.addTab(spec);

        host.setCurrentTab(0);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if(tabId.equals("PETICIÓN")){
                    startActivity(new Intent(VigiladoMainActivity.this,VigiladoMainActivity.class));
                }

                if(tabId.equals("TELÉFONOS")){
                    startActivity(new Intent(VigiladoMainActivity.this,ListContactActivity.class));
                }

                if(tabId.equals("LOG")){
                    startActivity(new Intent(VigiladoMainActivity.this,LogActivity.class));
                }

                if(tabId.equals("SALIR")){
                    startActivity(new Intent(VigiladoMainActivity.this,LoginActivity.class));
                }

            }
        });
    }


    View.OnClickListener myhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.good:
                    MENSAJE = "BIEN";
                    break;
                case R.id.bad:
                    MENSAJE = "MAL";
                    break;
                case R.id.call_me:
                    MENSAJE = "LLÁMAME";
                    break;
            }
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
    };



}
