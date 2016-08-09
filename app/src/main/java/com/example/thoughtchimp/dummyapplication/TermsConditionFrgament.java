package com.example.thoughtchimp.dummyapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class TermsConditionFrgament  extends Fragment {
    Toolbar mtoolbar;
    ImageView tncbackbutton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.termsandcondition, container, false);
        mtoolbar = (Toolbar)rootView.findViewById(R.id.tnctool);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
        tncbackbutton= (ImageView)rootView. findViewById(R.id.tnctback);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        tncbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
            }
        });

        return rootView;

    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            getActivity().finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
