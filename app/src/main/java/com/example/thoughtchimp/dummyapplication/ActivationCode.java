package com.example.thoughtchimp.dummyapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Created by thoughtchimp on 7/23/2016.
 */
public class ActivationCode extends AppCompatActivity implements  Constant {
    ArrayList activationdetials;
    String[] code;
    final String url = ACTIVATIONIP;
    Multiplerow madapter;
    ListView listview;
    String[] milestone;
    String[] validfrom;
    JSONArray activation = null;
    String childname;
    InputStream is=null;
    // ArrayList<Milestonedetails> miledetails;
    private List<MultipleRowModel> multipleRowModelList = new ArrayList<>();
    static JSONObject jObj = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activationcoderecycler);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        RequestQueue queue = Volley.newRequestQueue(this);
        String body="";

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("X-API-KEY","123456");
            httpget.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
            httpget.addHeader("access-token","V49wH0yUXBQZuPMfshEqWgxbY_4");

            HttpResponse httpResponse = httpClient.execute(httpget);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);

            }
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            body = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            activation = new JSONArray(body);
            System.out.println("---------------------------"+activation);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        try {

                            validfrom = new String[activation.length()];
                            code = new String[activation.length()];
                            milestone = new String[activation.length()];

                            for (int i = 0; i < activation.length(); i++) {
                                JSONObject activecode = activation.getJSONObject(i);
                                code[i] = activecode.getString("code");
                                milestone[i] = activecode.getString("milestone");
                                validfrom[i] = activecode.getString("valid_from");
//                                String s= String.valueOf(milestone);
                                JSONObject child=activecode.getJSONObject("child");
                                String firstname = child.getString("firstname");
                                String lastname=child.getString("lastname");
                                childname=firstname.concat(lastname);

//                                String s=  Arrays.toString(milestone);
                                System.out.println("hjhjhhjkhhhhhhhhh" + validfrom[i] + code[i]+ childname+milestone[i]);
                                System.out.println("-----------dssddss");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


      fillAdapter();

        madapter = new Multiplerow(ActivationCode.this, multipleRowModelList);

        recyclerview.setAdapter(madapter);

    }
    private void fillAdapter() {
        String[] content=code;
        String [] code1=validfrom;

        int type=0;
        int i;
        for(String s:milestone) {
            System.out.println("----------------"+s);

            if (s != null) {
                    if (s.equals("milestone 1")) {
                        System.out.println("--------------" + s.equals("milestone 1"));
                        type = Actionconstant.Milestone1;

                    } else if (s.equals("milestone 2")) {
                    System.out.println("--------------" + s.equals("milestone 2"));
                        type = Actionconstant.Milestone2;

                    } else if (s.equals("milestone 3")) {
                        type = Actionconstant.Milestone3;
                    }
                }
                multipleRowModelList.add(new MultipleRowModel(type,content,code1));
            }


//        }

    }

}

