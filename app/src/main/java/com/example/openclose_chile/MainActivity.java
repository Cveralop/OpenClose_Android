package com.example.openclose_chile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
// ventana de permiso telefono para la aplicación
    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;

    TextView nombre;
    RequestQueue cola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnHuella = (Button)findViewById(R.id.btnHuella);
        Button btnAcceder = (Button)findViewById(R.id.btnacceder);

        final EditText edUser = (EditText)findViewById(R.id.eduser);
        final EditText edClave = (EditText)findViewById(R.id.edclave);

//ingreso a la ventana que logea por huella digital
        btnHuella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(MainActivity.this, Activity_huella.class);
                MainActivity.this.startActivity(e);
            }
        });
//metodo para ingreso de login por usuario y clave
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = edUser.getText().toString();
                final String clave = edClave.getText().toString();

                final Response.Listener<String> respuesta = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //respuesta de la base de datos hacia el ingreso de los datos ingresados
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok==true){
                                String user = jsonRespuesta.getString("idUsuario");
                                String clave = jsonRespuesta.getString("clave");
                                String nombre = jsonRespuesta.getString("nombre");

                                Intent i = new Intent(MainActivity.this, Pantalla_principal.class);
                                // i.putExtra("idUsuario",user);
                                i.putExtra("nombre",nombre);
                                //i.putExtra("apellido",apellido);

                                MainActivity.this.startActivity(i);


                            }else{
                                //ventana de alerta cuando los datos estan mal ingresados para el login

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
                cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);

                int max = 5;
                int min = 1;
                int range = max - min + 1;
                int rand = (int)(Math.random() * range) + min;

                switch (rand){
                    case 1:
                        Toast.makeText(getApplicationContext(),"Revisa tu alrededor antes de ingresar",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"Verifica si tu entorno esta iluminado",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"No lleves cosas de valor a la vista",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"Si ves una situación extraña, llama inmediatamente a seguridad",Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(),"En caso de incidente NO opongas resistencia, tu vida vale más.",Toast.LENGTH_LONG).show();
                        break;
                }



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
//metodo para solicitar los permisos del telefono
    private void solicitarPermiso()
    {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, SOLICITUD_PERMISO_CALL_PHONE);

        Toast.makeText(this, "Solicitar Permiso ", Toast.LENGTH_SHORT);


    }

}
