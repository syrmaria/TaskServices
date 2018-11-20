package com.syrova_ma.taskservices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by SBT-Syrova-MA on 19.11.2018.
 */

public class SomeService extends Service {
    public static final String TAG = "MySomeService";

    private final IBinder mBinder = new SomeBinder();

    public class SomeBinder extends Binder {
        SomeService getService() {
            return SomeService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Started");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Got intent " + intent);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Binded with intent " + intent);
        return mBinder;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }

    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Unbinded with intent " + intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroyed");
    }

    public int getNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    public static Intent newIntent(Context c) {
        return new Intent(c, SomeService.class);
    }

}
