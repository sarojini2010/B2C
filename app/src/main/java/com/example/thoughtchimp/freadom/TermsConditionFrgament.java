package com.example.thoughtchimp.freadom;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/9/2016.
 */
public class TermsConditionFrgament  extends FragmentActivity {
    Toolbar mtoolbar;
    ImageView tncbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.termsandcondition);
        getActionBar().setDisplayHomeAsUpEnabled(true);
//        mtoolbar = (Toolbar)rootView.findViewById(R.id.tnctool);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mtoolbar);
//        tncbackbutton= (ImageView)rootView. findViewById(R.id.tnctback);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);




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
