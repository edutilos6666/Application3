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

import com.edutilos.broadcast.ReceiverWorker;
import com.edutilos.model.Worker;

public class ReceiverWorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_worker);
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

        receiverWorker = new ReceiverWorker();
        registerReceiver(receiverWorker, new IntentFilter(RECEIVER_WORKER));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverWorker);
    }

    private ReceiverWorker receiverWorker;
    private final String RECEIVER_WORKER = "com.edutilos.action.RECEIVER_WORKER";
    private EditText editSeconds, editId, editName, editPassword, editAge, editWage, editActive;
    private Button btnSetupTimer, btnMainActivity;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();
    }

    private void initComponents() {
        editSeconds = findViewById(R.id.rwa_editSeconds);
        editId = findViewById(R.id.rwa_editId);
        editName = findViewById(R.id.rwa_editName);
        editPassword = findViewById(R.id.rwa_editPassword);
        editAge = findViewById(R.id.rwa_editAge);
        editWage = findViewById(R.id.rwa_editWage);
        editActive = findViewById(R.id.rwa_editActive);
        btnSetupTimer = findViewById(R.id.rwa_btnSetupTimer);
        btnMainActivity = findViewById(R.id.rwa_btnMainActivity);
    }

    private void registerEvents() {
        btnSetupTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent();
                    i.setAction(RECEIVER_WORKER);
                    Integer seconds = Integer.parseInt(editSeconds.getText().toString());
                    i.putExtra("newline", "\r\n");
                    i.putExtra("title", "<<Worker Properties>>");
                    long id = Long.parseLong(editId.getText().toString());
                    String name = editName.getText().toString();
                    String password = editPassword.getText().toString();
                    int age = Integer.parseInt(editAge.getText().toString());
                    double wage = Double.parseDouble(editWage.getText().toString());
                    boolean active = Boolean.parseBoolean(editActive.getText().toString());
                    i.putExtra("worker", new Worker(id, name, password, age, wage, active));
//                    PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 1234, i, PendingIntent.FLAG_ONE_SHOT);
//                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ (seconds*1000), pi);
//                    Toast.makeText(getBaseContext(), String.format("Alarm was set in %d seconds",seconds), Toast.LENGTH_LONG).show();
                    Thread.sleep(seconds*1000);
                    sendBroadcast(i);
                } catch(Exception ex) {
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG);
                }
            }
        });

        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
