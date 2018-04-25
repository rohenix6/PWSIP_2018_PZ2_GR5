package com.example.adrian.and;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class rejestracjaform extends AppCompatActivity implements View.OnClickListener{


    Button bRegistry;
    EditText nazwauzytkownikaform, hasloform, peselform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracjaform);

        nazwauzytkownikaform = (EditText)findViewById(R.id.nazwauzytkownikaform);
        hasloform = (EditText) findViewById(R.id.hasloform);
        peselform = (EditText) findViewById(R.id.peselform);
        bRegistry = (Button) findViewById(R.id.registryform);

        bRegistry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.registryform:

                break;
    }
}

}
