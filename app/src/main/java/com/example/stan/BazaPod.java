package com.example.stan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BazaPod extends SQLiteOpenHelper {

    //Tablica za registraciju
    public static final String TABLE_NAME="registracija";
    public static final String COL_1="_id";
    public static final String COL_2="Ime";
    public static final String COL_3="Prezime";
    public static final String COL_4="EmailK";
    public static final String COL_6="Username";
    public static final String COL_7="Password";
    public static final String COL_8="BrojMob";

    //Tablica za izanmljivanje
    public static final String TABLE_NAME1="iznastanovi";
    public static final String TOL_1="_id1";
    public static final String TOL_2="Naslov";
    public static final String TOL_3="Lokacija";
    public static final String TOL_5="Povrsina";
    public static final String TOL_6="Cijena";
    public static final String TOL_7="Opis";
    public static final String TOL_8="TipProdaje";
    public static final String TOL_9="Email";

    //Tablica za poruku
    public static final String TABLE_NAME2="poruka";
    public static final String KOL_1="_id2";
    public static final String KOL_2="EmailPosiljatelja";
    public static final String KOL_3="Subjekt";
    public static final String KOL_4="Poruka";
    public static final String KOL_5="EmailPrimatelja";


    SQLiteDatabase database;

    public BazaPod(Context context) {
        super(context, "Zgrade.db",null, 3);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //stvaranje dviju tablica
        db.execSQL("CREATE TABLE " + TABLE_NAME+ " ( "+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+ COL_4+" TEXT ,"+COL_6+" TEXT ,"+COL_7+" TEXT,"+COL_8+" TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME1+ " ( "+TOL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TOL_2+" TEXT,"+TOL_3+" TEXT, "+TOL_5+" TEXT,"+TOL_6+" TEXT,"+TOL_7+" TEXT,"+TOL_8+" TEXT,"+TOL_9+" TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME2+ " ( "+KOL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KOL_2+" TEXT, "+KOL_3+" TEXT, "+ KOL_4+" TEXT , "+ KOL_5+ " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Brisanje tablica ako postoje
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.close();

    }
    public void insertData(String naslov, String lokacija, String povrsina, String cijena, String opis,String tipProdaje,String mail) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BazaPod.TOL_2, naslov);
        contentValues.put(BazaPod.TOL_3, lokacija);
        contentValues.put(BazaPod.TOL_5, povrsina);
        contentValues.put(BazaPod.TOL_6, cijena);
        contentValues.put(BazaPod.TOL_7, opis);
        contentValues.put(BazaPod.TOL_8, tipProdaje);
        contentValues.put(BazaPod.TOL_9, mail);
        long id = db.insert(TABLE_NAME1, null, contentValues);


    }
    public void insertDataPoruka(String mail, String subjekt, String poruka,String mailPrimatelja) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BazaPod.KOL_2, mail);
        contentValues.put(BazaPod.KOL_3, subjekt);
        contentValues.put(BazaPod.KOL_4, poruka);
        contentValues.put(BazaPod.KOL_5, mailPrimatelja);
        long id = db.insert(TABLE_NAME2, null, contentValues);


    }
    public Cursor usporediEmail() {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME2 +" INNER JOIN " +TABLE_NAME + " ON " + KOL_5 + " = " + COL_4+ " WHERE "+ KOL_1 +" =" + COL_1;
        Cursor cursor= db.rawQuery(query,null);
        return cursor;



    }



    public Cursor getList(){
        SQLiteDatabase novo=this.getWritableDatabase();
        Cursor data2=novo.rawQuery("SELECT *  FROM "+TABLE_NAME1,null);
        return data2;
    }



    //Metoda za ispis podataka iz tablice TABLE_NAME1
    public Cursor viewData(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 +" INNER JOIN " +TABLE_NAME + " ON " + TOL_1 + " = " + COL_1;
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }
    public Cursor viewData2(){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME1 ;
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }



    //Provjera da li email postoji  vec u bazi
    public Boolean provjeMail (String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from " + TABLE_NAME +" where EmailK=? ",new String[]{email});
        if(cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }
    //Provjera da li username postoji  vec u bazi
    public Boolean provjeriUsername (String username){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from registracija where Username=? ",new String[]{username});
        if(cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }
}