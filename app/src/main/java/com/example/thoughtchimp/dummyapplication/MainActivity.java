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
    ListView mlistview;
    private static final int Home = 0;
    private static final int Profile = 1;
    FragmentManager fragmentManager;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.navigationold);
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                R.drawable.ic_nav_icon,R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    /* *
//     * Called when invalidateOptionsMenu() is triggered
//     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
//                fragment = new HomeFragment();
                break;
            case 1:
//              fragment=new Profile();

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
//        mlistview = (ListView) findViewById(R.id.lv_drawer);
//        TextView txtName = (TextView) findViewById(R.id.txtname);
//        TextView txtEmail = (TextView) findViewById(R.id.txtemail);
//        txtName.setText("Rohan");
//        txtEmail.setText("something@gmail.com");
//        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.nav_drawer_icons);
//
//        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[2];
////        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_tracking,"Track");
////        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_grn,"GRN Invoice Update");
//        drawerItem[0] = new ObjectDrawerItem(R.drawable.academy, "Home");
//        drawerItem[1] = new ObjectDrawerItem(R.drawable.arrow, "Profile");
//
//        AdapterDrawerList adapterDrawerList = new AdapterDrawerList(MainActivity.this, R.layout.drawer_listview_row, drawerItem);
//        mlistview.setAdapter(adapterDrawerList);
//
//        SlidingMenuClickListenr ItemClickListener = new SlidingMenuClickListenr();
//        mlistview.setOnItemClickListener(ItemClickListener);
//
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle("Profile");
//        selectItem(Home);
//
////        SessionText= (TextView) findViewById(R.id.session_text);
////        ReccoText=(TextView) findViewById(R.id.recco_text);
////        SessionArcText= (TextView) findViewById(R.id.archieve_text);
////        progress= (ProgressBar) findViewById(R.id.progressBar);
////        btn_score=(Button)findViewById(R.id.button_score);
////        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
////        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAllUsers);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        final AllUsersAdapter allUserAdapter = new AllUsersAdapter(this, userlist);
//////       recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.INVALID_OFFSET));
////        recyclerView.setAdapter(allUserAdapter);
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////
////       SessionArcText.setOnClickListener(new View.OnClickListener() {
////           @Override
////           public void onClick(View v) {
////               Intent in=new Intent(MainActivity.this,SessionArchive.class);
////               startActivity(in);
////           }
////       });
//
////        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        System.out.println("------------------"+response);
////                        try {
////                            JSONObject jObj=new JSONObject(response);
////                            int sesion=jObj.getInt("session_completed");
////                            String Karmascore=jObj.getString("karma_score");
////                            String Recco=jObj.getString("recco");
////                            String Session_archieve=jObj.getString("session_total");
////                            JSONArray jsonArray=jObj.getJSONArray("sessions");
////                            for(int i=0;i<jsonArray.length();i++){
////                                JSONObject id=jsonArray.getJSONObject(i);
////                                User user=new User();
////                                String title1=id.getString("id");
////                //                String title2=title1.substring(7);
////                //                System.out.println("PPPPPPPPPPP"+title2);
////                //                String title3=title1;
////                //                System.out.println("____________"+title3);
////                                user.setUserEmail(title1);
////                                user.setUserName(id.optString("title"));
////                                user.setUserMobile(id.optString("parent_note"));
////                                user.setImageResourceId(R.drawable.arrow_hdpi);
////                                userlist.add(user);
////                                allUserAdapter.notifyDataSetChanged();
////                            }
////                            ReccoText.setText(Recco);
////                            progress.setMax(50);
////                            progress.setProgress(sesion);
////                            btn_score.setText(Karmascore);
////                            SessionText.setText(+sesion +" Sessions Completed");
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////            }
////        }){
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String,String> httpget = new HashMap<>();
////                httpget.put("X-API-KEY","123456");
////                httpget.put("Authorization","Basic YWRtaW46MTIzNA==");
////                httpget.put("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");
////                //..add other headers
////                return httpget;
////            }
////            @Override
////            protected Response<String> parseNetworkResponse(NetworkResponse response) {
////                if (response.headers == null) {
////                    // cant just set a new empty map because the member is final.
////                    response = new NetworkResponse(
////                            response.statusCode,
////                            response.data,
////                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
////                            response.notModified,
////                            response.networkTimeMs);
////                }
////
////                return super.parseNetworkResponse(response);
////            }
////        };
////
////        queue.add(stringRequest);
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.manu, menu);//Menu Resource, Menu
////        return true;
////
////    }
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.item1:
////                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
//private class SlidingMenuClickListenr implements AdapterView.OnItemClickListener {
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        view.setSelected(true);
//        selectItem(position);
//    }
//}
//private void selectItem(int position) {
//    mlistview.clearFocus();
//    mlistview.setSelection(position);
//    mlistview.setItemChecked(position, true);
//    Fragment fragment = null;
//
//    switch (position) {
//        case Home:
//                fragment = new HomeFragment();
//            break;
//        case Profile:
//            fragment = new Profile();
//            break;
//        default:
//            break;
//    }
//    setFragment(fragment);
//
//    mlistview.setItemChecked(position, true);
//    mlistview.setSelection(position);
//    drawerLayout.closeDrawer(Gravity.LEFT);
//}
//
//    private void setFragment(Fragment fragment) {
//        if (fragmentManager == null)
//            fragmentManager = getFragmentManager();
//        if (fragment != null)
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//        else
//            Log.e("MainActivity", "Unable to create fragment");
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//
//
//            case android.R.id.home:
//                if (drawerLayout.isDrawerOpen(Gravity.LEFT))
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                else
//                    drawerLayout.openDrawer(Gravity.LEFT);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//


}
