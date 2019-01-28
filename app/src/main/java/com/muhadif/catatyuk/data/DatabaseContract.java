package com.muhadif.catatyuk.data;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NOTE = "note";

    public static final class NoteColumns implements BaseColumns {

        static String TITLE = "title";

        static String DESCRIPTION = "description";

        static String DATE = "date";
    }
}
