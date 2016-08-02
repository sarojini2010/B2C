package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class LoginPage extends Activity implements Constant{
    SharedPreferences sharedPreferences;
    EditText phonenumber;
    SharedPreferences.Editor editor;
    Button login;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sharedPreferences=getSharedPreferences(USER_SESSION_ID, MODE_PRIVATE);
        phonenumber= (EditText) findViewById(R.id.phonenumber_edit);
        login=(Button) findViewById(R.id.login_btn);
        String number=sharedPreferences.getString("Phonenumber",null);
//        phonenumber.setText(number);
//        System.out.println("------------"+number);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginPage.this, OTPScreen.class);
                startActivity(in);
            }
        });
    }
}
