package com.example.thoughtchimp.dummyapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
    private ArrayList<ObjectDrawerItem>list;
    SharedPreferences sharedPreferences;
    String childname;
    ImageView parentdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.navigationlist);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        parentdetails= (ImageView) findViewById(R.id.parentform);
        mlistview = (ListView) findViewById(R.id.lv_drawer);
        sharedPreferences=getSharedPreferences("ChildProfile2",1);
        list = new ArrayList<ObjectDrawerItem>();
        int count=0;
        childname=sharedPreferences.getString("childname","");
        int counter = sharedPreferences.getInt("counter", 0); // Using '0' for the default value

        Map<String, ?> allEntries = sharedPreferences.getAll();

        Map<String, String> m = (Map<String, String>) sharedPreferences.getAll();
        List<String> list1 = new ArrayList<>(m.values());
        System.out.println("-------hhjhjhj------"+list1);
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            Log.d("map values", allEntries.entrySet().toString());
//            List<String> list=new ArrayList<>()
//            Log.d("map valuessssssssssssss", entry.getKey() + ": " + entry.getValue().toString());
//
//
//        }

//        System.out.println("------------childname"+ getRecord("childs",true));
        parentdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,ParentProfile.class);
                startActivity(in);
            }
        });
//        drawerlist2=(ListView) findViewById(R.id.lv_drawer2);
//        String[] name= new String[]{"rohan", "karan", "something"};
//        int[] images=new int[]{R.drawable.arrow,R.drawable.play_xhdpi,R.drawable.back_forword_hdpi};
//        Childdetails child=new Childdetails();
//        child.setName(name);
//        child.setProfilepic(images);
//        childdetailses.add(child);
//        drawerlist2.setAdapter(new Listadapter(this,childdetailses)) ;
//       drawerlist2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent in=new Intent(getApplicationContext(),HomeFragment.class);
//               startActivity(in);
//           }
//       });



//      mNavigationDrawerItemTitles = getResources().getStringArray(R.array.nav_drawer_items);


//        ObjectDrawerItem drawerItem ;
//        drawerItem[0] = new ObjectDrawerItem(R.drawable.academy,"");
//        drawerItem[1] = new ObjectDrawerItem(R.drawable.academy,"");

        for(int i=0;i<allEntries.keySet().size();i++) {
            list.add(new ObjectDrawerItem(R.drawable.academy, childname));
        }
        list.add( new ObjectDrawerItem(R.drawable.academy, "Fredo Milestone"));
        list.add(new ObjectDrawerItem(R.drawable.academy, "Rate this app"));
        list.add(new ObjectDrawerItem(R.drawable.academy, "Contact"));
        list.add(new ObjectDrawerItem(R.drawable.academy, "Terms & Condition"));
//        list.add(drawerItem);

        AdapterDrawerList adapterDrawerList = new AdapterDrawerList(MainActivity.this, R.layout.drawer_listview_row, list);
        mlistview.setAdapter(adapterDrawerList);

        SlidingMenuClickListenr ItemClickListener = new SlidingMenuClickListenr();
        mlistview.setOnItemClickListener(ItemClickListener);
//        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
//                R.drawable.ic_drawer, //nav menu toggle icon
//                R.string.app_name, // nav drawer open - description for accessibility
//                R.string.app_name);
//        drawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        selectItem(0);

    }
    private class SlidingMenuClickListenr implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setSelected(true);
            selectItem(position);
        }
    }
    private void setFragment(Fragment fragment) {

        if (fragmentManager == null)
            fragmentManager =getSupportFragmentManager() ;
        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        else
            Log.e("MainActivity", "Unable to create fragment");
    }
    public void selectItem(int position) {
        mlistview.clearFocus();
        mlistview.setSelection(position);
        mlistview.setItemChecked(position, true);
        Fragment fragment = null;
        Bundle args = new Bundle();

        switch (position) {

            case 0:
                fragment=new HomeFragment();
                args.putString("chilprrofile",childname);
                fragment.setArguments(args);
                break;
            case 1:


                break;
            default:
                break;
        }
        setFragment(fragment);

        mlistview.setItemChecked(position, true);
        mlistview.setSelection(position);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
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
    private String[] getRecord(String Id, boolean usePrefix) {
        final SharedPreferences prefs =getSharedPreferences("Childprofile",1);
        String data = (String) prefs.getAll().get((usePrefix ? "record_" + Id : Id));
        return TextUtils.split(data, ",");
    }
        }


