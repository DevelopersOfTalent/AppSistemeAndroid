package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class VigilanteMainActivity extends AppCompatActivity {

    private static final String MENSAJE = "¡Hola! ¿Qué tal estás? :)";


    ImageView stateRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilante_main);

        stateRequest = (ImageView)findViewById(R.id.stateRequest);
        stateRequest.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                                + MENSAJE
                                + "'}, 'include_player_ids': ['"
                                + Session.getInstance().getuIdVigilado()
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
