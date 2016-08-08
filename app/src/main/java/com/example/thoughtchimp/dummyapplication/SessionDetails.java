package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/28/2016.
 */
public class SessionDetails extends AppCompatActivity implements Constant {

    final String url =SessionDetailIp;
    String Sessiondoneurl=SESSIONDONE_URL;

    String sesionurl,storyimages,youtube;
    TextView parent_note,doin_plan,resource,story,sesionnumbers;
    RelativeLayout doinplan;
    LinearLayout youtubelayout,musiclayout,imglayout,layout;
    ViewPager viewPager;
    static String UPLOADS_Image_URL = "http://192.168.0.103/s2m-b2c/uploads/resource/13_1468496597.jpeg";
    static String UPLOADS_STORY_URL = SessionDetailIp + "/uploads/story/";
    String webviewurl;
    Context context;
    ImageView imageView,audioiamge,youtubeimages;
    InputStream is=null;
    JSONObject sessiondetails = null;
    Bitmap b;
    String sessionnumber;
    FragmentManager fragmentManager;
    ImageView backbutton3;
    Toolbar sessiontoolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        parent_note= (TextView) findViewById(R.id.parenttext);
        sesionnumbers=(TextView) findViewById(R.id.sesionnumber);
        doinplan= (RelativeLayout) findViewById(R.id.doingplan);
        youtubelayout= (LinearLayout) findViewById(R.id.youtubelayout11);
        musiclayout= (LinearLayout) findViewById(R.id.musiclayout);
        imglayout= (LinearLayout) findViewById(R.id.imagelayout);
        audioiamge= (ImageView) findViewById(R.id.audioimagelayout1);
        youtubeimages= (ImageView) findViewById(R.id.youtubeimagelayout1);
        backbutton3= (ImageView) findViewById(R.id.audiobackbtn);
        sessiontoolbar = (Toolbar) findViewById(R.id.sessiontoolbar);
        setSupportActionBar(sessiontoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        backbutton3= (ImageView) findViewById(R.id.sessionbackbtn);


        doin_plan=(TextView) findViewById(R.id.doingtext);
        resource= (TextView) findViewById(R.id.parenttext);
        Bundle extras = getIntent().getExtras();
        final String sessionid = extras.getString("Sessionid");
        sessionnumber =extras.getString("Sequencid");
        sesionnumbers.setText(sessionid);
        String sessionss=sessionid.substring(8);
        sesionurl=url+sessionss;
        System.out.println("-----url"+sesionurl);
        layout = (LinearLayout) findViewById(R.id.linear);
        String childurl=url.substring(url.lastIndexOf("=") + 1);
        System.out.println("sessionurl--------------"+childurl);
        int sessions=Integer.parseInt(sessionss);
        int sesin1=sessions-1;
        String sessiondetils=String.valueOf(sesin1);
        System.out.println("------------"+sessionid+sessiondetils);
        backbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HomeFragment llf = new HomeFragment();
                ft.replace(R.id.frame_container, llf);
                ft.commit();
            }
        });
        new Sessiondetail().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sessionmenu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                getSupportFragmentManager().popBackStackImmediate();
                return true;
            case R.id.sessiondone:
//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.mile1customdialog);
//                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
//                TextView text = (TextView) dialog.findViewById(R.id.text);
//
//                ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                image.setImageResource(R.drawable.play_hdpi);

//                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                sesssiondone();
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HomeFragment llf = new HomeFragment();
                ft.replace(R.id.frame_container, llf);
                ft.commit();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sesssiondone() {
        String childid=CHILDHOMEIP;
        String childId=childid.substring(childid.lastIndexOf("=") + 1);
//        String sessionid=sesionurl.substring(sesionurl.lastIndexOf("=") + 1);
//        System.out.println("------------childddddddd"+childId+sessionid);
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(Sessiondoneurl);
        httpPost.addHeader("X-API-KEY","123456");
        httpPost.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
        httpPost.addHeader("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");

        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("session_id",sessionnumber));
        nameValuePair.add(new BasicNameValuePair("child_id", childId));
        nameValuePair.add(new BasicNameValuePair("feedback","true"));

//        editTor.putString("birthdate",dateView.getText().toString());

        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder builder = new StringBuilder();
            String str = "";

            while ((str = rd.readLine()) != null) {
                builder.append(str);
            }

            String text = builder.toString();

            // write response to log
            Log.d("Http Post Response:", text.toString());
            // write response to log

        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//    public void storylistenr(View view){
//        Intent in= new Intent(this,Storylistener.class);
////        in.putExtra("Images",)
//        startActivity(in);
//
//    }
public class Sessiondetail extends AsyncTask<String,Void,String> implements Constant {

