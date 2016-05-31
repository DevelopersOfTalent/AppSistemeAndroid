package com.example.arranque1.appsisteme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.onesignal.OneSignal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText etPhone, etName;
    private static String MENSAJE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Session.getInstance().setuIdVigilante("59c38a4b-2599-4961-9edd-6cae010ccb43");
        Session.getInstance().setuIdVigilado("5a8439c6-52a3-4677-98d2-744dc05d31cd");

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        User ut = new User();
        ut.setId("59c38a4b-2599-4961-9edd-6cae010ccb43");
        //Session.getInstance().setuType(UserType.GUARDED);

        OneSignal.enableInAppAlertNotification(true);
        OneSignal.enableNotificationsWhenActive(true);
        OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {

                    @Override
                    public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
                        //OneSignal.sendTag("key", "value");
                        JSONArray json_array = additionalData.optJSONArray("actionButtons");

                        //AlertDialog.Builder aD = new AlertDialog.Builder(MainActivity.this);
                        Toast.makeText(MainActivity.this, message.toString(), Toast.LENGTH_LONG).show();
                        final AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                        alert.setTitle("Hola");
                        alert.setMessage("¿Cómo estás?");
                        alert.setButton(DialogInterface.BUTTON_POSITIVE, "BIEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "has pulsado bien", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(MainActivity.this, "has pulsado mal", Toast.LENGTH_LONG).show();
                                alert.cancel();
                            }
                        });
                        alert.setButton(DialogInterface.BUTTON_NEUTRAL, "LLÁMAME", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "has pulsado llamame", Toast.LENGTH_LONG).show();
                                alert.cancel();
                            }
                        });
                        alert.show();
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

    public void login(View v) {
        startActivity(new Intent(this, ContactActivity.class));
    }

    public void notificar(View v) {
        OneSignal.sendTag("1", "hola");
        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("debug", tags.toString());
                OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
                    @Override
                    public void idsAvailable(String userId, String registrationId) {
                        Log.d("debug", "User:" + userId);
                        if (registrationId != null)
                            Log.d("debug", "registrationId:" + registrationId);
                    }
                });
            }
        });
    }

    /*public void guardian(View v) {
        Intent intent = new Intent(MainActivity.this, MenuVigilanteActivity.class);
        intent.putExtra("parametro", "guardian");
        startActivity(intent);
    }*/

    /*public void guarded(View v) {
        Intent intent = new Intent(MainActivity.this, MenuVigiladoActivity.class);
        intent.putExtra("parametro", "guarded");
        startActivity(intent);
    }*/
}
