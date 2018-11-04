package com.edutilos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edutilos.model.Worker;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
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

    private EditText editId, editName, editPassword, editAge, editWage, editActive, editLog;
    private Button btnSubmit, btnClear, btnCancel, btnMainActivity;
    private final String NEWLINE = "\r\n";

    private void initComponents() {
        editId = (EditText) findViewById(R.id.lla_editId);
        editName = (EditText) findViewById(R.id.lla_editName);
        editPassword = (EditText) findViewById(R.id.lla_editPassword);
        editAge = (EditText) findViewById(R.id.lla_editAge);
        editWage = (EditText) findViewById(R.id.lla_editWage);
        editActive = (EditText) findViewById(R.id.lla_editActive);
        editLog = (EditText) findViewById(R.id.lla_editLog);
        btnSubmit = (Button) findViewById(R.id.lla_btnSubmit);
        btnClear = (Button) findViewById(R.id.lla_btnClear);
        btnCancel = (Button) findViewById(R.id.lla_btnCancel);
        btnMainActivity = (Button) findViewById(R.id.lla_btnMainActivity);
    }

    private void addValuesFromExtras() {
        Worker worker1 = (Worker)getIntent().getSerializableExtra("worker1");
        editId.setText(worker1.getId()+"");
        editName.setText(worker1.getName());
        editPassword.setText(worker1.getPassword());
        editAge.setText(worker1.getAge()+"");
        editWage.setText(worker1.getWage()+"");
        editActive.setText(worker1.isActive()+"");
    }

    private void registerEvents() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnSubmit", "btnSubmit.onClick");
                try {
                    long id = Long.parseLong(editId.getText().toString());
                    String name = editName.getText().toString();
                    String password = editPassword.getText().toString();
                    int age = Integer.parseInt(editAge.getText().toString());
                    double wage = Double.parseDouble(editWage.getText().toString());
                    boolean active = Boolean.parseBoolean(editActive.getText().toString());
                    StringBuilder sb = new StringBuilder();
                    sb.append("<<Worker Details>>").append(NEWLINE)
                            .append("id = ").append(id).append(NEWLINE)
                            .append("name = ").append(name).append(NEWLINE)
                            .append("password = ").append(password).append(NEWLINE)
                            .append("age = ").append(age).append(NEWLINE)
                            .append("wage = ").append(wage).append(NEWLINE)
                            .append("active = ").append(active).append(NEWLINE);
                    editLog.setText(sb.toString(), TextView.BufferType.NORMAL);
                } catch(Exception ex) {
                    editLog.setText(ex.getMessage(), TextView.BufferType.NORMAL);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editId.setText("", TextView.BufferType.NORMAL);
                editName.setText("", TextView.BufferType.NORMAL);
                editPassword.setText("", TextView.BufferType.NORMAL);
                editAge.setText("", TextView.BufferType.NORMAL);
                editWage.setText("", TextView.BufferType.NORMAL);
                editActive.setText("", TextView.BufferType.NORMAL);
                editLog.setText("", TextView.BufferType.NORMAL);
            }
        });
        btnCancel.setClickable(false);

        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LinearLayoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
