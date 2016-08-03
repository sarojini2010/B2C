package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/1/2016.
 */
public class Imageshow extends ActionBarActivity {
    ImageView view;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.largeimage);
        view = (ImageView) findViewById(R.id.expanded_image);
        Intent in=getIntent();
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("display1");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        view.setImageBitmap(bmp);

    }

}





