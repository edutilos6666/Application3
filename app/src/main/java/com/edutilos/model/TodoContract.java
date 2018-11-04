package com.edutilos.model;

import android.net.Uri;
import android.provider.BaseColumns;

public class TodoContract {
    public static final String CONTENT_AUTHORITY = "com.edutilos.model.todo";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);

    public static final class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TASK = "task";
        public static final String COLUMN_STATUS = "status";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME)
                .build();

        public static Uri buildTodoUriWithId(long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }

    }
}



























