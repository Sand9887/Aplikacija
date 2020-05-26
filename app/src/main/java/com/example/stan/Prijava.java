package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Prijava extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button prijava;
    TextView username;
    TextView lozinka;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);
        //Otvara se stvorena baza podatka
        openHelper = new BazaPod(this);

        //Čita se iz baze
        db = openHelper.getReadableDatabase();




        //Spajaju se varijable sa id button/edit text
            username = (EditText) findViewById(R.id.editText);
            lozinka = (EditText) findViewById(R.id.editText2);
            prijava = (Button)findViewById(R.id.button4);


            //Ako se klikne na button log in
            prijava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Dohvaća se string
                    String provAdres = username.getText().toString();
                    String provLoz = lozinka.getText().toString();

                    //Pomoću kursora čita se iz baze (tablice TABLE_NAME) jedan po jedan zapis
                    cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s=? AND %s=?", BazaPod.TABLE_NAME, BazaPod.COL_6, BazaPod.COL_7), new String[]{provAdres, provLoz});
                    Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));

                    //Ako kursor dohvaca podatke
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            // Ako kursor je našao podatak, javlja se korisniku potvrdna poruka, šalje na Main4Activity
                            cursor.moveToNext();
                            Toast.makeText(getApplicationContext(), "Uspješna prijava!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Prijava.this, Profil.class);
                            startActivity(intent);
                            finish();
                        }
                        // Ako kursor nije našao podatak, javlja se korisniku negativna poruka, i ne šalje ga se na Main4Activity
                        else {
                            Toast.makeText(getApplicationContext(), "Neuspješna prijava!", Toast.LENGTH_LONG).show();
                        }

                    }


                }
            });


    }
}
