package com.example.adrian.and;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Index extends AppCompatActivity {
    Button ilogowanie;
    Button irejestracja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ilogowanie = findViewById(R.id.blogowanie);
        ilogowanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),logowanieform.class);
                startActivity(i);
                finish();
            }
        });

        irejestracja = findViewById(R.id.brejestracja);
        irejestracja.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),rejestracjaform.class);
                startActivity(j);
                finish();
            }
        });
    }
}
