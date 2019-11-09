package com.example.openclose_chile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pantalla_principal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Button bnemergencia= (Button)findViewById(R.id.btnemergencia);
        final Button bnAbrir = (Button) findViewById(R.id.btnAbrir);
        final HttpClient client = new HttpClient();

        TextView tvNombre = (TextView)findViewById(R.id.txtnombre);
        TextView tvPatente = (TextView)findViewById(R.id.txtPatente);
        TextView tvDepto = (TextView)findViewById(R.id.txtDepto);

        bnemergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:942673648"));
                //ver si el permiso esta activado
                if (ActivityCompat.checkSelfPermission(Pantalla_principal.this, android.Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(i);

            }
        });

        bnAbrir.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                boolean status = false;
                client.doRrequest(status ? "1" : "0");
                    status = !status;
                    if (status)
                        bnAbrir.setBackgroundColor(R.color.holo_red_dark);
                    else
                        bnAbrir.setBackgroundColor(R.color.holo_green_light);


            }

        });


    }
}