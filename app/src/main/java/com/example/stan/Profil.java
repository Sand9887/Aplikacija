package com.example.stan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends AppCompatActivity {
    Button objavi;
    Button vidiObjavu;
    BazaPod db;
    ImageView image;
    TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        objavi = (Button) findViewById(R.id.button5);
        vidiObjavu = findViewById(R.id.button11);
        image=findViewById(R.id.imageView3);
        mail=(TextView)findViewById(R.id.textView16);
        db = new BazaPod(this);


        objavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profil.this, objavljivanje.class);
                startActivity(i);
            }
        });
        vidiObjavu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profil.this, Objava.class);
                startActivity(i);

            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profil.this, Poruka.class);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.vanjskiIzbornik:
                return true;


            case R.id.odjava:
                Toast.makeText(Profil.this,"USPJEŠNA ODJAVA!",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Profil.this,Prijava.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


