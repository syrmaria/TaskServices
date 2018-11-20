package com.syrova_ma.taskservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by SBT-Syrova-MA on 19.11.2018.
 */

public class BindServiceActivity extends AppCompatActivity {
    public static final String TAG = "MyBindServiceActivity";
    TextView txt;

    private SomeService mBoundService;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((SomeService.SomeBinder) service).getService();
            Log.d(TAG, "Service connected.");
            showServiceResult();
        }
        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
            Log.d(TAG, "Service disconnected.");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        Button bindButton = findViewById(R.id.bind_service_button);
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBindService();
            }
        });
        Button unbindButton = findViewById(R.id.unbind_service_button);
        unbindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUnbindService();
            }
        });
        txt = findViewById(R.id.service_result_text);
    }

    void doBindService() {
        if (bindService(new Intent(BindServiceActivity.this, SomeService.class),
                mConnection, Context.BIND_NOT_FOREGROUND)) {
            Log.d(TAG, "Called to bind service");
        } else {
            Log.e(TAG, "Service doesn't exist");
        }
    }

    private void showServiceResult() {
        int i = mBoundService.getNumber();
        txt.setText(getString(R.string.service_result, i));
    }

    private void doUnbindService() {
        if (mBoundService != null) {
            unbindService(mConnection);
            mBoundService = null;
            Log.d(TAG, "Unbind service");
        } else {
            Log.d(TAG, "No need to unbind service");
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "OnDestroyCalled");
        doUnbindService();
        super.onDestroy();
    }
}
