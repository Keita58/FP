package com.example.listview_niko;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactes");
        this.onCreate(db);
    }
}
