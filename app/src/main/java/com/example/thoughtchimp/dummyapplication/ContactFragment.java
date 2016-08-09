package com.example.thoughtchimp.dummyapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class ContactFragment extends FragmentActivity {
    Toolbar mtoolbar;
    ImageView contactbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
//        View rootView = inflater.inflate(R.layout.contactus, container, false);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}