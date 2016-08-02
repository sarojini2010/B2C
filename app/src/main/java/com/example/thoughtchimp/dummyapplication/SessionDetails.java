package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetails);
//        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        parent_note= (TextView) findViewById(R.id.parenttext);
        doinplan= (RelativeLayout) findViewById(R.id.doingplan);
        doin_plan=(TextView) findViewById(R.id.doingtext);
        resource= (TextView) findViewById(R.id.parenttext);
        parent_note.setMovementMethod(new ScrollingMovementMethod());
        doin_plan.setMovementMethod(new ScrollingMovementMethod());
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i < 5; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(15, 15, 15, 15);
            final Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.profile);

            imageView.setImageBitmap(b);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            layout.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(SessionDetails.this, Imageshow.class);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    in.putExtra("display1", bytes);
                    startActivity(in);


                }
            });
        }
//        ImageRequest request = new ImageRequest(url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//
//                        imageView.setImageBitmap(bitmap);
//                    }
//                }, 0, 0, null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                        imageView.setImageResource(R.drawable.play_hdpi);
//                    }
//                });
//
//




        final RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------"+response);
                        try {
                            JSONObject jObj=new JSONObject(response);
                            String parentnote=jObj.getString("parent_note");
                            String doinplan=jObj.getString("doing_plan");
                            String youtube=jObj.getString("youtube_url");
                            String story=jObj.getString("story");
                            JSONArray resource=jObj.getJSONArray("resource");

//                            String image= String.valueOf(resource);
//                            int images=Integer.parseInt(image);
                            parent_note.setText(parentnote);

//                            imageView.setImageResource(image);
//                            layout.addView(imageView);
//                            webviewurl=doinplan;
                           doin_plan.setText(doinplan);

                            System.out.println("-----------checking"+parentnote+doinplan+youtube+story+"imagesdddd");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> httpget = new HashMap<>();
                httpget.put("X-API-KEY","123456");
                httpget.put("Authorization","Basic YWRtaW46MTIzNA==");
                httpget.put("access-token","6InFDMC1mYyvJ0QoxiL8dEUSj_2");
                //..add other headers
                return httpget;
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response.headers == null) {
                    // cant just set a new empty map because the member is final.
                    response = new NetworkResponse(
                            response.statusCode,
                            response.data,
                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                            response.notModified,
                            response.networkTimeMs);
                }

                return super.parseNetworkResponse(response);
            }
        };

        queue.add(stringRequest);
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