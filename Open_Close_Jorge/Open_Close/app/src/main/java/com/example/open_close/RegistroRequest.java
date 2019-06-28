package com.example.open_close;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class RegistroRequest extends StringRequest{
    String[] args;
    DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    Date fecha = (Date) sdf.parse(args[2]);
    Time hora = new Time(fecha.getTime());

    private static String ruta ="http://openclose.raion.cl/usuario.php";
    private Map<String, String> parametros;
    public RegistroRequest(String fecha, String hora, String usu, Response.Listener<String> listener) throws ParseException {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("fecha", fecha + "");
        parametros.put("hora", hora + "");
        parametros.put("idUsuario", usu);

    }
        @Override
        protected Map<String, String> getParams(){
            return parametros;
        }


}
