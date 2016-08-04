package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import java.util.Map;

/**
 * Created by thoughtchimp on 7/28/2016.
 */
public class SessionDetails extends ActionBarActivity implements Constant {

    final String url =SessionDetailIp;
    String  audiourl=BaseUrl;
    String sesionurl,storyimages;
    TextView parent_note,doin_plan,resource,story;
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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        parent_note= (TextView) findViewById(R.id.parenttext);
        doinplan= (RelativeLayout) findViewById(R.id.doingplan);
        youtubelayout= (LinearLayout) findViewById(R.id.youtubelayout);
        musiclayout= (LinearLayout) findViewById(R.id.musiclayout);
        imglayout= (LinearLayout) findViewById(R.id.imagelayout);
        audioiamge= (ImageView) findViewById(R.id.audioimagelayout1);
        youtubeimages= (ImageView) findViewById(R.id.youtubeimagelayout1);

        doin_plan=(TextView) findViewById(R.id.doingtext);
        resource= (TextView) findViewById(R.id.parenttext);
        Bundle extras = getIntent().getExtras();
        final String sessionid = extras.getString("Sessionid");
        String sessionss=sessionid.substring(8);

        sesionurl=url+sessionss;
        System.out.println("-----url"+sesionurl);

        parent_note.setMovementMethod(new ScrollingMovementMethod());
        doin_plan.setMovementMethod(new ScrollingMovementMethod());
        layout = (LinearLayout) findViewById(R.id.linear);
//        imageView= (ImageView) findViewById(R.id.resourceimage);
//        for (int i = 0; i < 3; i++) {
////           imageView = new ImageView(this);
//            imageView.setId(i);
////            imageView.setPadding(15, 15, 15, 15);
////                b= BitmapFactory.decodeResource(getResources(), R.drawable.profile);
//            URL newurl ;
//            try {
//                newurl = new URL(UPLOADS_Image_URL);
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                b = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
//                options.inJustDecodeBounds = true;
////                int imageHeight = options.outHeight;
////                int imageWidth = options.outWidth;
////                String imageType = options.outMimeType;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            imageView.setImageBitmap(Bitmap.createScaledBitmap(b, 120, 120, false));

//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            layout.addView(imageView);
//
//        }
        String body="";

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("X-API-KEY","123456");
            httpget.addHeader("Authorization","Basic YWRtaW46MTIzNA==");
            httpget.addHeader("access-token","V49wH0yUXBQZuPMfshEqWgxbY_4");

            HttpResponse httpResponse = httpClient.execute(httpget);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);

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

        // try parse the string to a JSON object
        try {
            sessiondetails = new JSONObject(body);
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
                final String youtube = sessiondetails.getString("youtube_url");
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
                if(storyimages== null){
                    imglayout.setVisibility(View.GONE);
                }
                else {
                    imglayout.setVisibility(View.VISIBLE);
                }
                if (audio == null) {
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
                for (int i = 0; i < resourcearray.length(); i++) {
                    String value = (String) resourcearray.get(i);
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(value);
                    for (int j = 0; j < list.size(); j++) {
                        URL newurl;
                        final Bitmap bmp;
                        final String img ;
                        try {
                            newurl = new URL(BaseUrl + "/uploads/resource/" + value);
                            ImageView imageView2 = new ImageView(this);
                            imageView2.setId(i);
                            imageView2.setPadding(2, 2, 2, 2);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            bmp = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                            options.inJustDecodeBounds = true;
                            imageView2.setImageBitmap(Bitmap.createScaledBitmap(bmp, 250, 150, false));

//                            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                            layout.addView(imageView2);
                            imageView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("=========="+v.getId());
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
                        System.out.println("----" + list);
                    }
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }


//        doinplan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in=new Intent(SessionDetails.this,DoinPlanWebview.class);
//                in.putExtra("URL",webviewurl);
//                startActivity(in);
//            }
//        });

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


}