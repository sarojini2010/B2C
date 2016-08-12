package com.example.thoughtchimp.freadom;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class FredoMilestone extends AppCompatActivity {
    Toolbar mtoolbar;
    ImageView fredobackbutton;
    private ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fredomilestone);
        mtoolbar = (Toolbar) findViewById(R.id.fredotolbar);
        setSupportActionBar(mtoolbar);
        fredobackbutton= (ImageView) findViewById(R.id.fredoback);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//
        fredobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        return rootView;

    }
//

//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            getActivity().finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
//    }
}