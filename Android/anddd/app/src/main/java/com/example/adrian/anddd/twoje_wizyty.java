package com.example.adrian.anddd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class twoje_wizyty extends AppCompatActivity {

    Connection Connect;
    Button PobierzWizyte;
    String wyzyty;
    TextView WizytowyTekst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Twoje wizyty:");
        PobierzWizyte = findViewById(R.id.bwizyty);
        WizytowyTekst = findViewById(R.id.KoncoweTextView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoje_wizyty);

    }
}
