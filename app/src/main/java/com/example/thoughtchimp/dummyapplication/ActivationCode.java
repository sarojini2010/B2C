package com.example.thoughtchimp.dummyapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/23/2016.
 */
public class ActivationCode extends AppCompatActivity implements  Constant {
    ArrayList activationdetials;
    String code;
    final String url =ACTIVATIONIP;
    ListView listview;
    ArrayList<Milestonedetails> miledetails;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activationcode);
        listview = (ListView) findViewById(R.id.listview);

//        final Milestone[] items = new Milestone[40];
//        Activationlistadapter customAdapter = new Activationlistadapter(this, R.id.text, items);
//        listview.setAdapter(customAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        final Milestone[] items = new Milestone[3];
        for (int i = 0; i < items.length; i++) {
            if (i == 1) {
                items[i] = new Milestone("Milestone " + i, Activationdapter.Milestone1);
            } else if (i == 2) {
                items[i] = new Milestone("Milestone " + i, Activationdapter.Milestone2);
            } else
                items[i] = new Milestone("Milestone " + i, Activationdapter.Milestone3);
            }

        final Activationdapter mAdapter = new Activationdapter(this, R.id.textView3,items );
         listview.setAdapter(mAdapter);

//

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------"+response);
                        try {
                            JSONArray activation=new JSONArray(response);
                            for(int i=0;i<activation.length();i++){
                                JSONObject activecode=activation.getJSONObject(i);
                                code=activecode.getString("code");
                                int milestone=activecode.getInt("milestone");
                                String validfrom=activecode.getString("valid_from");
                                String child=activecode.getString("child");
                                final Milestone[] items = new Milestone[3];
//                                Milestonedetails milestone1=new Milestonedetails();
//                                milestone1.setActivationcode(code);
//                                milestone1.setValiddate(validfrom);
//                                milestone1.setMilestone(""+milestone);
//                                miledetails.add(milestone1);
//                                for(int j=0;j<milestone;j++){
//                                    if(j==1){
//                                        items[j] = new Milestone("Milestone " + i, Activationdapter.Milestone1);                                    }
//                                   else if (j == 1) {
//                                items[j] = new Milestone("Milestone " + i, Activationdapter.Milestone2);
//                            } else
//                                items[j] = new Milestone("Milestone " + i, Activationdapter.Milestone3);
//                        }

                            mAdapter.notifyDataSetChanged();
                            System.out.println("-----------------"+code+milestone+validfrom);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> httpget =   new HashMap<>();
                httpget.put("X-API-KEY","123456");
                httpget.put("Authorization","Basic YWRtaW46MTIzNA==");
                httpget.put("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");
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

        queue.add(stringRequest);
//
//



    }
}