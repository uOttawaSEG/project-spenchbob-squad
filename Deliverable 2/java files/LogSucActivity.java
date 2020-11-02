package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LogSucActivity extends AppCompatActivity {
    TextView txt;
    DatabaseHelper db;
    public static Service[] services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_suc);
        db = new DatabaseHelper(this);
        txt = findViewById(R.id.textView3);
        String name = db.getName();
        String identity = db.getIdentity();
        txt.setText("Welcome " +name+"!"+"\n"+"\n"+"You are logged in as a(n) "+identity);
    }
}