package com.example.thoughtchimp.dummyapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/22/2016.
 */
public class SessionArchive  extends AppCompatActivity implements Constant {
    private ArrayList<Session> sessionlist=new ArrayList<>();
    String url = SessionArchieveIp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sesionarc);

        RequestQueue queue = Volley.newRequestQueue(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.session);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final SessionAdapter sessionAdapter = new SessionAdapter(this, sessionlist);
        recyclerView.setAdapter(sessionAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------"+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String undo=jsonObject.getString("undo_session_id");
                            String undoid;
                            JSONArray sesionarray=jsonObject.getJSONArray("sessions");
                            for(int i=0;i<sesionarray.length();i++) {
                                JSONObject sessionsid = sesionarray.getJSONObject(i);
                                String id=sessionsid.getString("id");
                                String title=sessionsid.getString("title");
                                String parentnote=sessionsid.getString("parent_note");
                                String milestoneid=sessionsid.getString("milestone_id");
                                Session sessiondetails=new Session();
                                System.out.println("-------------"+id+undo);
                                if(undo.equals(id))
                                    sessiondetails.setUndosessionid("undo");
                                sessiondetails.setSessionid(id);
                                sessiondetails.setSessionnumber(title);
                                sessiondetails.setImageResource(R.drawable.arrow_hdpi);
                                sessiondetails.setSessiondetails(parentnote);
                                sessionlist.add(sessiondetails);
                                Collections.reverse(sessionlist);
                                System.out.println("sssss"+id+""+title+""+parentnote+""+milestoneid+""+sessiondetails+"");
                                sessionAdapter.notifyDataSetChanged();
//                                System.out.println("userrrrrrr"+userlist);
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
                //..add other headers
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



    }
}
