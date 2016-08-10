package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ChildDatabase;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ParentDatabase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class OTPScreen extends Activity implements Constant {
    String Url=OTPsend;
    String meessage;
    TextView requestagain;
    Context ctx;
    ChildDatabase childDatabase;
    ParentDatabase parentDatabase;
    String childid;
    String Urlotp;
    EditText phonenumber;
    String phone;
    int status;
    InputStream is=null;
    String body="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final Button Otplogin = (Button) findViewById(R.id.login_btn);
        phonenumber = (EditText) findViewById(R.id.phone_edit);
        requestagain = (TextView) findViewById(R.id.request);
        childDatabase = new ChildDatabase(this);
        parentDatabase = new ParentDatabase(this);

        System.out.println("phonenumber" + phone);


        Otplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("------checking1"+meessage);
                phone = phonenumber.getText().toString();
                Urlotp = Url + phone;
                System.out.println("otp login" + Urlotp);
                final RequestQueue queue = Volley.newRequestQueue(OTPScreen.this);

//                try {
//                    DefaultHttpClient httpClient = new DefaultHttpClient();
//                    HttpGet httpget = new HttpGet(Urlotp);
//                    httpget.addHeader("X-API-KEY", "123456");
//
//
//                    HttpResponse httpResponse = httpClient.execute(httpget);
//                    final int statusCode = httpResponse.getStatusLine().getStatusCode();
//
//                    if (statusCode != HttpStatus.SC_OK) {
//                        Log.w(getClass().getSimpleName(),
//                                "Error " + statusCode + " for URL " + Urlotp);
//
//                    }
//                    HttpEntity httpEntity = httpResponse.getEntity();
//                    is = httpEntity.getContent();
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(
//                            is, "iso-8859-1"), 8);
//                    StringBuilder sb = new StringBuilder();
//                    String line = null;
//                    while ((line = reader.readLine()) != null) {
//                        sb.append(line + "n");
//                    }
//                    is.close();
//                    body = sb.toString();
//                } catch (Exception e) {
//                    Log.e("Buffer Error", "Error converting result " + e.toString());
//                    System.out.println("response"+body);
//                }
//
//
//            }
//
//        });
//    }




                StringRequest stringRequest = new StringRequest(Request.Method.GET, Urlotp,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println("------------------" + response);

//                                try {
//                                    JSONObject object = new JSONObject(response);
//                                   meessage = object.getString("message");
                                System.out.println("------2" + meessage);
//                                if (status == 200) {
//                                    try {
//                                        JSONObject objects = new JSONObject(response);
//                                        JSONObject parent = objects.getJSONObject("parent");
//                                        String fullname = parent.getString("fullname");
//                                        String parentid = parent.getString("id");
//                                        String emaild = parent.getString("email");
//                                        String mobilenumber = parent.getString("mobile");
//                                        String profileimage = parent.getString("profile_image");
//                                        System.out.println("===================childdetails" + fullname + profileimage);
//                                        parentDatabase.insertparentdata(parentid, fullname, emaild, mobilenumber, profileimage);
//                                        JSONArray childimage = objects.getJSONArray("childs");
//
//                                        for (int i = 0; i < childimage.length(); i++) {
//                                            JSONObject names = childimage.getJSONObject(i);
//                                            childid = names.getString("id");
//                                            String childname = names.getString("fullname");
//                                            String profilechildimage = names.getString("child_image");
//                                            System.out.println("---------childprofile" + childname + profilechildimage + childid);
//                                            if ((childDatabase.getData(childid)) != null) {
//                                                System.out.println("databsessssssss1");
//                                                childDatabase.insertchilddata(childid, childname, "", profilechildimage);
//                                            } else {
//                                                System.out.println("databsessssssss2");
//                                                childDatabase.updatechilddata(childname, childid, "", profilechildimage);
//                                            }
//
//                                            System.out.println("----------------" + childDatabase.getData("100"));



//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }

                                    Intent in = new Intent(OTPScreen.this, LoginPage.class);
                                     in.putExtra("phonenumber",phone);
                                    startActivity(in);
                                    Toast.makeText(getApplicationContext(), "Login Succesfull", Toast.LENGTH_LONG).show();
//                                }
//                                else {
//                                    Toast.makeText(getApplicationContext(),"check your internet",Toast.LENGTH_LONG).show();
//                                }

//                                }
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
                             status=response.statusCode;
                            System.out.println("status code"+status);
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

                queue.add(stringRequest);





            }
        });
////        requestagain.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent in=new Intent(getApplicationContext(),LoginPage.class);
////                startActivity(in);
////            }
////        });
    }
}
