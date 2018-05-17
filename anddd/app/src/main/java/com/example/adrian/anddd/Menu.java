package com.example.adrian.anddd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button llistaLekarzy;
    Button zarejestrujWizyte;
    Button twojeWizyty;
    Button lokalizacja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        llistaLekarzy = findViewById(R.id.blistaLekarzy);
        llistaLekarzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),listaLekarzy.class);
                startActivity(i);
                finish();

            }
        });
       /*  zarejestrujWizyte= findViewById(R.id.bzarejestrowaneWizyty);
        zarejestrujWizyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),zarejestrujWizyt.class);
                startActivity(i);
                finish();
            }
        });
        twojeWizyty= findViewById(R.id.blogowanie);
        twojeWizyty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),twojeWizyt.class);
                startActivity(i);
                finish();
            }
        });
        lokalizacja = findViewById(R.id.blogowanie);
        lokalizacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),lokalizacj.class);
                startActivity(i);
                finish();
            }
        });*/
    }
}
