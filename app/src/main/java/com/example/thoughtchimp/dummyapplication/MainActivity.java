package com.example.thoughtchimp.dummyapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements  Constant{
    TextView SessionText,ReccoText,SessionArcText;
    ProgressBar progress;
    Button btn_score;
    private ArrayList<User> userlist=new ArrayList<>();
    final String url =CHILDHOMEIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        SessionText= (TextView) findViewById(R.id.session_text);
        ReccoText=(TextView) findViewById(R.id.recco_text);
        SessionArcText= (TextView) findViewById(R.id.archieve_text);
        progress= (ProgressBar) findViewById(R.id.progressBar);
        btn_score=(Button)findViewById(R.id.button_score);
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAllUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final AllUsersAdapter allUserAdapter = new AllUsersAdapter(this, userlist);
//       recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.INVALID_OFFSET));
        recyclerView.setAdapter(allUserAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,SessionArchive.class);
                startActivity(in);
            }
        });
       SessionArcText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in=new Intent(MainActivity.this,SessionArchive.class);
               startActivity(in);
           }
       });


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------"+response);
                        try {
                            JSONObject jObj=new JSONObject(response);
                            int sesion=jObj.getInt("session_completed");
                            String Karmascore=jObj.getString("karma_score");
                            String Recco=jObj.getString("recco");
                            String Session_archieve=jObj.getString("session_total");
                            JSONArray jsonArray=jObj.getJSONArray("sessions");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject id=jsonArray.getJSONObject(i);
                                User user=new User();
                                String title1=id.getString("id");
                //                String title2=title1.substring(7);
                //                System.out.println("PPPPPPPPPPP"+title2);
                //                String title3=title1;
                //                System.out.println("____________"+title3);
                                user.setUserEmail(title1);
                                user.setUserName(id.optString("title"));
                                user.setUserMobile(id.optString("parent_note"));
                                user.setImageResourceId(R.drawable.arrow_hdpi);
                                userlist.add(user);
                                allUserAdapter.notifyDataSetChanged();
                            }
                            ReccoText.setText(Recco);
                            progress.setMax(50);
                            progress.setProgress(sesion);
                            btn_score.setText(Karmascore);
                            SessionText.setText(+sesion +" Sessions Completed");
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
                Map<String,String> httpget = new HashMap<>();
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.manu, menu);//Menu Resource, Menu
//        return true;
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item1:
//                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



}
