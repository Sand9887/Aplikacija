package com.example.stan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registracija1 extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    // Deklaracija baze podataka
    BazaPod db1;


    // Deklaracija
    private Button signButton;

    private EditText imeSignin;

    private EditText prezimeSignin;

    private EditText emailSignin;


    private EditText usernameSignin;

    private EditText passwordSignin;

    private EditText mobSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija1);

        //Otvara se baza
        openHelper = new BazaPod(this);
        db1=new BazaPod(this);


        //Spajaju se varijable sa id button/EditText
        signButton = (Button) findViewById(R.id.button10);
        imeSignin = (EditText) findViewById(R.id.editText3);
        prezimeSignin = (EditText) findViewById(R.id.editText4);
        emailSignin = (EditText) findViewById(R.id.editText5);
        usernameSignin = (EditText) findViewById(R.id.editText6);
        passwordSignin = (EditText) findViewById(R.id.editText7);
        mobSignin = (EditText) findViewById(R.id.editText12);

        //Klik na sign up button
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int emailZnak;
                Boolean br = true;
                //Otvaranje baze za pisanje
                db = openHelper.getWritableDatabase();

                //"Hvatanje" teksta koji je korisnik unijeo prilikom registracije
                String ime = imeSignin.getText().toString();
                String prez = prezimeSignin.getText().toString();
                String emailA = emailSignin.getText().toString();
                String userName = usernameSignin.getText().toString();
                String userPass = passwordSignin.getText().toString();
                String brojMob= mobSignin.getText().toString();
                String spremaj = "";

                //Provjere ispravnosti
                //Ako su polja prazna, ispiši poruku
                if ((ime.equals("")) && (prez.equals("")) && (emailA.equals(""))  && (userName.equals("")) && (userPass.equals(""))&& (brojMob.equals(""))){
                    Toast.makeText(getApplicationContext(), "Polja su prazna. Unesite Vaše podatke u prazna polja!", Toast.LENGTH_SHORT).show();


                }
                //Ako je neko od polja prazno
                else if ((ime.equals("")) || (prez.equals("")) || (emailA.equals("")) || (userName.equals("")) || (userPass.equals(""))) {

                    //Spremi podatke u varijablu spremaj te prilikom klika na sign up ispiši poruku
                    if ((ime.equals(""))) {

                        spremaj = "Ime ";

                    }
                    if ((prez.equals(""))) {
                        spremaj += "Prezime ";

                    }
                    if ((emailA.equals(""))) {
                        spremaj += "Email ";

                    }

                    if ((userName.equals(""))) {
                        spremaj += "Username ";
                    }
                    if ((userPass.equals(""))) {
                        spremaj += "Lozinka ";

                    }
                    if ((brojMob.equals(""))) {
                        spremaj += "Lozinka ";

                    }
                    Toast.makeText(getApplicationContext(), "Polje " + spremaj + " je prazno. Unesite Vaše podatke u prazno polje!", Toast.LENGTH_SHORT).show();
                }
                //Ako je userName manja od 3 ispiši poruku


                if (!(ime.equals("")) && !(prez.equals("")) && !(emailA.equals(""))  && !(userName.equals("")) && !(userPass.equals("")) && !(brojMob.equals(""))) {
                    emailZnak = emailA.indexOf('@');
                    int emailZnak1=emailA.indexOf('.');
                    //Ako email adresa ne sadrži '@' i '.' ispiši poruku
                    if ((emailZnak < 0) || (emailZnak1<0)) {
                        Toast.makeText(getApplicationContext(), "Neispravna email adresa", Toast.LENGTH_SHORT).show();

                    }

                    else {
                        // Provjera u bazi da li postoji username ili e-mail
                        if((db1.provjeriUsername(userName)).equals(false)){
                            Toast.makeText(getApplicationContext(), "Username već postoji", Toast.LENGTH_SHORT).show();
                        }
                        else if((db1.provjeMail(emailA)).equals(false)){
                            Toast.makeText(getApplicationContext(), "E-mail već postoji", Toast.LENGTH_SHORT).show();}
                        else {
                            insertData(ime, prez, emailA, userName, userPass,brojMob);
                            Toast.makeText(getApplicationContext(), "Uspjesna registracija", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Registracija1.this, Profil.class);
                            startActivity(intent);
                        }




                    }

                }



            }
        });

    }
    //Unos podataka u bazu podataka u tablicu TABLE_NAME

    public void insertData(String ime, String prez, String emailA, String userName, String userPass,String mobitel) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BazaPod.COL_2, ime);
        contentValues.put(BazaPod.COL_3, prez);
        contentValues.put(BazaPod.COL_4, emailA);
        contentValues.put(BazaPod.COL_6, userName);
        contentValues.put(BazaPod.COL_7, userPass);
        contentValues.put(BazaPod.COL_8, mobitel);

        long id = db.insert(BazaPod.TABLE_NAME, null, contentValues);

    }


}
