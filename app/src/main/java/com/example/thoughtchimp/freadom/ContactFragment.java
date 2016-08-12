package com.example.thoughtchimp.freadom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class ContactFragment extends AppCompatActivity {
    Toolbar mtoolbar;
    ImageView contactbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
        mtoolbar = (Toolbar) findViewById(R.id.contactollbar);
        setSupportActionBar(mtoolbar);
        contactbackbutton= (ImageView) findViewById(R.id.contactback);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//
        contactbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        mtoolbar = (Toolbar) rootView.findViewById(R.id.contactollbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
//        contactbackbutton = (ImageView) rootView.findViewById(R.id.contactback);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        contactbackbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                onBackPressed();
//            }
//        });
//
//        return rootView;

    }
//    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
//    }


}