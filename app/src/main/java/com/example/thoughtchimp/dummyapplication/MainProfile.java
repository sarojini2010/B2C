package com.example.thoughtchimp.dummyapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ParentDatabase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;

/**
 * Created by thoughtchimp on 8/6/2016.
 */
public class MainProfile  extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,Constant {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    ImageView parentform,parentimages;
    SharedPreferences sharedPreferences2,sharedPreferences;
    TextView parentame;
    String childname;
    String childimages;
    Bitmap b;
    String Name;
    ParentDatabase parentdata;
    String childids=FragmentDrawer.childid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ParentProfile.makePostRequest();


            setContentView(R.layout.activitymain2);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            parentform = (ImageView) findViewById(R.id.parentforms);
            parentame = (TextView) findViewById(R.id.parentnames);
            parentimages = (ImageView) findViewById(R.id.parentimage);
            parentdata= new ParentDatabase(MainProfile.this);
                Cursor Allparent = parentdata.getData("name");
                Allparent.moveToFirst();
                while (!Allparent.isAfterLast()) {
                    Name = Allparent.getString(1);
//                    String images = Allparent.getString(2);
                    Allparent.moveToNext();
                    System.out.println("===============parentname"+Name);
                }

            sharedPreferences2 = getSharedPreferences("ChildProfile3", MODE_PRIVATE);
            String profileimage = sharedPreferences2.getString("parentname", "");
            String parentname=sharedPreferences2.getString("profileimages","");
            parentame.setText(parentname);
            URL newurl;
            try {
                newurl = new URL(BaseUrl + "//uploads/profile/parent/" + profileimage);
                BitmapFactory.Options options = new BitmapFactory.Options();
                b = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                parentimages.setImageBitmap(b);
                parentimages.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            sharedPreferences2 = getSharedPreferences("ChildProfile3", 1);
            childname = sharedPreferences2.getString("childname", "");

            mToolbar = (Toolbar) findViewById(R.id.toolbar);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            drawerFragment = (FragmentDrawer)
                    getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
            drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
            drawerFragment.setDrawerListener(this);
            parentform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(MainProfile.this, ParentProfile.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(in);
                }
            });

            // display the first navigation drawer view on app launch
            displayView(0);

    }

//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menus, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//
//
//        return super.onOptionsItemSelected(item);
//    }
//



    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }
    private void displayView(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        int i1=0;
        position=i1;
        String title = getString(R.string.app_name);
//        switch (position) {
//                case 0:
//        for(int i=position;i<FragmentDrawer.arrayList.size();i++) {
//            fragment = new HomeFragment();
            args.putString("chilprrofile", FragmentDrawer.arrayList.get(position).childname);
            args.putString("childid", FragmentDrawer.arrayList.get(position).childid);
            fragment.setArguments(args);
            title = getString(R.string.homefragment);
//                    break;
//        }
//
//            case 1:
//                Intent in3 = new Intent(getApplicationContext(),
//                        FredoMilestone.class);
//                startActivity(in3);//
//                break;
//            case 2:
//               // fragment = new C();
////                title = getString(R.string.title_messages);
//                break;
//            case 3:
//                Intent in2 = new Intent(getApplicationContext(),
//                        ContactFragment.class);
//                startActivity(in2);//                title = getString(R.string.title_messages);
//                break;
//            case 4:
//                Intent in = new Intent(getApplicationContext(),
//                        TermsConditionFrgament.class);
//                startActivity(in);//                title = getString(R.string.title_messages);
//                break;
//            default:
//                break;
//        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}