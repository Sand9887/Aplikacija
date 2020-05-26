package com.example.stan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Poruka extends AppCompatActivity {
    BazaPod db;

    ListView userList;
    ArrayList<String> listItemDr;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poruka);
        userList = findViewById(R.id.lista1);
        listItemDr = new ArrayList<String>();



        db = new BazaPod(this);
        Cursor cursor = db.usporediEmail();
        while(cursor.moveToNext()){


               // listItemDr.add("Šalje: " + cursor.getString(1) + "\n" + "Tema: " + cursor.getString(2) + "\n" + "Poruka: " + cursor.getString(3));


                listItemDr.add("Šalje: " + cursor.getString(1) + "\n" + "Tema: " + cursor.getString(2) + "\n" + "Poruka: " + cursor.getString(3));



            }

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,listItemDr);
        userList.setAdapter(adapter);




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
                Toast.makeText(Poruka.this,"USPJEŠNA ODJAVA!",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Poruka.this,Prijava.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }

