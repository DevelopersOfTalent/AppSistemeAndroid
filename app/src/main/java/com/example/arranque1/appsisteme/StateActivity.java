package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;


public class StateActivity extends AppCompatActivity {

    private static String MENSAJE = "";
    Button bGood, bBad, bCallme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        bGood = (Button) findViewById(R.id.bGood);
        bBad = (Button) findViewById(R.id.bBad);
        bCallme = (Button) findViewById(R.id.bCallme);

        bGood.setOnClickListener(myhandler);
        bBad.setOnClickListener(myhandler);
        bCallme.setOnClickListener(myhandler);

        String n = Session.getInstance().getuIdVigilante().toString();
        Toast.makeText(StateActivity.this, n, Toast.LENGTH_SHORT).show();

    }

    /*public void good (View v){
        MENSAJE = "BIEN";
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        OneSignal.sendTag("BIEN", ts);
        try {
            OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                    + MENSAJE
                    + "'}, 'include_player_ids': ['"
                    + Session1.getInstance().getuIdVigilante()
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
                            + Session1.getInstance().getuIdVigilante()
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
                            + Session1.getInstance().getuIdVigilante()
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
    }*/



    View.OnClickListener myhandler = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bGood:
                    MENSAJE = "BIEN";
                    break;
                case R.id.bBad:
                    MENSAJE = "MAL";
                    break;
                case R.id.bCallme:
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
