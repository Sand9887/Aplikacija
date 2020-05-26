package com.example.stan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    //Deklaracija
    ListView list;

    BazaPod db;
    Intent i;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        //Deklaracija listview i adapter-a
        list = findViewById(R.id.listview1);
        imageView = findViewById(R.id.imageView);



        db=new BazaPod(this);



        showList();


    }

    private void showList() {




        ArrayList<ContactListItems> contactList = new ArrayList<ContactListItems>();
        contactList.clear();

        Cursor cursor = db.viewData2();
       // Cursor cursor = db.viewData();


            if (cursor != null && cursor.getCount() != 0) {


                if (cursor.moveToFirst()) {


                    do {
                        ContactListItems contactListItems = new ContactListItems();

                        contactListItems.setNaslov(cursor.getString(cursor.getColumnIndex("Naslov")));
                        contactListItems.setLokacija(cursor.getString(cursor.getColumnIndex("Lokacija")));
                        contactListItems.setPovrsina(cursor.getString(cursor.getColumnIndex("Povrsina")));
                        contactListItems.setCijena(cursor.getString(cursor.getColumnIndex("Cijena")));
                        contactListItems.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                        contactListItems.setEmail(cursor.getString(cursor.getColumnIndex("Email")));

                        contactList.add(contactListItems);

                    } while (cursor.moveToNext());
                }
            }


        cursor.close();


        ContactListAdapter contactListItems=new ContactListAdapter(Main2Activity.this,contactList);

        list.setAdapter(contactListItems);

    }

    class ContactListAdapter extends BaseAdapter {
        Context context;
        Button vise;
        ArrayList<ContactListItems> contactList;
        BazaPod db;





        public ContactListAdapter(Context context, ArrayList<ContactListItems> list) {
            Button vise;
            this.context = context;
            contactList = list;
        }

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return contactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ContactListItems contactListItems = contactList.get(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_layout, null);


            }

                //naslov
                TextView naslov = convertView.findViewById(R.id.textView21);
                naslov.setText(contactListItems.getNaslov());

                //lokacija
                TextView lokacija = convertView.findViewById(R.id.textView24);
                lokacija.setText("Lokacija:"+contactListItems.getLokacija());

                //povrsina
                TextView povrsina = convertView.findViewById(R.id.textView23);
                povrsina.setText("Površina:"+contactListItems.getPovrsina()+"m2");


                TextView cijena = convertView.findViewById(R.id.textView22);
                cijena.setText("Cijena: "+contactListItems.getCijena()+"kn");

                 ImageView imageView = convertView.findViewById(R.id.imageView);
                 imageView.setImageResource(R.drawable.kuca);








            Button upit=convertView.findViewById(R.id.button12);
                upit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id1=contactListItems.getID();
                        String mail=contactListItems.getEmail();
                        Intent j=new Intent(Main2Activity.this,Upit.class);


                        j.putExtra("id1",position+1);
                        startActivity(j);



                    }
                });
                vise=convertView.findViewById(R.id.button13);
            vise=convertView.findViewById(R.id.button13);
            vise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=contactListItems.getID();
                    String mail=contactListItems.getEmail();
                    Intent i=new Intent(Main2Activity.this,Vise.class);


                    i.putExtra("id",position+1);
                    i.putExtra("mail",mail);



                    startActivity(i);
                }
            });




                //imageView.setImageResource(images[position]);
                //textView.setText(proizvodi[position]);


                return convertView;

            }

        }







    }











