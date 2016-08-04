package com.example.thoughtchimp.dummyapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.AdapterDrawerList;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.Listadapter;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.NavDrawerListAdapter;
import android.app.ActionBar;
import io.fabric.sdk.android.Fabric;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    DrawerLayout drawerLayout;
    ListView mlistview,drawerlist2;
    private static final int Fredo = 0;
    private static final int Rate = 1;
    FragmentManager fragmentManager;
    private ArrayList<Childdetails> childdetailses=new ArrayList<>();
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.navigationlist);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
//        mlistview = (ListView) findViewById(R.id.lv_drawer);
        drawerlist2=(ListView) findViewById(R.id.lv_drawer2);
        String[] name= new String[]{"rohan", "karan", "something"};
        int[] images=new int[]{R.drawable.arrow,R.drawable.play_xhdpi,R.drawable.back_forword_hdpi};
        Childdetails child=new Childdetails();
        child.setName(name);
        child.setProfilepic(images);
        childdetailses.add(child);
        drawerlist2.setAdapter(new Listadapter(this,childdetailses)) ;
//       drawerlist2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent in=new Intent(getApplicationContext(),HomeFragment.class);
//               startActivity(in);
//           }
//       });


//        TextView txtName = (TextView) findViewById(R.id.t);
//        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.nav_drawer_items);


//        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[4];
////        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_tracking,"Track");
////        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_grn,"GRN Invoice Update");
//        drawerItem[0] = new ObjectDrawerItem(R.drawable.academy, "Fredo Milestone");
//        drawerItem[1] = new ObjectDrawerItem(R.drawable.academy, "Rate this app");
//        drawerItem[2] = new ObjectDrawerItem(R.drawable.academy, "Contact");
//        drawerItem[3] = new ObjectDrawerItem(R.drawable.academy, "Terms & Condition");

//        AdapterDrawerList adapterDrawerList = new AdapterDrawerList(MainActivity.this, R.layout.drawer_listview_row, drawerItem);
//        mlistview.setAdapter(adapterDrawerList);

//        SlidingMenuClickListenr ItemClickListener = new SlidingMenuClickListenr();
//        drawerlist2.setOnItemClickListener(ItemClickListener);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
//        selectItem(Fredo);

    }
//    private class SlidingMenuClickListenr implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            view.setSelected(true);
////            selectItem(position);
//        }
//    }
//    private void setFragment(Fragment fragment) {
//
//        if (fragmentManager == null)
//            fragmentManager = getFragmentManager();
//        if (fragment != null)
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//        else
//            Log.e("MainActivit", "Unable to create fragment");
//    }
//    public void selectItem(int position) {
//        drawerlist2.clearFocus();
//        drawerlist2.setSelection(position);
//        drawerlist2.setItemChecked(position, true);
//        Fragment fragment = null;
//
//        switch (position) {
//
//            case Fredo:
//                //fragment = new FragmentPricing();
//                break;
//            case Rate:
//
//
//                break;
//            default:
//                break;
//        }
//        setFragment(fragment);

//        drawerlist2.setItemChecked(position, true);
//        drawerlist2.setSelection(position);
//        drawerLayout.closeDrawer(Gravity.LEFT);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.manu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT))
                    drawerLayout.closeDrawer(Gravity.LEFT);
                else
                    drawerLayout.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }
        }


