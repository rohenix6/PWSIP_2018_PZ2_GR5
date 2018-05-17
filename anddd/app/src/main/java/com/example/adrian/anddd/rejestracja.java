package com.example.adrian.anddd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class rejestracja extends AppCompatActivity {

    Connection Connect;
    Button rejestracja;
    EditText ETpesel, ETpass, ETimie, ETnazwisko, Etnrlokalu, ETmiasto, ETadres, ETnumerkon, ETemail, ETrepass;
    //String DBpesel, DBPassword, db, ip, UserNamerStr, DBpassword, DBrepass, DBimie, DBnazwisko, DBnrlokalu, DBmiasto, DBadres, DBnumerkon, DBemail;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        ETpesel = findViewById(R.id.peselform);
        ETpass = findViewById(R.id.hasloform);
        ETrepass = findViewById(R.id.rehasloform);
        ETimie = findViewById(R.id.imieform);
        ETnazwisko = findViewById(R.id.nazwiskoform);
        Etnrlokalu = findViewById(R.id.nrlokaluform);
        ETmiasto = findViewById(R.id.miastoform);
        ETadres = findViewById(R.id.adresform);
        ETnumerkon = findViewById(R.id.nrkontaktowyform);
        ETemail = findViewById(R.id.emailform);
        rejestracja = findViewById(R.id.registryform);
        progressBar = findViewById(R.id.progressBarRejestracja);

        progressBar.setVisibility(View.GONE);
        rejestracja.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newRejestracja nRejestracja = new newRejestracja();
                nRejestracja.execute("");
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class newRejestracja extends AsyncTask<String, String, String> {
         String peselstr = ETpesel.getText().toString();
         String passwordstr = ETpass.getText().toString();
         //String repasstr = ETrepass.getText().toString();
         String imiestr = ETimie.getText().toString();
         String nazwiskostr = ETnazwisko.getText().toString();
         String nrlokalustr = Etnrlokalu.getText().toString();
         String miastostr = ETmiasto.getText().toString();
         String adresstr = ETadres.getText().toString();
         String numerkonstr = ETnumerkon.getText().toString();
         String emailstr = ETemail.getText().toString();
         String ConnectionResult = "";
         Boolean isSuccess = false;

         @Override
         protected void onPreExecute() {
              progressBar.setVisibility(View.VISIBLE);
              }

         @Override
         protected void onPostExecute(String result) {
              progressBar.setVisibility(View.GONE);
              Toast.makeText(rejestracja.this, result, Toast.LENGTH_SHORT).show();

              if (isSuccess) {
                   Toast.makeText(rejestracja.this, "Registry Successfull", Toast.LENGTH_LONG).show();
                   finish();
              }
         }

        @Override
        protected String doInBackground(String... params) {
               if (peselstr.trim().equals("")  || passwordstr.trim().equals("") /*|| repasstr.trim().equals("")*/ || imiestr.trim().equals("") || nazwiskostr.trim().equals("") || nrlokalustr.trim().equals("") || miastostr.trim().equals("") || adresstr.trim().equals("") || numerkonstr.trim().equals("") || emailstr.trim().equals(""))
               {
                   ConnectionResult = "Wypelnij wszystkie pola";
               } else {
                    try {
                         polaczenie conStr = new polaczenie();
                         Connect = conStr.CONN();

                         if (Connect == null) {
                              ConnectionResult = "Sprawdz polaczenie";
                         } else {/*,'"+passwordstr+"',*/

                            PreparedStatement query= Connect.prepareStatement("insert into uzytkownicy (Imie, Nazwisko, Miasto, Nr_Lokalu, Adres, Numer_Kontaktowy, Email, PESEL, Haslo) VALUES( '"+imiestr+"', '"+nazwiskostr+"','"+miastostr+"', '"+nrlokalustr+"',  '"+adresstr+"', '"+numerkonstr+"', '"+emailstr+"', '"+peselstr+"', '"+passwordstr+"' )");
                              //Statement stmt = Connect.createStatement();
                              query.executeUpdate();
                              ConnectionResult = "Dodano do bazy";

                             Intent i = new Intent(getApplicationContext(), sg.class);
                             startActivity(i);
                             finish();
                         }
                    } catch (Exception ex)
                    {
                         isSuccess = false;
                         ConnectionResult = ex.getMessage();
                    }
               }
               return ConnectionResult;
        }
    }
}