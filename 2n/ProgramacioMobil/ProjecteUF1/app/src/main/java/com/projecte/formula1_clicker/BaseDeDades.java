package com.projecte.formula1_clicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDades extends SQLiteOpenHelper {

    String taula = "CREATE TABLE idUsuari(cadena text not null)";

    public BaseDeDades(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(taula);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void crearUsuari(String cadena) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO idUsuari(cadena) VALUES " + "('" + cadena + "')");
        db.close();
    }
}
