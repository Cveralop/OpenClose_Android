package com.example.openclose2;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ResponseCache;

public class SessionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;

    EditText cajaUser, cajaPass;
    Button btnConsultar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.session_fragment, container, false);

        View vista = inflater.inflate(R.layout.session_fragment, container, false);
        cajaUser =(EditText) vista.findViewById(R.id.txtUser);
        cajaPass =(EditText) vista.findViewById(R.id.txtPass);
        btnConsultar = (Button) vista.findViewById(R.id.btningres);
        rq = Volley.newRequestQueue(getContext());

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        User usuario = new User();
        Toast.makeText(getContext(), "Inicio de session" + cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setUser(jsonObject.optString("pwd"));
            usuario.setUser(jsonObject.optString("name"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void iniciarSesion()
    {

        String url ="http://192.168.0.26/Login/sesion.php?user="+cajaUser.getText().toString()+
                "&pwd="+cajaPass.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url,null, this, this);

        rq.add(jrq);

    }
}
