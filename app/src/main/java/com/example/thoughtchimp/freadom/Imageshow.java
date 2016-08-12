package com.example.thoughtchimp.freadom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by thoughtchimp on 8/1/2016.
 */
public class Imageshow extends ActionBarActivity {
    ImageView view;
    Button deletebutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.largeimage);

        view = (ImageView) findViewById(R.id.expanded_image);
        deletebutton= (Button) findViewById(R.id.crossbutton);
//        Intent in=getIntent();
//        Bundle extras = getIntent().getExtras();
//        byte[] bytes = getIntent().getByteArrayExtra("display1");
        byte[] byteArray = getIntent().getByteArrayExtra("display1");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        view.setImageBitmap(bmp);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}





