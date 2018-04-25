package com.example.adrian.and;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class logowanieform extends AppCompatActivity implements View.OnClickListener{

    Button blogin;
    EditText nazwauzytkownikaform, hasloform;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanieform);

        nazwauzytkownikaform = (EditText) findViewById(R.id.nazwauzytkownikaform);
        hasloform = (EditText) findViewById(R.id.hasloform);
        blogin = (Button) findViewById(R.id.zalogujform);

        blogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.zalogujform:


                break;

        }
    }
}
