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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
    SharedPreferences sharedPreferences;
    EditText phonenumber;
    String URL=LOGINIP;
    String phonenum;
    SharedPreferences.Editor editor;
    Button login;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sharedPreferences=getSharedPreferences(USER_SESSION_ID, MODE_PRIVATE);
        phonenumber= (EditText) findViewById(R.id.phone_edit);
        login=(Button) findViewById(R.id.login_btn);
        final String number=sharedPreferences.getString("Phonenumber",null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phonenum=phonenumber.getText().toString();
                makePostRequest();
                Intent in=new Intent(getApplicationContext(),OTPScreen.class);
                startActivity(in);
            }
        });
    }

    private void makePostRequest() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("X-API-KEY","123456");
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("mobile", phonenum));
        nameValuePair.add(new BasicNameValuePair("otp", "12345"));
        nameValuePair.add(new BasicNameValuePair("device_token","Tokenid"));


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

            String text = builder.toString();
            // write response to log
            Log.w("Http Post Response:", text.toString());

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

    }
}
