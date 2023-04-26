package com.example.lablogin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {

    MyDatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;

    final String[] from = new String[] {MyDatabaseHelper.COLUMN_ID,
            MyDatabaseHelper.COLUMN_NAME, MyDatabaseHelper.COLUMN_PNUMBER };

    final int[] to = new int[] { R.id.textViewID, R.id.textViewNAME, R.id.textViewPNUMBER };

    ListView lvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dbHelper = new MyDatabaseHelper(this);
        dbHelper.deleteAll();

        Button btSave = (Button) findViewById(R.id.button_save);
//        Button btDisplay = (Button) findViewById(R.id.button_display);
        lvPerson = (ListView) findViewById(R.id.listViewPerson);
        EditText tvName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText tvPhoneNumber = (EditText) findViewById(R.id.editTextTextPersonNumber);

        cursor = dbHelper.fetchPerson();

        adapter = new SimpleCursorAdapter(this, R.layout.view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        lvPerson.setAdapter(adapter);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addPerson(tvName.getText().toString(), tvPhoneNumber.getText().toString());
                cursor = dbHelper.fetchPerson();

                adapter = new SimpleCursorAdapter(FormActivity.this, R.layout.view_record, cursor, from, to, 0);
                adapter.notifyDataSetChanged();

                lvPerson.setAdapter(adapter);
            }
        });

//        btDisplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor cursor = dbHelper.fetchPerson();
//                Log.i("POO", cursor.toString());
//            }
//        });
    }

    @Override
    protected void onResume() {


        super.onResume();
    }
}