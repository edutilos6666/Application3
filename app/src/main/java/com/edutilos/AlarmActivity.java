package com.edutilos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edutilos.broadcast.ReceiverVibrator;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        receiverVibrator = new ReceiverVibrator();
        registerReceiver(receiverVibrator, new IntentFilter(CUSTOM_VIBRATOR_ACTION));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverVibrator);
    }


    private ReceiverVibrator receiverVibrator;
    private final String CUSTOM_VIBRATOR_ACTION = "com.edutilos.action.CUSTOM_VIBRATOR";
    private EditText editSeconds;
    private Button btnSetupTimer;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        editSeconds = findViewById(R.id.aa_editSeconds);
        btnSetupTimer = findViewById(R.id.aa_btnSetupTimer);
    }

    private void registerEvents() {
        btnSetupTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(CUSTOM_VIBRATOR_ACTION);
                int seconds = Integer.parseInt(editSeconds.getText().toString());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1234, i, PendingIntent.FLAG_ONE_SHOT );
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ (seconds*1000), pendingIntent);
                Toast.makeText(getBaseContext(), String.format("Alarm set in %d seconds.", seconds), Toast.LENGTH_LONG).show();
//                sendBroadcast(i);
            }
        });
    }
}
