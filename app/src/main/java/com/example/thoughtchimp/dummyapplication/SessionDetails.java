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
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.CustomAdapter;
import com.squareup.picasso.Picasso;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoughtchimp on 7/28/2016.
 */
public class SessionDetails extends ActionBarActivity implements Constant {

    final String url =SessionDetailIp;
    TextView parent_note,doin_plan,resource,story;
    private ImageLoader imageLoader;
    RelativeLayout doinplan;
    ImageView images;
    ViewPager viewPager;
    String webviewurl;
    private float scale = 1f;
    private ScaleGestureDetector SGD;
    Context context;
    ImageView imageView;
    InputStream is=null;
    JSONObject sessiondetails = null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        parent_note= (TextView) findViewById(R.id.parenttext);
        doinplan= (RelativeLayout) findViewById(R.id.doingplan);
        doin_plan=(TextView) findViewById(R.id.doingtext);
        resource= (TextView) findViewById(R.id.parenttext);
        parent_note.setMovementMethod(new ScrollingMovementMethod());
        doin_plan.setMovementMethod(new ScrollingMovementMethod());
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
//        for (int i = 0; i < 5; i++) {
//           imageView = new ImageView(this);
//            imageView.setId(i);
//            imageView.setPadding(15, 15, 15, 15);
//            final Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
//
//            imageView.setImageBitmap(b);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            layout.addView(imageView);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent in = new Intent(SessionDetails.this, Imageshow.class);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                    b.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                    byte[] bytes = stream.toByteArray();
//                    in.putExtra("display1", bytes);
//                    startActivity(in);
//
//
//                }
//            });
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
//            String sessionid=sessiondetails.getString("session_id");
            String parentnote=sessiondetails.getString("parent_note");
            String doingplan=sessiondetails.getString("doing_plan");
            if(doingplan==null){
                doin_plan.setVisibility(View.GONE);
            }
            else {
                doin_plan.setVisibility(View.VISIBLE);
                doin_plan.setText(doingplan);
            }
//            String youtube=sessiondetails.getString("youtube_url");
            JSONObject story=sessiondetails.getJSONObject("story");
            String images=story.getString("image");
            String audio=story.getString("audio");
            JSONArray resourcearray= sessiondetails.getJSONArray("resource");
            parent_note.setText(parentnote);
            doin_plan.setText(doingplan);
            Picasso.with(getApplicationContext()).load(images).into(imageView);
            layout.addView(imageView);

//            imageView.setImageResource(images);
//            layout.addView(imageView);

            for (int i = 0; i < resourcearray.length(); i++) {
                String value =   (String)resourcearray.get(i);

                System.out.println("----"+value);
            }
            System.out.println("checkinggggggggg"+parentnote+doingplan+images+audio);

        } catch (JSONException e) {
            e.printStackTrace();
        }

//
//        queue.add(stringRequest);
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
    public void storylistenr(View view){
        Intent in= new Intent(this,Storylistener.class);
        startActivity(in);

    }

}