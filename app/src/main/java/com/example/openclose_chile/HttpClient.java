package com.example.openclose_chile;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

package com.mysite.myapp;

public class HttpClient {
    public void doRrequest(String status) {
        initClient();
        Log.d("AA", "Making request..["+status+"]");
        Request req = new Request.Builder().url(URL).post(RequestBody.create(JSON, createJSON(status))).build();

        Object client;
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("AA", "resp [" + response.body().string() + "]");
            }
        });
    }

    private void initClient() {
    }

}
