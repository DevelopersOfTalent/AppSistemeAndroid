package com.example.arranque1.appsisteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;

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
        if (stateRequest != null) {
            stateRequest.setOnClickListener(listener);
        }

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
                    startActivity(new Intent(VigilanteMainActivity.this,VigilanteMainActivity.class));
                }

                if(tabId.equals("TELÉFONOS")){
                    startActivity(new Intent(VigilanteMainActivity.this,ListContactActivity.class));
                }

                if(tabId.equals("LOG")){
                    startActivity(new Intent(VigilanteMainActivity.this,LogActivity.class));
                }

                if(tabId.equals("SALIR")){
                    startActivity(new Intent(VigilanteMainActivity.this,LoginActivity.class));
                }

            }
        });
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
