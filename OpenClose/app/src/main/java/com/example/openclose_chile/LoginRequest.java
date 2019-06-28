package com.example.openclose_chile;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static String ruta ="http://openclose.raion.cl/usuario.php";
    private Map<String, String> parametros;
    public LoginRequest(String idUsuario, String clave, Response.Listener<String> listener) throws ParseException {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("idUsuario", idUsuario);
        parametros.put("clave", clave);

    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}
