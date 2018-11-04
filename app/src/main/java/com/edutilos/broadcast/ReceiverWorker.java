package com.edutilos.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.edutilos.model.Worker;

public class ReceiverWorker extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String newline = intent.getStringExtra("newline");
        Worker worker = (Worker)intent.getSerializableExtra("worker");
        StringBuilder sb = new StringBuilder();
        if(worker == null)
            sb.append(title).append(newline);
        else
            sb.append(title).append(newline)
                .append("<<Worker>>").append(newline)
                .append("id = ").append(worker.getId()).append(newline)
                .append("name = ").append(worker.getName()).append(newline)
                .append("password = ").append(worker.getPassword()).append(newline)
                .append("age = ").append(worker.getAge()).append(newline)
                .append("wage = ").append(worker.getWage()).append(newline)
                .append("active = ").append(worker.isActive()).append(newline);
        Toast.makeText(context , sb.toString(), Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(VibrationEffect.createWaveform(new long[]{1000, 2000}, 2));
        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
    }
}
