package com.example.adrian.anddd;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class listaLekarzy extends AppCompatActivity {
    //Spinner dropdown = findViewById(R.id.slistaSpecjalistow);
    //String[] items = new String[]{"1", "2", "three"};

    Spinner spec_spiner;
    String wybSpecjal;
    ArrayAdapter<CharSequence> adapter;
    TextView wybor;
    Connection Connect;
    PreparedStatement stmt;
    ResultSet rs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lekarzy);

        wybor = findViewById(R.id.ttwybor);

        spec_spiner = findViewById(R.id.slistaSpecjalistow);
        /*adapter = ArrayAdapter.createFromResource(this, R.array.Specjalizacje, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spec_spiner.setAdapter(adapter);
        spec_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wybSpecjal = parent.getItemAtPosition(position).toString();

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Wybrany", Toast.LENGTH_LONG).show();
                wybor.setText(wybSpecjal);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
           }
        });*/
        String query = "select Specjalizacja from lekarze";
        try {
            polaczenie conStr = new polaczenie();
            Connect = conStr.CONN();
            stmt = Connect.prepareStatement(query);
            rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<String>();
            while(rs.next())
            {
              String id = rs.getString("Specjalizacja");
              data.add(id);
            }
            String[] array = data.toArray(new String[0]);
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
            spec_spiner.setAdapter(NoCoreAdapter);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            }
            spec_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String name = spec_spiner.getSelectedItem().toString();
                    wybSpecjal = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Wybrany", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
    }

}
