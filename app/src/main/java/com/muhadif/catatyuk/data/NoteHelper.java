package com.muhadif.catatyuk.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.muhadif.catatyuk.entity.Note;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.muhadif.catatyuk.data.DatabaseContract.NoteColumns.DATE;
import static com.muhadif.catatyuk.data.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.muhadif.catatyuk.data.DatabaseContract.NoteColumns.TITLE;
import static com.muhadif.catatyuk.data.DatabaseContract.TABLE_NOTE;

public class NoteHelper {

    private static String DATABASE_NAME = TABLE_NOTE;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase sqLiteDatabase;

    public NoteHelper(Context context){
        this.context = context;
    }

    public NoteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);

        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<Note> query(){
        ArrayList<Note> arrayList = new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.query(DATABASE_NAME, null, null,
                null,
                null,
                null,
                _ID + " DESC",
                null);
        cursor.moveToFirst();

        Note note;

        if (cursor.getCount() > 0){
            do {

                note = new Note();

                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)) );
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

                arrayList.add(note);

                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insert(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, note.getTitle());
        contentValues.put(DESCRIPTION, note.getDescription());
        contentValues.put(DATE, note.getDate());
        return sqLiteDatabase.insert(DATABASE_NAME, null, contentValues);

    }

    public int update(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, note.getTitle());
        contentValues.put(DESCRIPTION, note.getDescription());
        contentValues.put(DATE, note.getDate());
        return sqLiteDatabase.update(DATABASE_NAME, contentValues, _ID + " = '" + note.getId()
                + "' ", null);
    }

    public int delete(int id){
        return sqLiteDatabase.delete(TABLE_NOTE, _ID + " = '" + id + "' ", null);
    }
}
