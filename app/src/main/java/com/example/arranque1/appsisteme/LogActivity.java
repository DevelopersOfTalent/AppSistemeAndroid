package com.example.arranque1.appsisteme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.arranque1.appsisteme.bbdd.DaoLog;

import java.util.List;

public class LogActivity extends AppCompatActivity {
    ListView lv;
    DaoLog daoLog;
    TextView headerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        lv = (ListView)findViewById(R.id.logList);
        headerText = (TextView)findViewById(R.id.headerText);
        if(Session.getInstance().getuType()==UserType.GUARDIAN){
            headerText.setText("Vigilante");
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilante));
        }else if(Session.getInstance().getuType()==UserType.GUARDED){
            headerText.setText("Vigilado");
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilado));
        }
        daoLog = new DaoLog(this);
        List<Log> logList = daoLog.getLogList();
        LogAdapter adapter = new LogAdapter(this, logList);
        lv.setAdapter(adapter);

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
                Class currentClass = null;
                UserType userType = Session.getInstance().getuType();
                if (userType == UserType.GUARDED){
                    currentClass = VigiladoMainActivity.class;
                }else if(userType == UserType.GUARDIAN){
                    currentClass = VigilanteMainActivity.class;
                }

                if(tabId.equals("PETICIÓN")){
                    startActivity(new Intent(LogActivity.this,currentClass));
                    finish();
                }

                if(tabId.equals("TELÉFONOS")){
                    startActivity(new Intent(LogActivity.this,ListContactActivity.class));
                    finish();
                }

                if(tabId.equals("LOG")){
                    startActivity(new Intent(LogActivity.this,LogActivity.class));
                    finish();
                }

                if(tabId.equals("SALIR")){
                    startActivity(new Intent(LogActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }
}
