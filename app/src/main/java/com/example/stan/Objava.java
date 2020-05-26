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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Objava extends AppCompatActivity {

    BazaPod db;
    private Button add_data;
    private Button izbrisi;
    private Button update;
    ImageView imageView;

    //Deklaracija listView i adaptera
    private ListView userList;
    ArrayList<String> listItem2;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objava);

            //Poziv baze
            db=new BazaPod(this);
            listItem2 = new ArrayList<>();

            //Povezivanje elemenata sa svojim id


            userList= findViewById(R.id.listview);

            imageView=findViewById(R.id.imageView2);

//        db.deleteAll();
            //Metoda ispisa podataka
            viewData();


            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    String text=userList.getItemAtPosition(i).toString();
                    Toast.makeText(Objava.this," "+ text,Toast.LENGTH_SHORT).show();
                }
            });
            //Na klik button izbrisi korisnik briše podatke

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Objava.this,Profil.class);
                    startActivity(i);


                }
            });




        }
        //Metoda koja pomoću cursora ide od prvog do zadnjeg podatka i ispisiva ih ako postoje
        private void viewData(){

            Cursor cursor= db.getList();
            //Provjera postoje li podaci u bazi
            //Ako ne ispiši poruku
            if((cursor.getCount()== 0) ){

                Toast.makeText(Objava.this,"nema podataka",Toast.LENGTH_SHORT).show();
            }
            else{
                //Ako da ispiši podatke u listview jedan po jedan
                while(cursor.moveToNext()){
                    listItem2.clear();
                    listItem2.add("Naslov :\n" + "\n"+ cursor.getString(1));{

                    }

                    listItem2.add("Lokacija :\n" + "\n"+ cursor.getString(2));{

                    }
                    listItem2.add("Površina :\n" + "\n"+ cursor.getString(3)+"m2");{

                    }

                    listItem2.add("Cijena :\n" +"\n"+ cursor.getString(4)+"kn");{

                    }

                    listItem2.add("Opis :\n" + "\n"+ cursor.getString(5));{

                    }
                    listItem2.add("Tip kuće  :\n" +"\n"+ cursor.getString(6));{

                    }





                    adapter= new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,listItem2);
                    userList.setAdapter(adapter);

                }

            }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.vanjskiIzbornik:
                return true;

            case R.id.odjava:
                Toast.makeText(Objava.this,"Uspjesna odjava",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Objava.this,Prijava.class);
                startActivity(i);
                return true;

             default:
                 return super.onOptionsItemSelected(item);
        }



    }
}
