package com.example.claudio.sqllite;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtEdad;
    Button btnGuardar;
    Button btnListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre=(EditText)findViewById(R.id.edNombre);
        edtEdad=(EditText)findViewById(R.id.edEdad);
        btnGuardar=(Button)findViewById(R.id.btnIngresar);
        btnListar=(Button)findViewById(R.id.btnListar);

        btnListar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                    }
            });


    }

    public void clkGuardar (View v){
        Guardar(edtNombre.getText().toString(),edtEdad.getText().toString());
    }

    private  void Guardar(String nombre, String edad){
        BaseHelper helper=new BaseHelper(this, "MiBase",null,1);
        SQLiteDatabase db=helper.getWritableDatabase(); //abrir conexion a base de datos

        try{
            ContentValues cv = new ContentValues();
            cv.put("nombre",nombre);
            cv.put("edad",edad);
            db.insert("PERSONA",null,cv);
            db.close(); // Cerrar conexion a base de datos
            Toast.makeText(this,"Conexion Ok",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Error de conexion",Toast.LENGTH_SHORT).show();
        }
    }
}
