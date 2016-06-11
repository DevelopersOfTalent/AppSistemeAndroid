package com.example.arranque1.appsisteme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arranque1.appsisteme.bbdd.DaoLog;

import java.util.List;

public class LogActivity extends AppCompatActivity {
    ListView lv;
    ImageButton deleteButton;
    DaoLog daoLog;
    TextView headerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        lv = (ListView)findViewById(R.id.logList);
        headerText = (TextView)findViewById(R.id.headerText);
        UserType userType = Session.getInstance().getuType();
        if(userType==UserType.GUARDIAN){
            headerText.setText("VIGILANTE");
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilante));
        }else if(Session.getInstance().getuType()==UserType.GUARDED){
            headerText.setText("VIGILADO");
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilado));
        }

        deleteButton = (ImageButton)findViewById(R.id.deleteIcon);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(LogActivity.this);
                deleteDialog.setTitle("¿Seguro que deseas borrar?");
                deleteDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoLog.deleteLog(LogActivity.this);
                        startActivity(new Intent(LogActivity.this, LogActivity.class));
                        finish();
                    }
                });
                deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDialog.setCancelable(true);
                    }
                });
                deleteDialog.show();
            }
        });

        daoLog = new DaoLog(this);
        List<Log> logList = daoLog.getLogList();
        LogAdapter adapter = new LogAdapter(this, logList);
        lv.setAdapter(adapter);

        final TabHost host = (TabHost)findViewById(R.id.tabHost);
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

        host.setCurrentTab(2);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if (tabId.equals("PETICIÓN")) {
                    if(Session.getInstance().getuType()==UserType.GUARDED){
                        startActivity(new Intent(LogActivity.this, VigiladoMainActivity.class));
                    }else if(Session.getInstance().getuType()==UserType.GUARDIAN){
                        startActivity(new Intent(LogActivity.this, VigilanteMainActivity.class));
                    }
                    finish();
                }

                if(tabId.equals("TELÉFONOS")){
                    startActivity(new Intent(LogActivity.this,ListContactActivity.class));
                    finish();
                }

                if(tabId.equals("SALIR")){
                    startActivity(new Intent(LogActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Session.getInstance().getuType() == UserType.GUARDIAN) {
            String perfil = "VIGILANTE";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilante));
        } else if (Session.getInstance().getuType() == UserType.GUARDED) {
            String perfil = "VIGILADO";
            headerText.setText(perfil);
            headerText.setBackgroundColor(getResources().getColor(R.color.colorButtonVigilado));
        }
    }

}


