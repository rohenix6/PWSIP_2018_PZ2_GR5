package com.example.adrian.anddd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractQueue;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class listaLekarzy extends AppCompatActivity {



    ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lekarzy);

        setTitle("Wybierz Specjalizację:");
        lv = findViewById(R.id.lvlistaLekarzy);
        String[] wartosci = new String[]{"Ginekolog","Rodzinny","Okulista"};

        ArrayAdapter<String> alisty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, wartosci);

        lv.setAdapter(alisty);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                   Intent i = new Intent(view.getContext(), wizyta_ginekolog.class);
                   startActivityForResult(i,0);
                }
                else if  (position==1)
                {
                    Intent i = new Intent(listaLekarzy.this, wizyta_rodzinny.class);
                    startActivity(i);
                }
                else if (position==2)
                {
                    Intent i = new Intent(view.getContext(), wizyta_okulista.class);
                    startActivity(i);
                }
            }
        });


    }
}






