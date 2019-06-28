package com.example.open_close;

/**
 * Created by Claudio Vera y Jorge Caro INACAP 2019.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnHuella = (Button)findViewById(R.id.btnHuella);
        Button btnAcceder = (Button)findViewById(R.id.btnacceder);
        final EditText edUser = (EditText)findViewById(R.id.eduser);
        final EditText edClave = (EditText)findViewById(R.id.edclave);

        btnHuella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(MainActivity.this, Activity_huella.class);
                MainActivity.this.startActivity(e);
            }
        });

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = edUser.getText().toString();
                final String clave = edClave.getText().toString();
                final Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok==true){
                                String user = jsonRespuesta.getString("idUsuario");
                                //String clave = jsonRespuesta.getString("clave");
                                String nombre = jsonRespuesta.getString("nombre");
                                String apellido = jsonRespuesta.getString("apellido");

                               // if(usert.equals(userj)&clavet.equals(clavej)){
                                    Intent i = new Intent(MainActivity.this, Pantalla_principal.class);
                                   // i.putExtra("idUsuario",user);
                                    i.putExtra("nombre",nombre);
                                    i.putExtra("apellido",apellido);

                                    MainActivity.this.startActivity(i);

                                //}else{
                                  //  Toast.makeText(null,"no valido",Toast.LENGTH_LONG).show();
                                //}





                            }else{
                                AlertDialog.Builder alerta= new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage("Fallo del Login").setNegativeButton("Reintentar", null).create().show();

                            }


                        } catch (JSONException e) {
                           e.getMessage();

                        }
                    }
                };
                LoginRequest r = null;
                try {
                    r = new LoginRequest(user,clave,respuesta);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                RequestQueue cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);



            }
        });



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT);

        }
        else
        {
            solicitarPermiso();
        }

    }

    private void solicitarPermiso()
    {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, SOLICITUD_PERMISO_CALL_PHONE);

            Toast.makeText(this, "Solicitar Permiso ", Toast.LENGTH_SHORT);



    }
}
