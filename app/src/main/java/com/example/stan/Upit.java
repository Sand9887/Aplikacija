package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Upit extends AppCompatActivity {
    Intent j;
    TextView imePrez;
    TextView mail;
    EditText emailKorisnik;
    EditText poruka;
    EditText subjekt;
    Button posalji;
    String sviPodaci="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upit);
        j=getIntent();
        imePrez=findViewById(R.id.textView33);
        mail=findViewById(R.id.textView34);

        emailKorisnik=findViewById(R.id.editText16);
        poruka=findViewById(R.id.editText18);
        subjekt=findViewById(R.id.editText17);

        posalji=findViewById(R.id.button14);


        int id=j.getIntExtra("id1",0);
        String id1=String.valueOf(id);



        final BazaPod databaseHelper = new BazaPod(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        // pripremi i izvedi upit
        String upit2 = "SELECT * FROM " + BazaPod.TABLE_NAME + " WHERE " + BazaPod.COL_1+ "= ?";

        Cursor c = db.rawQuery(upit2, new String[] {id1});


        if ((c != null) && (c.getCount() > 0)) { // ima podataka

            while (c.moveToNext()) {

                String ime = c.getString(1);
                String prezime = c.getString(2);
                String imeP=ime+" "+prezime;
                imePrez.setText(imeP);
                String mailK = c.getString(3);
                mail.setText(mailK);

            }
        }
        posalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailK = emailKorisnik.getText().toString();
                int email = emailK.indexOf('@');
                int emailZnak1 = emailK.indexOf('.');
                //Ako email adresa ne sadrži '@' i '.' ispiši poruku
                if ((emailZnak1 < 0)) {
                    Toast.makeText(getApplicationContext(), "Neispravna email adresa", Toast.LENGTH_SHORT).show();

                } else {
                    SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
                    databaseHelper.insertDataPoruka(emailKorisnik.getText().toString(),subjekt.getText().toString(), poruka.getText().toString(),mail.getText().toString());

                    Intent i = new Intent(Upit.this, MainActivity.class);
                    Toast.makeText(Upit.this, "Uspjesno poslano", Toast.LENGTH_SHORT).show();
                    startActivity(i);


                }
            }
        });
    }

}
