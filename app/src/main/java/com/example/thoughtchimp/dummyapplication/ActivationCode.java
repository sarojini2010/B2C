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
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.Activationdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
    String[] validfrom;
    JSONArray activation = null;
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
//        String response = "[\n" +
//                "  {\n" +
//                "    \"code\": \"twertfvg4\",\n" +
//                "    \"child\": {\n" +
//                "      \"child_id\": \"11\",\n" +
//                "      \"firstname\": \"karan\",\n" +
//                "      \"lastname\": \"sharma\",\n" +
//                "      \"child_image\": null\n" +
//                "    },\n" +
//                "    \"milestone\": \"milestone 1\",\n" +
//                "    \"valid_from\": \"2016-07-13 09:12:18\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"code\": \"dsgdfgh\",\n" +
//                "    \"child\": {\n" +
//                "      \"child_id\": \"12\",\n" +
//                "      \"firstname\": \"punam\",\n" +
//                "      \"lastname\": \"pandey\",\n" +
//                "      \"child_image\": \"12_1468932052172.jpeg\"\n" +
//                "    },\n" +
//                "    \"milestone\": \"milestone 3\",\n" +
//                "    \"valid_from\": \"2016-07-13 09:12:18\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"code\": \"t5674jhg\",\n" +
//                "    \"child\": null,\n" +
//                "    \"milestone\": \"milestone 3\",\n" +
//                "    \"valid_from\": \"2016-07-13 09:12:18\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"code\": \"gfdgfshg4\",\n" +
//                "    \"child\": null,\n" +
//                "    \"milestone\": \"milestone 2\",\n" +
//                "    \"valid_from\": \"2016-07-13 09:12:18\"\n" +
//                "  }\n" +
//                "]";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("X-API-KEY","123456");
            httpget.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
            httpget.addHeader("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");

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
                            for (int i = 0; i < activation.length(); i++) {
                                JSONObject activecode = activation.getJSONObject(i);
                                code[i] = activecode.getString("code");
                                int milestone = activecode.getInt("milestone");
                                validfrom[i] = activecode.getString("valid_from");
                                String child = activecode.getString("child");
                                System.out.println("hjhjhhjk" + validfrom + code);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


        fillAdapter();

        madapter = new Multiplerow(ActivationCode.this, multipleRowModelList);

        recyclerview.setAdapter(madapter);

    }
    private void fillAdapter() {



        String[] content=validfrom;
        String [] code1=code;
        int type=0;
        int i=0;
        for ( i = 0; i < activation.length(); i++) {

            if (i==1 ) {
                type = Actionconstant.Milestone1;

            } else if(i==2)  {
                type = Actionconstant.Milestone2;

            }else if(i==3) {
                type = Actionconstant.Milestone3;
            }
            multipleRowModelList.add(new MultipleRowModel(type , content,code1));
        }
    }

}

