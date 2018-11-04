package com.edutilos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edutilos.model.Worker;

public class TableLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
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



    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        addValuesFromExtras();
        registerEvents();
    }

    private EditText editId, editName, editPassword, editAge, editWage, editActive;
    private Button btnMainActivity, btnSendIntent1;

    private void initComponents() {
        editId = (EditText) findViewById(R.id.tla_editId);
        editName = (EditText) findViewById(R.id.tla_editName);
        editPassword = (EditText) findViewById(R.id.tla_editPassword);
        editAge = (EditText) findViewById(R.id.tla_editAge);
        editWage = (EditText) findViewById(R.id.tla_editWage);
        editActive = (EditText) findViewById(R.id.tla_editActive);
        btnMainActivity = (Button) findViewById(R.id.tla_btnMainActivity);
        btnSendIntent1 = findViewById(R.id.tla_btnSendIntent1);
    }

    private void addValuesFromExtras() {
        Worker worker2 = (Worker)getIntent().getSerializableExtra("worker2");
        editId.setText(worker2.getId()+"");
        editName.setText(worker2.getName());
        editPassword.setText(worker2.getPassword());
        editAge.setText(worker2.getAge()+"");
        editWage.setText(worker2.getWage()+"");
        editActive.setText(worker2.isActive()+"");
    }

    private void registerEvents() {
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableLayoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSendIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction("com.edutilos.CUSTOM_INTENT_1");
                sendBroadcast(i);
            }
        });
    }

}
