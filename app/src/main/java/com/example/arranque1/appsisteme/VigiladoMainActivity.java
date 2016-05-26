package com.example.arranque1.appsisteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VigiladoMainActivity extends AppCompatActivity {

    ImageView good, bad, call_me;
    String state = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigilado_main);

        good = (ImageView)findViewById(R.id.good);
        bad = (ImageView)findViewById(R.id.bad);
        call_me = (ImageView)findViewById(R.id.call_me);

        good.setOnClickListener(goodListener);
        bad.setOnClickListener(badListener);
        call_me.setOnClickListener(call_meListener);
    }

    View.OnClickListener goodListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //código
        }
    };

    View.OnClickListener badListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //código
        }
    };

    View.OnClickListener call_meListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //código
        }
    };

}
