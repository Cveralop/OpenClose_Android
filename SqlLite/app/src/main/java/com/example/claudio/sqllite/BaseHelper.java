package com.example.claudio.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Claudio on 21-11-2017.
 */

public class BaseHelper extends SQLiteOpenHelper{
    String tabla ="CREATE TABLE PERSONA(NOMBRE TEXT, EDAD TEXT)";
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table persona");
        sqLiteDatabase.execSQL(tabla);

    }
}
