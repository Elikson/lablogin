package com.example.lablogin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {

    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dbHelper = new MyDatabaseHelper(this);

        Button btSave = (Button) findViewById(R.id.button_save);
        Button btDisplay = (Button) findViewById(R.id.button_display);
        EditText tvName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText tvPhoneNumber = (EditText) findViewById(R.id.editTextTextPersonNumber);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addPerson(tvName.getText().toString(), tvPhoneNumber.getText().toString());
            }
        });

        btDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.fetchPerson();
            }
        });
    }

}