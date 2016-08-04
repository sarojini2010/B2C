package com.example.thoughtchimp.dummyapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.AdapterDrawerList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by thoughtchimp on 7/25/2016.
 */
public class Storylistener extends AppCompatActivity  {
    private Button forwardbutton,backwardbutton,start;
    private ImageView image;
    private Handler mHandler = new Handler();
    private Utilities utils;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 15000;
    private int backwardTime = 15000;
    private ProgressBar progress;
    public static int oneTimeOnly = 0;
    private TextView textcompleted,textduration;
    DrawerLayout drawerLayout;
    ListView mlistview;
    Bitmap b;
    String ImagesUrl="http://192.168.0.103/s2m-b2c/uploads/resource/";
    String storyurl="http://192.168.0.103/s2m-b2c/uploads/story/10_1468496103.mp3";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audioplayer);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mlistview = (ListView) findViewById(R.id.lv_drawer);
        forwardbutton = (Button) findViewById(R.id.btn_forward);
        backwardbutton = (Button) findViewById(R.id.btn_backward);
        start=(Button)findViewById(R.id.btn_stop);
        image=(ImageView)findViewById(R.id.audioiamge);
        textcompleted=(TextView) findViewById(R.id.complete_text);
        textduration=(TextView) findViewById(R.id.totalduration);
        progress=(ProgressBar) findViewById(R.id.seekBar);
//        mediaPlayer = MediaPlayer.create(this,R.raw.testing);


//        Intent in=getIntent();
        Bundle extras = getIntent().getExtras();
        final String imagesid = extras.getString("Images");
        String songurl= extras.getString("Song");
        try{
            mediaPlayer = MediaPlayer.create(this, Uri.parse("http://192.168.0.103/s2m-b2c/uploads/story/"+songurl));

        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
//        mediaPlayer.start();
        URL newurl ;
        try {
            newurl = new URL(ImagesUrl+imagesid);
            b = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(b);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);


        utils = new Utilities();

        progress.setClickable(false);
        mediaPlayer.start();
        finalTime=mediaPlayer.getDuration();
        startTime=mediaPlayer.getCurrentPosition();

        if (oneTimeOnly == 0) {
            progress.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

      start.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(mediaPlayer.isPlaying()){
                  if(mediaPlayer!=null){
                      mediaPlayer.pause();
                  }
              }
              else {
                  if(mediaPlayer!=null){
                      mediaPlayer.start();
                  }}
          }
      });

        textcompleted.setText(""+utils.milliSecondsToTimer((long) startTime));
        textduration.setText(""+utils.milliSecondsToTimer((long) finalTime));
        progress.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime,100);
        backwardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;
                int currentPosition = mediaPlayer.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if(currentPosition - backwardTime >= 0){
                    // forward song
                    mediaPlayer.seekTo(currentPosition - backwardTime);
                }else{
                    // backward to starting position
                    mediaPlayer.seekTo(0);
                }
            }
        });
        forwardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;
                int currentPosition = mediaPlayer.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if(currentPosition + forwardTime <= mediaPlayer.getDuration()){
                    // forward song
                    mediaPlayer.seekTo(currentPosition + forwardTime);
                }else{
                    // forward to end position
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            textcompleted.setText(""+utils.milliSecondsToTimer((long) startTime));
            progress.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

//    @Override
//    protected void onPause() {
//        super.onPause();
//        mediaPlayer.stop();
//        startTime = mediaPlayer.getCurrentPosition();
//       progress.setProgress((int) startTime);
//
//    }
}

