package com.example.adrian.anddd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sg extends AppCompatActivity {
    Button ilogowanie;
    Button irejestracja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sg);

        ilogowanie = findViewById(R.id.blogowanie);
        ilogowanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),logowanie.class);
                startActivity(i);
                finish();
            }
        });

        irejestracja = findViewById(R.id.brejestracja);
        irejestracja.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),rejestracja.class);
                startActivity(j);
                finish();
            }
        });
    }
}