    ProgressDialog pd;
    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(SessionDetails.this);
        pd.setMessage("Please wait.");
        pd.show();
        super.onPreExecute();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {
        String body="";

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(sesionurl);
            httpget.addHeader("X-API-KEY","123456");
            httpget.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
            httpget.addHeader("access-token","V49wH0yUXBQZuPMfshEqWgxbY_4");

            HttpResponse httpResponse = httpClient.execute(httpget);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + sesionurl);

            }
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            body = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return body;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            sessiondetails = new JSONObject(s);
            System.out.println("---------------------------"+sessiondetails);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        try {
            String sessionids=sessiondetails.getString("session_id");
            String parentnote = sessiondetails.getString("parent_note");
            String doingplan = sessiondetails.getString("doing_plan");
            if (doingplan == null) {
                doinplan.setVisibility(View.GONE);
            } else {
                doinplan.setVisibility(View.VISIBLE);
                doin_plan.setText(doingplan);
            }
              youtube = sessiondetails.getString("youtube_url");
            System.out.println("---------"+youtube);
            if (youtube == null) {
                youtubelayout.setVisibility(View.GONE);
            } else {
                youtubelayout.setVisibility(View.VISIBLE);
                URL youtubeurl ;

                try {
                    youtubeurl = new URL("https://img.youtube.com/vi/"+youtube+"/sddefault.jpg");
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    b = BitmapFactory.decodeStream(youtubeurl.openConnection() .getInputStream());
                    youtubeimages.setImageBitmap(b);
                    youtubeimages.setScaleType(ImageView.ScaleType.FIT_XY);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                youtubelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Youtubeurl=youtube.toString();
                        System.out.println("youtubeurl"+Youtubeurl);
                        Intent in=new Intent(SessionDetails.this,Youtubevideo.class);
                        in.putExtra("Youtubeurl", Youtubeurl);
                        startActivity(in);
                    }
                });

            }
            JSONObject story = sessiondetails.getJSONObject("story");
            storyimages = story.getString("image");
            final String audio = story.getString("audio");
            if (story == null) {
                musiclayout.setVisibility(View.GONE);
            } else {
                musiclayout.setVisibility(View.VISIBLE);
                URL newurl ;
                try {
                    newurl = new URL(BaseUrl+"/uploads/story/"+storyimages);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    b = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                    audioiamge.setImageBitmap(b);
                    audioiamge.setScaleType(ImageView.ScaleType.FIT_XY);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                musiclayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String songurl= audio.toString();
                        System.out.println("checkingggggggggg song"+audio);
                        Intent song=new Intent(getApplicationContext(),Storylistener.class);
                        song.putExtra("Song", songurl);
                        song.putExtra("Images",storyimages);
                        startActivity(song);
                    }
                });
            }
            JSONArray resourcearray = sessiondetails.getJSONArray("resource");
            parent_note.setText(parentnote);
            doin_plan.setText(doingplan);
            if(resourcearray==null){
                imglayout.setVisibility(View.GONE);
            }else{
                imglayout.setVisibility(View.VISIBLE);

            for (int i = 0; i < resourcearray.length(); i++) {
                String value = (String) resourcearray.get(i);
                ArrayList<String> list = new ArrayList<String>();
                list.add(value);
//                for (int j = 0; j < list.size(); j++) {
                    URL newurl;
                    final Bitmap bmp;
                    final String img;
                    try {
                        newurl = new URL(BaseUrl+"/uploads/resource/"+value);
                        ImageView imageView2 = new ImageView(SessionDetails.this);
                        imageView2.setId(i);
                        imageView2.setPadding(0, 10, 40, 10);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        bmp = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        options.inJustDecodeBounds = true;
//                        Get size code
                        Display display = getWindowManager().getDefaultDisplay();
                        DisplayMetrics outMetrics = new DisplayMetrics();
                        display.getMetrics(outMetrics);

                        float density  = getResources().getDisplayMetrics().density;

                        double dpWidth  = (double) ((outMetrics.widthPixels)-50-110)/2.5;
                        int dpHeight = (int)(dpWidth/1.5);
                        System.out.println("width-> " + dpWidth + "height->" + dpHeight);


                        imageView2.setImageBitmap(Bitmap.createScaledBitmap(bmp, (int) dpWidth,dpHeight, false));

//                            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                        layout.addView(imageView2);
                        imageView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("==========" + v.getId());
                                Intent in = new Intent(SessionDetails.this, Imageshow.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                in.putExtra("display1", bytes);
                                startActivity(in);


                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                }
                    System.out.println("----" + list);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
            pd.cancel();

    }
}
}