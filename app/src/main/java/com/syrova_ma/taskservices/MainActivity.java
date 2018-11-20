package com.syrova_ma.taskservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyMainActivity";
    Button serviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceButton = findViewById(R.id.start_service_button);
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartService();
            }
        });
        Button activityButton = findViewById(R.id.activity_button);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BindServiceActivity.class);
                startActivity(i);
                Log.d(TAG, "Started second activity");
            }
        });
    }

    private void doStartService() {
        startService(SomeService.newIntent(this));
        Log.d(TAG, "Started service");
    }

    @Override
    protected void onDestroy() {
        stopService(SomeService.newIntent(this));
        Log.d(TAG, "OnDestroy - stopped service");
        super.onDestroy();
    }
}
