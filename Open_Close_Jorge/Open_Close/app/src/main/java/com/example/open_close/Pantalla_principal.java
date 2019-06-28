package com.example.open_close;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
    }
}