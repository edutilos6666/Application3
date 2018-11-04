package com.edutilos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentWorkerForm_PM extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_worker_form_pm, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();
        registerEvents();
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/


    private EditText editId, editName, editPassword, editAge, editWage, editActive;
    private Button btnSubmit, btnClear, btnMainActivity;
    private final String NEWLINE = "\r\n";

    private void initComponents() {
        editId = (EditText) getView().findViewById(R.id.worker_pm_editId);
        editName = (EditText) getView().findViewById(R.id.worker_pm_editName);
        editPassword = (EditText) getView().findViewById(R.id.worker_pm_editPassword);
      /*  editAge = (EditText) getView().findViewById(R.id.worker_pm_editAge);
        editWage = (EditText) getView().findViewById(R.id.worker_pm_editWage);
        editActive = (EditText) getView().findViewById(R.id.worker_pm_editActive);*/
        btnSubmit = (Button) getView().findViewById(R.id.worker_pm_btnSubmit);
        btnClear = (Button) getView().findViewById(R.id.worker_pm_btnClear);
        btnMainActivity = (Button) getView().findViewById(R.id.worker_pm_btnMainActivity);
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
                  /*  int age = Integer.parseInt(editAge.getText().toString());
                    double wage = Double.parseDouble(editWage.getText().toString());
                    boolean active = Boolean.parseBoolean(editActive.getText().toString());*/
                    StringBuilder sb = new StringBuilder();
                    sb.append("<<Worker Details>>").append(NEWLINE)
                            .append("id = ").append(id).append(NEWLINE)
                            .append("name = ").append(name).append(NEWLINE)
                            .append("password = ").append(password).append(NEWLINE);
                            /*.append("age = ").append(age).append(NEWLINE)
                            .append("wage = ").append(wage).append(NEWLINE)
                            .append("active = ").append(active).append(NEWLINE);*/
                    Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG)
                            .show();
                } catch(Exception ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editId.setText("", TextView.BufferType.NORMAL);
                editName.setText("", TextView.BufferType.NORMAL);
                editPassword.setText("", TextView.BufferType.NORMAL);
              /*  editAge.setText("", TextView.BufferType.NORMAL);
                editWage.setText("", TextView.BufferType.NORMAL);
                editActive.setText("", TextView.BufferType.NORMAL);*/
            }
        });


        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }




}
