package com.edutilos.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.edutilos.dao.TodoDbHelper;
import com.edutilos.model.TodoContract;

public class TodoProvider extends ContentProvider {
    private TodoDbHelper todoDbHelper;
    public static final int CODE_TODO = 100;
    public static final int CODE_TODO_WITH_ID = 101;
    private static final UriMatcher uriMatcher = buildUriMatcher();
    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TodoContract.CONTENT_AUTHORITY;
        /* content://com.edutilos.model.todo/todo/ */
        matcher.addURI(authority, TodoContract.TodoEntry.TABLE_NAME, CODE_TODO);
        /* content://com.edutilos.model.todo/todo/1 (e.g) */
        matcher.addURI(authority, TodoContract.TodoEntry.TABLE_NAME+"/#", CODE_TODO_WITH_ID);
        return matcher;
    }
    @Override
    public boolean onCreate() {
        todoDbHelper = new TodoDbHelper(getContext());
        return todoDbHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case CODE_TODO_WITH_ID: {
                String _ID = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{_ID};
                cursor = todoDbHelper.getReadableDatabase().query(
                  TodoContract.TodoEntry.TABLE_NAME,
                  projection,
                  TodoContract.TodoEntry._ID+" = ?",
                  selectionArguments,
                  null, null,sortOrder
                );
                break;
            }
            case CODE_TODO: {
                cursor = todoDbHelper.getReadableDatabase().query(
                        TodoContract.TodoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: "+ uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        switch(uriMatcher.match(uri)) {
            case CODE_TODO:
                long _id= db.insert(TodoContract.TodoEntry.TABLE_NAME, null, values);
                if(_id != -1) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return TodoContract.TodoEntry.buildTodoUriWithId(_id);
            default:
                return null;
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        int count = 0;
        switch(uriMatcher.match(uri)) {
            case CODE_TODO:
                count = db.delete(TodoContract.TodoEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CODE_TODO_WITH_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TodoContract.TodoEntry.TABLE_NAME, "_ID = "+id +
                        (!TextUtils.isEmpty(selection)?" AND ("+ selection+ ")": ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+ uri);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        int count = 0;
        switch(uriMatcher.match(uri)) {
            case CODE_TODO:
                count = db.update(TodoContract.TodoEntry.TABLE_NAME,values, selection, selectionArgs);
                break;
            case CODE_TODO_WITH_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(TodoContract.TodoEntry.TABLE_NAME, values, "_ID = " + id +
                        (!TextUtils.isEmpty(selection)?" AND ("+ selection+")": ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+ uri);
        }
        return count;
    }
}
