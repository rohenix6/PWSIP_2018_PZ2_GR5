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

public class wizyta_ginekolog extends AppCompatActivity {

    Connection Connect;
    Button UmowWizyte, Rezygnuj, ZmienSp;
    Spinner specSpin, lekSpin;
    String wybLekarz;
    TextView EndowyTekst;
    PreparedStatement smtpa;
    ResultSet crs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizyta_ginekolog);

        setTitle("Wybierz Date i godzinę wizyty:");
        Rezygnuj = findViewById(R.id.bRezygnuj);
        ZmienSp = findViewById(R.id.bZmienSpec);
        UmowWizyte = findViewById(R.id.bUmowWizyte);
        specSpin = findViewById(R.id.spinerSpec);
        lekSpin = findViewById(R.id.spinerLek);
        EndowyTekst = findViewById(R.id.KoncoweTextView);
        //final String name = (String) getIntent().getStringExtra("WybSpecjalizacjja");

       //EndowyTekst.setText(name);
        UmowWizyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // nowaWizyta nWizyta = new nowaWizyta;
               // nWizyta.execute("");
            }
        });
        ZmienSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), listaLekarzy.class);
                startActivity(i);

            }
        });
        Rezygnuj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);

            }
        });


 //final String wybranyLekarz= lekSpin.getSelectedItem().toString();
        try {

            final String queray = "SELECT Imie, Nazwisko FROM `lekarze` WHERE Specjalizacja='Ginekolog'";
            polaczenie conStr = new polaczenie();
            Connect = conStr.CONN();
            smtpa = Connect.prepareStatement(queray);
            crs = smtpa.executeQuery();
            ArrayList<String> data2 = new ArrayList<String>();

            while (crs.next()) {
                String idd = crs.getString("Imie");
                String iddd = crs.getString("Nazwisko");
                data2.add("Dr " + idd + " " + iddd);

            }
            ArrayAdapter CoreAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data2);
            lekSpin.setAdapter(CoreAdapter);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        lekSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wybLekarz = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), "Dr " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                String lol = lekSpin.getSelectedItem().toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
