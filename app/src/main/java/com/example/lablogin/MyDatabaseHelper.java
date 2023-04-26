package com.example.lablogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "LAB_DB";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "PERSON_NAME";
    public static final String COLUMN_PNUMBER = "PERSON_NUMBER";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_PNUMBER + " TEXT);";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addPerson(String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PNUMBER, phoneNumber);

        long result = db.insert(TABLE_NAME, null, cv);
        Toast.makeText(context, result != -1 ? "Inserido com sucesso!" : "Falha ao inserir!", Toast.LENGTH_SHORT).show();
    }

    public Cursor fetchPerson() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_PNUMBER };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        Log.i("POO", cursor.getCount() + "");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void deletePerson(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=" + _id, null);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE (0 = 0)");
    }
}
