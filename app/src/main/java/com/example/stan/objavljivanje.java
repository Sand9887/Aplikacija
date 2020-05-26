package com.example.stan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class objavljivanje extends AppCompatActivity {
    SQLiteOpenHelper openHelper;

    // Deklaracija baze podataka
    BazaPod db1;

    RadioGroup radioGroup;

    RadioGroup radioGroupVrijeme;

    RadioButton radioButton;
    RadioButton mjesec;
    RadioButton godina;
    RadioButton iznajStan;
    RadioButton kupnjaStan;
    RadioButton kupnjKuce;


    TextView labela;


    EditText editMjesec;
    EditText editGodina;
    EditText naslov;
    EditText lokacija;
    EditText tip;
    EditText povrsina;
    EditText cijena;
    EditText opis;
    EditText email;


    Button objavi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objavljivanje);

        radioGroup= findViewById(R.id.radioGroup);
        radioGroupVrijeme= findViewById(R.id.radioGroup2);

        labela=findViewById(R.id.textView12);

        mjesec=findViewById(R.id.radioButton4);
        godina=findViewById(R.id.radioButton11);

        mjesec.setVisibility(View.INVISIBLE);
        godina.setVisibility(View.INVISIBLE);


        editMjesec=findViewById(R.id.editText9);
        editGodina=findViewById(R.id.editText10);



        objavi=findViewById(R.id.button3);



        naslov=findViewById(R.id.editText11);
        lokacija=findViewById(R.id.editText12);
        povrsina=findViewById(R.id.editText14);
        cijena=findViewById(R.id.editText15);
        opis=findViewById(R.id.editText8);
        email=findViewById(R.id.editText13);



        iznajStan=findViewById(R.id.radioButton10);
        kupnjaStan=findViewById(R.id.radioButton9);
        kupnjKuce=findViewById(R.id.radioButton8);

        db1=new BazaPod(this);

        int radioId=radioGroup.getCheckedRadioButtonId();

        radioButton=findViewById(radioId);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton10){
                    godina.setVisibility(View.VISIBLE);
                    mjesec.setVisibility(View.VISIBLE);


                } else {
                    editGodina.setVisibility(View.INVISIBLE);
                    editMjesec.setVisibility(View.INVISIBLE);

                }
            }
        });



        radioGroupVrijeme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton4){
                    editMjesec.setVisibility(View.VISIBLE);
                    editGodina.setVisibility(View.INVISIBLE);


                } else {
                    editGodina.setVisibility(View.VISIBLE);
                    editMjesec.setVisibility(View.INVISIBLE);

                }
            }
        });
        objavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iznajStan=findViewById(R.id.radioButton10);
                kupnjaStan=findViewById(R.id.radioButton9);
                kupnjKuce=findViewById(R.id.radioButton8);
                String naslovN = naslov.getText().toString();
                String lokacijaN = lokacija.getText().toString();
                String povrsinaN = povrsina.getText().toString();
                String cijenaN = cijena.getText().toString();
                String opisN = opis.getText().toString();
                String emailN=email.getText().toString();

                if(iznajStan.isChecked()){
                    String iznajStanaN= iznajStan.getText().toString();
                    db1.insertData(naslovN,lokacijaN,povrsinaN,cijenaN,opisN,"Iznajmljivanje stana",emailN);
                    Toast.makeText(objavljivanje.this,"Uspjesno su podaci spremljeni",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(objavljivanje.this,Profil.class);
                    startActivity(i);
                }

                else if(kupnjaStan.isChecked()){
                    String kupnjaStanaN= kupnjaStan.getText().toString();
                    db1.insertData(naslovN,lokacijaN,povrsinaN,cijenaN,opisN,"Prodaja kuce",emailN);
                    Toast.makeText(objavljivanje.this,"Uspjesno su podaci spremljeni",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(objavljivanje.this,Profil.class);
                    startActivity(i);
                }
                else if(kupnjKuce.isChecked()){
                    String kupnjaKuceN= kupnjKuce.getText().toString();
                    db1.insertData(naslovN,lokacijaN,povrsinaN,cijenaN,opisN,"Prodaja stana",emailN);
                    Toast.makeText(objavljivanje.this,"Uspjesno su podaci spremljeni",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(objavljivanje.this,Profil.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(objavljivanje.this,"Niste izabrali sve podatke",Toast.LENGTH_SHORT).show();
                }





}


        });



            }



    public void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();

        radioButton=findViewById(radioId);

        Toast.makeText(this,""+radioButton.getText(),Toast.LENGTH_SHORT).show();

    }

    public void checkButton1(View v){
        int radioId=radioGroupVrijeme.getCheckedRadioButtonId();

        radioButton=findViewById(radioId);


        Toast.makeText(this,""+radioButton.getText(),Toast.LENGTH_SHORT).show();

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
            case  R.id.odjava:
                Toast.makeText(objavljivanje.this,"Uspješna odjava",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(objavljivanje.this,Prijava.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
