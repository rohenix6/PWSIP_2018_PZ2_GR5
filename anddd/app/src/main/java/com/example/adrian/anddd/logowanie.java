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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class logowanie extends AppCompatActivity {



    Connection Connect;
    Button button;
    EditText ETpesel, ETpass;
    String DBUserNameStr, DBPassword, db, ip, UserNamerStr, PasswordStr;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        ETpesel = findViewById(R.id.enazwauzytkownikaform);
        ETpass = findViewById(R.id.ehasloform);
        button = findViewById(R.id.bzalogujform);
        progressBar = findViewById(R.id.pprogress);


        DBUserNameStr = "";
        DBPassword = "";

        progressBar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserNamerStr = ETpesel.getText().toString();
                PasswordStr = ETpass.getText().toString();

                checkLogin check_Login = new checkLogin();
                check_Login.execute(UserNamerStr, PasswordStr);
            }

        });

    }

    @SuppressLint("StaticFieldLeak")
    public class checkLogin extends AsyncTask <String, String, String>
    {
        String ConnectionResult = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(logowanie.this, result, Toast.LENGTH_SHORT).show();

            if (isSuccess)
            {
                Toast.makeText(logowanie.this, "Login Successfull", Toast.LENGTH_LONG).show();
                finish();

            }

        }

        @Override
        protected String doInBackground(String... params) {

            String usernam = UserNamerStr;
            String passwordd = PasswordStr;

            if (usernam.trim().equals("") || passwordd.trim().equals("")) {
                ConnectionResult = "Wypelnij pola";
            } else {
                try {
                    polaczenie conStr = new polaczenie();
                    Connect = conStr.CONN();

                    if (Connect == null) {
                        ConnectionResult = "Sprawdz polaczenie";
                    }
                    else
                        {
                        String query = "select * from uzytkownicy where PESEL= '" + usernam.toString() + "' and Haslo = '" + passwordd.toString() + "'";
                        Statement stmt = Connect.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if (rs.next()) {
                            ConnectionResult = "Login successful";
                            isSuccess = true;
                            Connect.close();

                            Intent i = new Intent(getApplicationContext(), Menu.class);
                            startActivity(i);
                            finish();
                        } else {
                            ConnectionResult = "Z tym nie przejdziesz";
                            isSuccess = false;
                        }

                    }

                } catch (Exception ex) {
                    isSuccess = false;
                    ConnectionResult = ex.getMessage();

                }


            }


            return ConnectionResult;

        }
    }
}


