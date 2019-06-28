package com.example.claudio.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity { //leer datos de una base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cargar();
    }

    public  void cargar(){
        BaseHelper baseHelper=new BaseHelper(this,"MiBase",null,1);
        SQLiteDatabase db=baseHelper.getReadableDatabase();
        if(db!=null){
            Cursor c =db.rawQuery("select * from PERSONA",null);
            int cantidad=c.getCount();
            int i=0;
            String[] arreglo=new String[cantidad];
            if(c.moveToFirst()){
                do{
                    String linea = c.getString(0)+" "+ c.getString(1);
                    arreglo[i]=linea;
                    i++;
                }while (c.moveToNext());
            }
            db.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arreglo);
            ListView listado=(ListView)findViewById(R.id.listado);
            listado.setAdapter(adapter);
        }
    }
}
