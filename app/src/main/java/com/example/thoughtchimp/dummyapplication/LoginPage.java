package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ChildDatabase;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ParentDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class LoginPage extends Activity implements Constant{
    SharedPreferences sharedPreferences,sharedPreferences1;
    EditText otpnumber;
    String URL=LOGINIP;
    static String otpnum;
    String text;
    static String firstchild;
    String childid;
    ChildDatabase childDatabase;
    ParentDatabase parentDatabase;
    SharedPreferences.Editor editor,editor1;
    Button login;
    String phone;
    String LoginUrl;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpcode);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        System.out.println("loginurl"+URL);

        sharedPreferences=getSharedPreferences(USER_SESSION_ID, MODE_PRIVATE);
        sharedPreferences1=getSharedPreferences("Childprofile3", MODE_PRIVATE);
        Bundle bnd=getIntent().getExtras();
        phone =bnd.getString("phonenumber");


        otpnumber= (EditText) findViewById(R.id.name_edits);
        login=(Button) findViewById(R.id.otplog_btn);
        childDatabase=new ChildDatabase(this);
        parentDatabase=new ParentDatabase(this);
        final String number=sharedPreferences.getString("Phonenumber",null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otpnum=otpnumber.getText().toString();
//                if(status==200)
                makePostRequest();
                System.out.println("url"+URL);
                Intent in=new Intent(getApplicationContext(),MainProfile.class);

                startActivity(in);
            }
        });
    }

    private void makePostRequest() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("X-API-KEY","123456");
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("mobile", phone));
        nameValuePair.add(new BasicNameValuePair("otp", otpnum));
        nameValuePair.add(new BasicNameValuePair("device_token","fdsfgsdgfd"));


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
            int status=response.getStatusLine().getStatusCode();
            System.out.println("statusssssss"+status);

            while ((str = rd.readLine()) != null) {
                builder.append(str);
            }

            text= builder.toString();
            System.out.println();
            // write response to log
            Log.w("Http Post Response:", text.toString());

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }


        try {
            JSONObject object=new JSONObject(text);
            JSONObject parent=object.getJSONObject("parent");
            String fullname=parent.getString("fullname");
            String parentid=parent.getString("id");
            String emaild=parent.getString("email");
            String mobilenumber=parent.getString("mobile");
            String profileimage=parent.getString("profile_image");
            System.out.println("===================childdetails"+fullname+profileimage);
            parentDatabase.insertparentdata(parentid,fullname,emaild,mobilenumber,profileimage);
            JSONArray childimage=object.getJSONArray("childs");
            childDatabase.removeAll();
            for(int i=0;i<childimage.length();i++) {
                JSONObject names=childimage.getJSONObject(i);
                if(i==0) {
                    firstchild = names.getString("id");
                }
                childid = names.getString("id");
                String childname=names.getString("fullname");
                String profilechildimage=names.getString("child_image");
                String milestone_id=names.getString("milestone_id");
                editor = sharedPreferences1.edit();
                editor.putString("childnameprofile",childname);
                editor.commit();
                System.out.println("---------childprofile"+childname+profilechildimage+childid);

//                if((childDatabase.getData(childid))!=null){
                    System.out.println("databsessssssss1");
                childDatabase.insertchilddata(childid,childname,milestone_id,profilechildimage);
//                }else{
//                    System.out.println("databsessssssss2");
//                    childDatabase.updatechilddata(childname,childid,"",profilechildimage);
//                }

                System.out.println("----------------"+childDatabase.getData("100"));

            }

    } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }
