package com.edutilos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edutilos.model.TodoContract;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
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
        registerEvents();
    }

    private Button btnFindAll, btnInsert, btnUpdate, btnDelete, btnDetails,
            btnMainActivity;
    private EditText editUpdate, editDelete, editDetails;
    private EditText editFindAll;
    private void initComponents() {
        btnFindAll = findViewById(R.id.todo_btnFindAll);
        btnInsert = findViewById(R.id.todo_btnInsert);
        btnUpdate = findViewById(R.id.todo_btnUpdate);
        btnDelete = findViewById(R.id.todo_btnDelete);
        btnDetails = findViewById(R.id.todo_btnDetails);
        btnMainActivity = findViewById(R.id.todo_btnMainActivity);
        editFindAll = findViewById(R.id.todo_editFindAll);
        editUpdate = findViewById(R.id.todo_editUpdate);
        editDelete = findViewById(R.id.todo_editDelete);
        editDetails = findViewById(R.id.todo_editDetails);
    }
    private void registerEvents() {
        btnFindAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAllTodos();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(TodoContract.TodoEntry.COLUMN_DATE, System.currentTimeMillis());
                values.put(TodoContract.TodoEntry.COLUMN_TASK, "Simple Task");
                values.put(TodoContract.TodoEntry.COLUMN_STATUS, 0);
                Uri uri = getContentResolver().insert(TodoContract.TodoEntry.CONTENT_URI, values);

                findAllTodos();

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long id = Long.parseLong(editUpdate.getText().toString());
                    ContentValues values = new ContentValues();
                    values.put(TodoContract.TodoEntry.COLUMN_DATE, System.currentTimeMillis());
                    values.put(TodoContract.TodoEntry.COLUMN_TASK, "Updated Simple Task");
                    values.put(TodoContract.TodoEntry.COLUMN_STATUS, 1);
                    Uri uri = TodoContract.TodoEntry.buildTodoUriWithId(id);
                    getContentResolver().update(uri, values, null, null);
                    editUpdate.setText("");
                    findAllTodos();
                } catch(Exception ex) {
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long id = Long.parseLong(editDelete.getText().toString());
                    Uri uri = TodoContract.TodoEntry.buildTodoUriWithId(id);
                    getContentResolver().delete(uri, null, null);
                    editDelete.setText("");
                    findAllTodos();
                } catch(Exception ex) {
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG)
                    .show();
                }
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long id = Long.parseLong(editDetails.getText().toString());
                    Uri uri = TodoContract.TodoEntry.buildTodoUriWithId(id);
                    final Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    editDetails.setText("");
                    if(cursor.moveToFirst()) {
                        StringBuilder sb = new StringBuilder();
                        String _id = cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry._ID));
                        int date = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_DATE));
                        String task = cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_TASK));
                        int status = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_STATUS));
                        String newline = "\r\n";
                        sb.append("<<Todo Details>>").append(newline)
                                .append("id = ").append(_id).append(newline)
                                .append("date = ").append(date).append(newline)
                                .append("task = ").append(task).append(newline)
                                .append("status = ").append(status).append(newline);
                        Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_LONG)
                        .show();
                    }
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

    private void findAllTodos() {
        Cursor cursor = getContentResolver().query(TodoContract.TodoEntry.CONTENT_URI,
                null, null, null, null);
        StringBuilder sb = new StringBuilder();
//                while(cursor.moveToNext()) {
        if(cursor.moveToFirst()) {
            do {
                String _id = cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry._ID));
                int date = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_DATE));
                String task = cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_TASK));
                int status = cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_STATUS));
                sb.append("Todo(").append(_id).append(", ").append(task).
                        append(", ").append(date).append(", ")
                        .append(status).append(")\n");
            } while(cursor.moveToNext());
        }

        editFindAll.setText(sb.toString(), TextView.BufferType.NORMAL);
    }
}
