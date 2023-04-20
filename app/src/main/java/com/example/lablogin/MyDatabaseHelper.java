package com.example.lablogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "LAB_DB";
    private static final String COLUMN_ID = "LAB_DB";
    private static final String COLUMN_NAME = "PERSON_NAME";
    private static final String COLUMN_PNUMBER = "PERSON_NUMBER";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "INSERT INTO ";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addPerson(String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PNUMBER, phoneNumber);

        db.update(TABLE_NAME, cv, null, null);
    }

    public void deletePerson(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
