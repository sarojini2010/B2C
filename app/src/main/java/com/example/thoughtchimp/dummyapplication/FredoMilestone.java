package com.example.thoughtchimp.dummyapplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
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
public class FredoMilestone extends FragmentActivity {
    Toolbar mtoolbar;
    ImageView fredobackbutton;
    private ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fredomilestone);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.title_addgoal));
//
//        actionBar = getActivity().getActionBar();
//        mtoolbar = (Toolbar) rootView.findViewById(R.id.fredotools);
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
//        fredobackbutton = (ImageView) rootView.findViewById(R.id.fredoback);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//
//        fredobackbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                onBackPressed();
//            }
//        });
//
//        return rootView;

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            getActivity().finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
//    }
}