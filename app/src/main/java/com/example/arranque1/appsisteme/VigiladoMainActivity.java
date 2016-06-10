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

import com.example.arranque1.appsisteme.bbdd.DaoLog;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VigiladoMainActivity extends AppCompatActivity {
    ImageView good, bad, call_me;
    DaoLog daoLog = new DaoLog(this);
    private static String MENSAJE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilado_main);

        good = (ImageView) findViewById(R.id.good);
        bad = (ImageView) findViewById(R.id.bad);
        call_me = (ImageView) findViewById(R.id.call_me);

        good.setOnClickListener(myhandler);
        bad.setOnClickListener(myhandler);
        call_me.setOnClickListener(myhandler);

        OneSignal.enableInAppAlertNotification(true);
        OneSignal.enableNotificationsWhenActive(true);

        final TabHost host = (TabHost) findViewById(R.id.tabHost);
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

                if (tabId.equals("TELÉFONOS")) {
                    startActivity(new Intent(VigiladoMainActivity.this, ListContactActivity.class));
                }

                if (tabId.equals("LOG")) {
                    startActivity(new Intent(VigiladoMainActivity.this, LogActivity.class));
                }

                if (tabId.equals("SALIR")) {
                    startActivity(new Intent(VigiladoMainActivity.this, LoginActivity.class));
                }
            }
        });
    }

    //Dentro de este onClickListener irá el SendTag o el JSONObject con el string de fecha (dateTime) y el de estado (MENSAJE).
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

            Calendar date = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            String dateTime = String.valueOf(sdf.format(date.getTime()));
            com.example.arranque1.appsisteme.Log log = new com.example.arranque1.appsisteme.Log(MENSAJE, dateTime);
            daoLog.addLog(log);

            try {
                OneSignal.postNotification(new JSONObject("{'contents': {'en':'"
                        + MENSAJE
                        + "'}, 'include_player_ids': ['"+
                        Session.getInstance().getuIdVigilante()
                        +"'],'data':{'state':'"+MENSAJE+"','date':'"+dateTime+"'}}"),
                        new OneSignal.PostNotificationResponseHandler() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                Log.d("Result", "Exito");
                            }

                            @Override
                            public void onFailure(JSONObject response) {
                                Log.d("Result", response.toString());
                            }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


}
