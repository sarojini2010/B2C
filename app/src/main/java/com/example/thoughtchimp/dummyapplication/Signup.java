package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thoughtchimp on 8/2/2016.
 */
public class Signup  extends Activity implements Constant {
    EditText name,email,phonenumber;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean isFirstTime=false;
    Button signup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences(USER_SESSION_ID, MODE_PRIVATE);
        String emailids= sharedPreferences.getString("Email", null);
        String namess= sharedPreferences.getString("Name", null);
        String Ph= sharedPreferences.getString("Phonenumber", null);
        if(emailids==null && namess==null && Ph==null) {
            setContentView(R.layout.signup);
            name = (EditText) findViewById(R.id.name_edit);
            email = (EditText) findViewById(R.id.email_edit);
            phonenumber = (EditText) findViewById(R.id.phonenumber_edit);
            signup = (Button) findViewById(R.id.signup_btn);


            editor = sharedPreferences.edit();

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String names = name.getText().toString();
                    String emailid = email.getText().toString();
                    String phone = phonenumber.getText().toString();
                    editor.putString("Name", names);
                    editor.putString("Email", emailid);
                    editor.putString("Phonenumber", phone);
                    editor.commit();

                    if ((emailValidator(emailid)) && validateNumber(phone)) {
                    if (names != null && emailid != null && phone != null) {
                        Intent in = new Intent(Signup.this, LoginPage.class);
                        startActivity(in);
                        Toast.makeText(getApplicationContext(),"Login Sucess",Toast.LENGTH_LONG).show();
                    }
                    }
                    else {
                        Toast.makeText(Signup.this, "please input valid email addrees and password", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else {
            Intent in = new Intent(Signup.this, LoginPage.class);
            startActivity(in);
        }

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

}
