package com.example.open_close;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;


class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;


    public FingerprintHandler(Context context) {

        this.context = context;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;

        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        this.update("Error de Huella Digital.", true);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        context.startActivity(new Intent(context, Pantalla_principal.class));
    }

    private void update(String e, boolean success) {
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);

    }


}
