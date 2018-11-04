package com.edutilos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edutilos.model.TodoContract;

public class TodoDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mytodolist.db";
    private static final int DATABASE_VERSION = 1;
    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(TodoContract.TodoEntry.TABLE_NAME).append(" (")
                .append(TodoContract.TodoEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(TodoContract.TodoEntry.COLUMN_DATE).append(" INTEGER NOT NULL, ")
                .append(TodoContract.TodoEntry.COLUMN_TASK).append(" TEXT NOT NULL, ")
                .append(TodoContract.TodoEntry.COLUMN_STATUS).append(" INTEGER NOT NULL);");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TodoContract.TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}





















