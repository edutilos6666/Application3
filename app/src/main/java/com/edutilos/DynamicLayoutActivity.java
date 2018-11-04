package com.edutilos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.edutilos.model.Worker;

public class DynamicLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_layout);
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

        addDynamicComponents();
    }

    private GridLayout gridLayout;
    private TextView viewTitle, viewId, viewName, viewPassword, viewAge, viewWage, viewActive;
    private EditText editId, editName, editPassword, editAge, editWage, editActive;
    private Button btnMainActivity;
    private void addDynamicComponents() {
        gridLayout  = findViewById(R.id.content_dynamic_layout);
        gridLayout.setColumnCount(2);

        //title
        viewTitle = new TextView(this);
        viewTitle.setText(R.string.title_activity_dynamic_layout);
        viewTitle.setLayoutParams(generateParam(0, 0));
        gridLayout.addView(viewTitle);

        //id
        viewId = new TextView(this);
        viewId.setText(R.string.lblId);
        editId = new EditText(this);
        editId.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editId.setClickable(false);
        editId.setCursorVisible(false);
        editId.setFocusable(false);
        editId.setFocusableInTouchMode(false);
        editId.setHint("Insert id");
        viewId.setLayoutParams(generateParam(1, 0));
        editId.setLayoutParams(generateParam(1, 1));
        gridLayout.addView(viewId);
        gridLayout.addView(editId);


        //name
        viewName = new TextView(this);
        viewName.setText(R.string.lblName);
        editName = new EditText(this);
        editName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        editName.setClickable(false);
        editName.setCursorVisible(false);
        editName.setFocusable(false);
        editName.setFocusableInTouchMode(false);
        editName.setHint("Insert name");
        viewName.setLayoutParams(generateParam(2, 0));
        editName.setLayoutParams(generateParam(2, 1));
        gridLayout.addView(viewName);
        gridLayout.addView(editName);

        //password
        viewPassword = new TextView(this);
        viewPassword.setText(R.string.lblPassword);
        editPassword = new EditText(this);
        editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPassword.setClickable(false);
        editPassword.setCursorVisible(false);
        editPassword.setFocusable(false);
        editPassword.setFocusableInTouchMode(false);
        editPassword.setHint("Insert password");
        viewPassword.setLayoutParams(generateParam(3, 0));
        editPassword.setLayoutParams(generateParam(3, 1));
        gridLayout.addView(viewPassword);
        gridLayout.addView(editPassword);

        //age
        viewAge = new TextView(this);
        viewAge.setText(R.string.lblAge);
        editAge = new EditText(this);
        editAge.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editAge.setClickable(false);
        editAge.setCursorVisible(false);
        editAge.setFocusable(false);
        editAge.setFocusableInTouchMode(false);
        editAge.setHint("Insert age");
        viewAge.setLayoutParams(generateParam(4, 0));
        editAge.setLayoutParams(generateParam(4, 1));
        gridLayout.addView(viewAge);
        gridLayout.addView(editAge);

        //wage
        viewWage = new TextView(this);
        viewWage.setText(R.string.lblWage);
        editWage = new EditText(this);
        editWage.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editWage.setClickable(false);
        editWage.setCursorVisible(false);
        editWage.setFocusable(false);
        editWage.setFocusableInTouchMode(false);
        editWage.setHint("Insert wage");
        viewWage.setLayoutParams(generateParam(5, 0));
        editWage.setLayoutParams(generateParam(5, 1));
        gridLayout.addView(viewWage);
        gridLayout.addView(editWage);

        //active
        viewActive = new TextView(this);
        viewActive.setText(R.string.lblActive);
        editActive = new EditText(this);
        editActive.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        editActive.setClickable(false);
        editActive.setCursorVisible(false);
        editActive.setFocusable(false);
        editActive.setFocusableInTouchMode(false);
        editActive.setHint("Insert active");
        viewActive.setLayoutParams(generateParam(6, 0));
        editActive.setLayoutParams(generateParam(6, 1));
        gridLayout.addView(viewActive);
        gridLayout.addView(editActive);

        //button
        btnMainActivity = new Button(this);
        btnMainActivity.setText(R.string.btnMainActivity);
        btnMainActivity.setLayoutParams(generateParam(7, 0));
        gridLayout.addView(btnMainActivity);

        // registerEvents
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    private GridLayout.LayoutParams generateParam(int rowSpec, int colSpec) {
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param.leftMargin = 5;
        param.topMargin = 5;
        param.rightMargin = 5;
        param.bottomMargin = 5;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(rowSpec);
        param.columnSpec = GridLayout.spec(colSpec);
        return param;
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addValuesFromExtras();
    }

    private void addValuesFromExtras() {
        Worker worker4 = (Worker)getIntent().getSerializableExtra("worker4");
        editId.setText(worker4.getId()+"");
        editName.setText(worker4.getName());
        editPassword.setText(worker4.getPassword());
        editAge.setText(worker4.getAge()+"");
        editWage.setText(worker4.getWage()+"");
        editActive.setText(worker4.isActive()+"");
    }
}
