package com.edutilos;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Arrays;

public class UIContolsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uicontols);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initComponents();
        registerEvents();
    }

    private AutoCompleteTextView textViewAutoComplete;
    private CheckBox cbListening , cbSpeaking;
    private ToggleButton tbWriting;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale, rbChild;
    private ProgressBar progressBar;
    private Button btnStartProgress, btnStopProgress;
    private Thread t;
    private Spinner spinner, spinner2;
    private DatePicker datePicker;
    private TimePicker timePicker;

    private void initComponents() {
        textViewAutoComplete = findViewById(R.id.uica_textViewAutoComplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.select_dialog_item, new String[]{
                        "Baku", "Bischkek", "Bileceri",
                "Ankara", "Astana", "Almati", "Andorra"
        });
        textViewAutoComplete.setThreshold(1);
        textViewAutoComplete.setAdapter(adapter);

        cbListening = findViewById(R.id.uica_cbListening);
        cbSpeaking = findViewById(R.id.uica_cbSpeaking);
        tbWriting = findViewById(R.id.uica_tbWriting);
        rgGender = findViewById(R.id.uica_rgGender);
        rbMale = findViewById(R.id.uica_rbMale);
        rbFemale = findViewById(R.id.uica_rbFemale);
        rbChild = findViewById(R.id.uica_rbChild);
        progressBar = findViewById(R.id.uica_progressBar);
        btnStartProgress = findViewById(R.id.uica_btnStartProgress);
        btnStopProgress = findViewById(R.id.uica_btnStopProgress);
        initThread();
        spinner = findViewById(R.id.uica_spinner);
        spinner2 = findViewById(R.id.uica_spinner2);
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item,
                new String[] {
                        "Baku", "Istanbul", "Ankara", "Berlin", "Bochum", "Essen"
                });
        adapterSpinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterSpinner2);
        datePicker = findViewById(R.id.uica_datePicker);
        timePicker = findViewById(R.id.uica_timePicker);
    }

    private void initThread() {
        t = new Thread() {
            @Override
            public void run() {
                while(progressBar.getProgress() <= progressBar.getMax()) {
                    try {
                        Thread.sleep(100);
                        progressBar.setProgress(progressBar.getProgress()+10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registerEvents() {
        cbListening.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getBaseContext(), isChecked+"", Toast.LENGTH_SHORT).show();
            }
        });
        cbSpeaking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getBaseContext(), Boolean.toString(isChecked), Toast.LENGTH_LONG)
                        .show();
            }
        });

        tbWriting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getBaseContext(), Boolean.toString(isChecked), Toast.LENGTH_LONG)
                        .show();
            }
        });


        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String text = findCheckedRadioButtonText();
                if(text == null) return;
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG)
                        .show();
            }
        });

        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.start();
                btnStartProgress.setEnabled(false);
                btnStopProgress.setEnabled(true);
            }
        });

        btnStopProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t.isAlive() && !t.isInterrupted()) {
                    t.interrupt();
                    btnStartProgress.setEnabled(true);
                    btnStopProgress.setEnabled(false);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "Nothing was selected", Toast.LENGTH_SHORT).show();
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "Nothing was selected", Toast.LENGTH_SHORT).show();
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuilder sb = new StringBuilder();
                sb.append(dayOfMonth).append("/")
                        .append(monthOfYear).append("/")
                        .append(year);
                Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                StringBuilder sb = new StringBuilder();
                sb.append(hourOfDay).append(":").append(minute);
                Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String findCheckedRadioButtonText() {
        if(rbMale.isChecked()) {
            return rbMale.getText().toString();
        } else if(rbFemale.isChecked()) {
            return rbFemale.getText().toString();
        } else if(rbChild.isChecked()) {
            return rbChild.getText().toString();
        }
        return null;
    }
}
