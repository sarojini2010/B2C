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
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class Signup  extends Activity implements Constant {
    EditText name,email,phonenumber;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Url=SIGNUPIP;
    String emailid,phone,names;
    TextView loginpage;
    Button signup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences(USER_SESSION_ID, MODE_PRIVATE);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final String emailids= sharedPreferences.getString("Email", null);
        String namess= sharedPreferences.getString("Name", null);
        String Ph= sharedPreferences.getString("Phonenumber", null);
//        if(emailids==null && namess==null && Ph==null) {
            setContentView(R.layout.signup);

            name = (EditText) findViewById(R.id.name_edit);
            email = (EditText) findViewById(R.id.email_edit);
            phonenumber = (EditText) findViewById(R.id.phonenumber_edit);
            signup = (Button) findViewById(R.id.signup_btn);
            loginpage = (TextView) findViewById(R.id.logindetails);


            editor = sharedPreferences.edit();

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    names = name.getText().toString();
                    emailid = email.getText().toString();
                    phone = phonenumber.getText().toString();

                    editor.putString("Name", names);
                    editor.putString("Email", emailid);
                    editor.putString("Phonenumber", phone);
                    editor.commit();

                    if ((emailValidator(emailid)) && validateNumber(phone)) {
                        if (names != null && emailid != null && phone != null) {
                            makePostRequest();
                            Intent in = new Intent(Signup.this, OTPScreen.class);
                            startActivity(in);
                            Toast.makeText(getApplicationContext(), "Login Sucess", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Signup.this, "please input valid email addrees and password", Toast.LENGTH_SHORT).show();
                    }


                }
            });
//        }
            loginpage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Signup.this, OTPScreen.class);
                    startActivity(in);
                }
            });
//        }
//        else {
//            Intent in = new Intent(Signup.this, MainProfile.class);
//            startActivity(in);
//        }

    }

    public static boolean emailValidator(final String mailAddress) {

        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();

    }
    public boolean validateNumber(String S) {
        String Regex = "[^\\d]";
        String PhoneDigits = S.replaceAll(Regex, "");
        return (PhoneDigits.length()==10);
    }
    private void makePostRequest() {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(Url);
        httpPost.addHeader("X-API-KEY","123456");


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("fullname",names));
        nameValuePair.add(new BasicNameValuePair("email", emailid));
        nameValuePair.add(new BasicNameValuePair("mobile",phone ));


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

            String text = builder.toString();
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

    }

}
