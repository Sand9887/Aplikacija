package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.EnumMap;

public class Vise extends AppCompatActivity {
    TextView naslov;
    TextView lokacija;
    TextView opis;
    TextView povrsina;
    TextView cijena;
    TextView email;
    TextView ime;
    TextView prezime;
    TextView mobitel;
    TextView tipProdaje;


    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vise);
        naslov=findViewById(R.id.textView14);
        lokacija=findViewById(R.id.textView15);
        opis=findViewById(R.id.textView17);
        povrsina=findViewById(R.id.textView26);
        cijena=findViewById(R.id.textView27);
        email=findViewById(R.id.textView28);
        ime=findViewById(R.id.textView29);
        prezime=findViewById(R.id.textView30);
        tipProdaje=findViewById(R.id.textView39);
        mobitel=findViewById(R.id.textView6);




        i=getIntent();

        int id=i.getIntExtra("id",0);
        String id1=String.valueOf(id);
        String mail=i.getStringExtra("mail");

        BazaPod databaseHelper = new BazaPod(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        // pripremi i izvedi upit
        String upit = "SELECT * FROM " + BazaPod.TABLE_NAME1 + " WHERE " + BazaPod.TOL_1+ "= ?";

        Cursor c = db.rawQuery(upit, new String[] {id1});

        String upit2 = "SELECT * FROM " + BazaPod.TABLE_NAME + " WHERE " + BazaPod.COL_1+ "= ?";

        Cursor cursor = db.rawQuery(upit2, new String[] {id1});


        if ((c != null) && (c.getCount() > 0)) { // ima podataka

            while (c.moveToNext()) {

                String nasOglas = c.getString(1);
                naslov.setText(nasOglas);
                String lokOglas = c.getString(2);
                lokacija.setText("Lokacija:"+lokOglas);
                String povOglas = c.getString(3);
                povrsina.setText("Površina:"+povOglas+"m2");
                String cijOglas = c.getString(4);
                cijena.setText("Cijena:"+cijOglas);
                String opisOglas = c.getString(5);
                opis.setText("Opis:"+opisOglas);
                String tip = c.getString(6);
                tipProdaje.setText("Tip prodaje:"+tip);
            }
        }
        if ((cursor != null) && (cursor.getCount() > 0)) { // ima podataka

            while (cursor.moveToNext()) {


                String emailOglas=cursor.getString(3);
                email.setText(emailOglas);
                String imeOglas=cursor.getString(2);
                ime.setText("Ime:"+imeOglas);
                String prezOglas=cursor.getString(1);
                prezime.setText("Prezime:"+prezOglas);
                String brojMobitela=cursor.getString(6);
                mobitel.setText("Mobitel:"+brojMobitela);

            }
        }




    }
}
