package com.example.listview_niko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDades extends SQLiteOpenHelper {

    String creacioTaula = "CREATE TABLE contactes(id integer primary key autoincrement, nom text not null, " +
            "cognom text not null, telefon integer not null, adreca text default '', mail text default '', " +
            "dataNaixement text default '')";

    public BaseDeDades(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creacioTaula);
        db.execSQL("INSERT INTO contactes (nom, cognom, telefon, adreca, mail, dataNaixement) VALUES " +
                "('Marc', 'Sánchez López', 622260896, 'Grugliasco, 61', 'a', '26/08/2000')");
        db.execSQL("INSERT INTO contactes (nom, cognom, telefon, adreca, mail, dataNaixement) VALUES " +
                "('Laia', 'Massana Manzanares', 600000000, 'Grugliasco, 61', 'a', '19/04/2001')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactes");
        this.onCreate(db);
    }

    public ArrayList<Titular> selectBD() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Titular> aux = new ArrayList<>();
        Cursor c1 = db.rawQuery("Select id, nom, cognom, telefon, adreca, mail, dataNaixement from contactes", null);
        if(c1 != null && c1.moveToFirst()) {
            do {
                int id = c1.getInt(0);
                String nom = c1.getString(1);
                String cognom = c1.getString(2);
                int telefon = c1.getInt(3);
                String adreca = c1.getString(4);
                String mail = c1.getString(5);
                String dataNaixement = c1.getString(6);
                Titular con = new Titular(id, nom, cognom, telefon, adreca, mail, dataNaixement);
                aux.add(con);
            } while (c1.moveToNext());
        }
        db.close();
        Log.i("BD", ""+aux);
        return aux;
    }

    public int selectBDEspecific(Titular t) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = 0;
        Cursor c1 = db.rawQuery("Select id from contactes where nom = ? and cognom = ? and adreca = ? and mail = ?", new String[]{t.getNom(), t.getCognom(), t.getAdreca(), t.getMail()});
        if(c1 != null && c1.moveToFirst()) {
            id = c1.getInt(0);
        }
        db.close();
        return id;
    }

    public void crearContacteBD(Titular aux) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO contactes (nom, cognom, telefon, adreca, mail, dataNaixement) VALUES " +
                "('"+aux.getNom()+"', '"+aux.getCognom()+"', "+aux.getTelefon()+", '"+aux.getAdreca()+"', '"+aux.getMail()+"', '"+aux.getDataNaixement()+"')");
        db.close();
    }


    public void eliminaContacteBD(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM contactes WHERE id = "+id+"");
        db.close();
    }

    public void modificaContacteBD(int id, Titular aux) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valors = new ContentValues();
        valors.put("nom", aux.getNom());
        valors.put("cognom", aux.getCognom());
        valors.put("telefon", aux.getTelefon());
        valors.put("adreca", aux.getAdreca());
        valors.put("mail", aux.getMail());
        valors.put("dataNaixement", aux.getDataNaixement());
        db.update("contactes", valors, "id = "+id+"", null);
        db.close();
    }

    public boolean creaPredefinits(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM contactes WHERE id = "+id+"", null);
        if(c != null && c.moveToFirst())
            return true;
        else
            return false;
    }
}
