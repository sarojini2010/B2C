package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.impl.cookie.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by thoughtchimp on 7/26/2016.
 */
public class Profile  extends ActionBarActivity {
    private CollapsingToolbarLayout collapsingToolbar;
    TextView dateView;
    Context context;
    Spinner gender,setdate1;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
            String week;
    Activity activity;
    public int currentDateView;
    


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile2);
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState)
//        {
//        View rootView = inflater.inflate(R.layout.profile2, container, false);
//            Button setdate= (Button)findViewById(R.id.setdate1);
            FloatingActionButton editsave= (FloatingActionButton) findViewById(R.id.fabButton_edit_save);

//            context = container.getContext();
        collapsingToolbar =
                (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");

        gender= (Spinner) findViewById(R.id.gender_spinner);
        dateView= (TextView)findViewById(R.id.datetext);
        String[] gendercategory = { "Male","Female"};
        ArrayAdapter adapter = new ArrayAdapter(
                this,R.layout.customizespinner ,gendercategory);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String item = parent.getItemAtPosition(position).toString();

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
//        //week =calendar.get(Calendar.DAY_OF_WEEK);
//        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
       SimpleDateFormat simpleformat=new SimpleDateFormat("EEE, dd MMM yyyy ",Locale.US);
        dateView.setText(DateUtil.getFormattedDate(day,month,year,DateUtil.MONTH_DAY_YEAR));
//        week= dayFormat.format(calendar.getTime());
//        String month2=simpleformat.format(new Date());
//        System.out.println("-------------week"+month2);
//        showDate(year, month+1, day);
//            setdate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showDialog(999);
//                }
//            });
//        return rootView;
    }


    public void onEditSave(View view) {
        Toast.makeText(this,"something",Toast.LENGTH_LONG).show();
    }

    public void setDate(View view) {
        currentDateView = view.getId();
        Calendar now = Calendar.getInstance();
        int date=now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog  mdiDialog =new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String textViewDateStr = DateUtil.getFormattedDate(dayOfMonth,monthOfYear,year,DateUtil.MONTH_DAY_YEAR);
                //Toast.makeText(getApplicationContext(),year+ " "+monthOfYear+" "+dayOfMonth,Toast.LENGTH_LONG).show();
                dateView.setText(textViewDateStr);

            }
        }, year, month, date);
        mdiDialog.show();

    }


    protected android.app.Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        StringBuilder date=new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);

       // dateView.setText(DateFormat.getDateInstance().format(calendar.getTime()));
    }

//    @Override
//    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//        String textViewDateStr = DateUtil.getFormattedDate(dayOfMonth,monthOfYear,year,DateUtil.MONTH_DAY_YEAR);
//        String serverDateStr = DateUtil.getFormattedDate(dayOfMonth,monthOfYear,year,DateUtil.YEAR_MONTH_DATE);
//        dateView.setText(textViewDateStr);
//    }
}
