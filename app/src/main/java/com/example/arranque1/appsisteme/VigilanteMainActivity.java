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
        spec.setIndicator("PETICIÓN", getDrawable(R.drawable.icono_peticion));
        host.addTab(spec);

        spec = host.newTabSpec("TELÉFONOS");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TELÉFONOS", getDrawable(R.drawable.icono_telefonos));
        host.addTab(spec);

        spec = host.newTabSpec("LOG");
        spec.setContent(R.id.tab3);
        spec.setIndicator("LOG", getDrawable(R.drawable.icono_menu));
        host.addTab(spec);

        spec = host.newTabSpec("SALIR");
        spec.setContent(R.id.tab4);
        spec.setIndicator("SALIR", getDrawable(R.drawable.icono_salir));
        host.addTab(spec);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.tab1){
            startActivity(new Intent(this,VigilanteMainActivity.class));
        }

        if(id == R.id.tab2){
            startActivity(new Intent(this,ListContactActivity.class));
        }

        if(id == R.id.tab3){
            startActivity(new Intent(this,LogActivity.class));
        }

        if(id == R.id.tab4){
            startActivity(new Intent(this,LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
