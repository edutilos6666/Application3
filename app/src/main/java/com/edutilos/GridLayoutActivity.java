package com.edutilos;

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

public class GridLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
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
    private Button btnMainActivity;

    private void initComponents() {
        editId = findViewById(R.id.gla_editId);
        editName = findViewById(R.id.gla_editName);
        editPassword = findViewById(R.id.gla_editPassword);
        editAge = findViewById(R.id.gla_editAge);
        editWage = findViewById(R.id.gla_editWage);
        editActive = findViewById(R.id.gla_editActive);
        btnMainActivity = findViewById(R.id.gla_btnMainActivity);
    }

    private void addValuesFromExtras() {
        Worker worker3 = (Worker)getIntent().getSerializableExtra("worker3");
        editId.setText(worker3.getId()+"");
        editName.setText(worker3.getName());
        editPassword.setText(worker3.getPassword());
        editAge.setText(worker3.getAge()+"");
        editWage.setText(worker3.getWage()+"");
        editActive.setText(worker3.isActive()+"");
    }

    private void registerEvents() {
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridLayoutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
