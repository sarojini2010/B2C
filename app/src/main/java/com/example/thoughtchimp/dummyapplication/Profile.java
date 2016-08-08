package com.example.thoughtchimp.dummyapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ChildDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by thoughtchimp on 7/26/2016.
 */
public class Profile  extends AppCompatActivity implements Constant {
    private CollapsingToolbarLayout collapsingToolbar;
    EditText profilename,classname,Interest,schoolname;
    Context context;
    TextView dateView;
    Spinner gender,setdate1;
    String item;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
            String week;
    String Url=CHILDADD;
    Toolbar mtoolbar;

    public int currentDateView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editTor;
    ChildDatabase myDb;
    ImageView backbutton,addchild;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("ChildProfile3", 1);
        editTor = sharedPreferences.edit();
        final String name = sharedPreferences.getString("childname", null);
        myDb=new ChildDatabase(this);
        if (name == null) {
        setContentView(R.layout.profile2);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        profilename = (EditText) findViewById(R.id.textView_profile_name);
        classname = (EditText) findViewById(R.id.class_section);
        Interest = (EditText) findViewById(R.id.interest);
        schoolname = (EditText) findViewById(R.id.school);
            backbutton= (ImageView) findViewById(R.id.backbtn);
            addchild= (ImageView) findViewById(R.id.addchilddetails);



            mtoolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mtoolbar);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton editsave = (FloatingActionButton) findViewById(R.id.fabButton_edit_save);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");

        gender = (Spinner) findViewById(R.id.gender_spinner);
        dateView = (TextView) findViewById(R.id.datetext);
        String[] gendercategory = {"Male", "Female"};
        ArrayAdapter adapter = new ArrayAdapter(
                this, R.layout.customizespinner, gendercategory);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                item = parent.getItemAtPosition(position).toString();

//            Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleformat = new SimpleDateFormat("EEE, dd MMM yyyy ", Locale.US);
//        dateView.setText(DateUtil.getFormattedDate(day,month,year,DateUtil.MONTH_DAY_YEAR));
    }
        else {
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Profile.this,MainProfile.class);
                startActivity(in);
            }
        });

        addchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddetails();
            }
        });
        }


    public void onEditSave(View view) {
        Toast.makeText(this, "something", Toast.LENGTH_LONG).show();
    }

    public void setDate(View view) {
        currentDateView = view.getId();
        Calendar now = Calendar.getInstance();
        int date = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mdiDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String textViewDateStr = DateUtil.getFormattedDate(dayOfMonth, monthOfYear, year, DateUtil.YEAR_MONTH_DATE);
                //Toast.makeText(getApplicationContext(),year+ " "+monthOfYear+" "+dayOfMonth,Toast.LENGTH_LONG).show();
                dateView.setText(textViewDateStr);

            }
        }, year, month, date);
        mdiDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manu, menu);

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                adddetails();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public void adddetails() {
        String name = profilename.getText().toString();
//        String date=dateView.getText().toString();
//        String classes=classname.getText().toString();
//        String school=schoolname.getText().toString();
//        String intersted=Interest.getText().toString();
//        String genders=item.toString();
        makePostRequest();

        String contents=null;
        Cursor resultSet = myDb.getData(contents);
        if (resultSet.getCount()!=0){
            resultSet.moveToPosition(0);
            Intent in=new Intent(Profile.this,MainProfile.class);
            Bundle bnd=new Bundle();
            bnd.putString("Childname", resultSet.getString(1));
            System.out.println("childnameeeeeeeeeee"+resultSet.getString(1));

//        in.putExtra("name",name);
        startActivity(in);}
//        System.out.println("checking"+genders+intersted+date+classes+school+name);


    }


    private void makePostRequest() {
         int count = 1;
        String name=profilename.getText().toString();
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(Url);
        httpPost.addHeader("X-API-KEY","123456");
        httpPost.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
        httpPost.addHeader("access-token","V49wH0yUXBQZuPMfshEqWgxbY_4");

        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("fullname",name));
        nameValuePair.add(new BasicNameValuePair("birthdate", dateView.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("gender",item.toString()));
        nameValuePair.add(new BasicNameValuePair("school",schoolname.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("class",classname.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("interest",Interest.getText().toString()));
        myDb.insertchilddata(name);
//        editTor.putString("childname",name);
//        editTor.commit();
//        editTor.apply();

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

            String text = builder.toString();
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

    }
    public void SavePreferences(String key, String value) {
        // TODO Auto-generated method stub
        SharedPreferences data = getSharedPreferences("Childprofile2",1);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(key, value);
        editor.commit();


    }
    private void storeRecord(String Id, String Name) {
        final SharedPreferences prefs = getSharedPreferences("Childprofile",1);
        SharedPreferences.Editor editor = prefs.edit();
        String data = TextUtils.join(",", new String[]{Id, Name});
        editor.putString("record_" + Id, data);
        editor.commit();
    }

    private String[] getRecord(String Id, boolean usePrefix) {
        final SharedPreferences prefs =getSharedPreferences("Childprofile",1);
        String data = (String) prefs.getAll().get((usePrefix ? "record_" + Id : Id));
        return TextUtils.split(data, ",");
    }




}
