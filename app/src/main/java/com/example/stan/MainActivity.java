package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button kupac;
    Button proizvodac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kupac=(Button)findViewById(R.id.button);
        proizvodac=(Button)findViewById(R.id.button2);

        kupac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

        proizvodac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,Registracija.class);
                startActivity(i1);
            }
        });
    }
}
