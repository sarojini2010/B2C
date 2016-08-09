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
public class ContactFragment extends Fragment {
    Toolbar mtoolbar;
    ImageView contactbackbutton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contactus, container, false);
        mtoolbar = (Toolbar) rootView.findViewById(R.id.contactollbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mtoolbar);
        contactbackbutton = (ImageView) rootView.findViewById(R.id.contactback);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        contactbackbutton.setOnClickListener(new View.OnClickListener() {
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