package com.edutilos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class CustomService1 extends Service {
    public CustomService1() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, CustomService1.class.getCanonicalName()+" was started.",
                Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, String.format("%s was destroyed", getClass().getCanonicalName()),
                Toast.LENGTH_LONG).show();
    }
}
