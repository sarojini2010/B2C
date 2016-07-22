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

import com.android.volley.RequestQueue;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements  Constant{
    Toolbar toolbar;
    TextView SessionText,ReccoText,SessionArcText;
    ProgressBar progress;
    Button btn_score;
    private ArrayList<User> userlist=new ArrayList<>();
    ArrayList SessionTitle;
    static JSONObject jObj = null;
    final String url =CHILDHOMEIP;
    InputStream is=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        SessionText= (TextView) findViewById(R.id.session_text);
        ReccoText=(TextView) findViewById(R.id.recco_text);
        SessionArcText= (TextView) findViewById(R.id.archieve_text);
        progress= (ProgressBar) findViewById(R.id.progressBar);
        btn_score=(Button)findViewById(R.id.button_score);
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


        String body="" ;

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
            jObj = new JSONObject(body);
            System.out.println("---------------------------"+jObj);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        try {
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
               // SessionTitle.add(jsonArray.get(i).toString());

//                System.out.println("-----------"+title +""+SessionTitle+""+created);
            }
//            SessionArcText.setText(Session_archieve+" Session Archieve");
            ReccoText.setText(Recco);
            progress.setMax(50);
            progress.setProgress(sesion);
            btn_score.setText(Karmascore);
            SessionText.setText(""+sesion +" Sessions Completed");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAllUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AllUsersAdapter allUserAdapter = new AllUsersAdapter(this, userlist);
//       recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.INVALID_OFFSET));
        recyclerView.setAdapter(allUserAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,SessionArchieve.class);
                startActivity(in);
            }
        });
       SessionArcText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent in=new Intent(MainActivity.this,SessionArchieve.class);
               startActivity(in);
           }
       });
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