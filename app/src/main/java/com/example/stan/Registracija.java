package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registracija extends AppCompatActivity {
    Button registracija;
    Button prijava;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        registracija=(Button)findViewById(R.id.button8);
        prijava=(Button)findViewById(R.id.button9);

        registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Registracija.this,Registracija1.class);
                startActivity(i);
            }
        });

        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Registracija.this,Prijava.class);
                startActivity(i);
            }
        });
    }
}
