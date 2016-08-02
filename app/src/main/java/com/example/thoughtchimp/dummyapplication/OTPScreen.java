package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class OTPScreen extends Activity implements Constant {
    String Url=OTPsend;
    String meessage;
    Context ctx;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpcode);
        final Button Otplogin = (Button) findViewById(R.id.otplog_btn);
        Otplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("------checking1"+meessage);

                final RequestQueue queue = Volley.newRequestQueue(OTPScreen.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println("------------------" + response);
                                try {
                                    JSONObject object = new JSONObject(response);
                                   meessage = object.getString("message");
                                    System.out.println("------2"+meessage);

                                    Intent in=new Intent(OTPScreen.this,SessionArchive.class);
                                    startActivity(in);
                                    Toast.makeText(getApplicationContext(),"Login Succesfull",Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> httpget = new HashMap<>();
                        httpget.put("X-API-KEY", "123456");
                        return httpget;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        if (response.headers == null) {
                            // cant just set a new empty map because the member is final.
                            response = new NetworkResponse(
                                    response.statusCode,
                                    response.data,
                                    Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                                    response.notModified,
                                    response.networkTimeMs);
                        }

                        return super.parseNetworkResponse(response);
                    }
                };
                System.out.println("------checking3"+meessage);

                queue.add(stringRequest);





            }
        });
    }
}
