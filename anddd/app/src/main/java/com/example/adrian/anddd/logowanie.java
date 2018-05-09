package com.example.adrian.anddd;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;


public class logowanie extends AppCompatActivity {

    private static final String dane_bazy = "jdbc:mysql://:3306/and?useUnicode=yes&characterEncoding=utf-8";
    private static final String uzytkownik_bazy = "root";
    private static final String haslo_bazy = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

    }
}

