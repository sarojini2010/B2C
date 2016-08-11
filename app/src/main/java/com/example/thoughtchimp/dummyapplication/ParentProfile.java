package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ParentDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thoughtchimp on 8/5/2016.
 */
public class ParentProfile extends ActionBarActivity implements Constant{

    static String parenturl=PARENTIP;
    static TextView parentname;
    static TextView emailid;
    static TextView phone;
    Toolbar toolbar;
    static String text;
    SharedPreferences sharedPreferences;
    static SharedPreferences.Editor edit;
    ImageView parentback,addparent;
    static ParentDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentprofiles);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        parentname= (TextView) findViewById(R.id.textView_parent_name);
        emailid=(TextView) findViewById(R.id.textView_parent_email);
        phone=(TextView) findViewById(R.id.parentphonenum);
        toolbar= (Toolbar) findViewById(R.id.parenttoolbar);
        setSupportActionBar(toolbar);
        db=new ParentDatabase(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        parentback= (ImageView) findViewById(R.id.parentback);
        addparent= (ImageView) findViewById(R.id.addparent);
        parentback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in=new Intent(ParentProfile.this,MainProfile.class);
//                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(in);
            }
        });
        addparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddetails();
            }
        });
        sharedPreferences=getSharedPreferences("Parenprofile",MODE_PRIVATE);
        edit=sharedPreferences.edit();
//        makePostRequest();



    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.parentmenu, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.parentadd:
//                adddetails();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
    public void adddetails() {
        String name = parentname.getText().toString();
//        String date=dateView.getText().toString();
//        String classes=classname.getText().toString();
//        String school=schoolname.getText().toString();
//        String intersted=Interest.getText().toString();
//        String genders=item.toString();
        makePostRequest();
//        Intent in = new Intent(getApplicationContext(), MainProfile.class);
//        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(in);


    }
    public static void makePostRequest() {

        String name=parentname.getText().toString();
        String email=emailid.getText().toString();
        String phones=phone.getText().toString();
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(parenturl);
        httpPost.addHeader("X-API-KEY","123456");
        httpPost.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
        httpPost.addHeader("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");

        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("fullname",name));
        nameValuePair.add(new BasicNameValuePair("emailid",email));
        nameValuePair.add(new BasicNameValuePair("profile_image",phones));



//        SavePreferences("childname",name);
//        storeRecord("childs",name);
////        editTor.putString("birthdate",dateView.getText().toString());
//        editTor.commit();

        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder builder = new StringBuilder();
            String str = "";

            while ((str = rd.readLine()) != null) {
                builder.append(str);
            }

                text= builder.toString();
//            editTor.putString("result",text);
//            editTor.commit();
            // write response to log
            Log.d("Http Post Response:", text.toString());


            // write response to log

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
        try {
            JSONObject object=new JSONObject(text);
            String profileimage=object.getString("profile_image");
            String names=object.getString("fullname");
//            String emailid=object.getString("");
//            db.insertparentdata(names);
            System.out.println("--------pRofilephoto"+profileimage);

            edit.putString("ParentName",names);
            edit.putString("Profileimage",profileimage);
            edit.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
