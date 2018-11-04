package com.edutilos.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedAction = intent.getAction();
        Toast.makeText(context, String.format("Received intent %s", receivedAction), Toast.LENGTH_LONG)
        .show();
    }
}
