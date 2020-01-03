package com.example.openclose_chile;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //consulta a la base de datos por los datos ingresados en el MainActivity

    private static String ruta ="https://www.openclosechile.cl/usuariolog.php";
    private Map<String, String> parametros;
    public LoginRequest(String idUsuario, String clave, Response.Listener<String> listener) throws ParseException {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();

        //covertir clave ingresada a MD5
        String claveConvertir = clave;
        String clave_md5 = md5(claveConvertir);

        parametros.put("idUsuario", idUsuario);
        parametros.put("password", clave_md5);

    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }

// Metodo para convertir clave a MD5
    public String md5(String s) {
        try {
            // Crea MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
