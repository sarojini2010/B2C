package com.example.thoughtchimp.dummyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by thoughtchimp on 8/4/2016.
 */
public class SplashScreen extends Activity {
    private boolean active = true;

    private int elapsed = 0;

    private static int SPLASH_TIME_OUT = 3000;
    private static int SPLASH_INTERVAL = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int elapsed = 0;
                    while (SplashScreen.this.active && (elapsed < SPLASH_TIME_OUT)) {
                        Thread.sleep(SPLASH_INTERVAL);
                        if (SplashScreen.this.active)
                            elapsed += SPLASH_INTERVAL;
                    }
                } catch (InterruptedException e) {
                    // Nothing
                } finally {
                    finish();
                    if (SplashScreen.this.active) {
                        Intent launchIntent = new Intent(SplashScreen.this,HomeFragment.class);
                        Log.d("Splash", "Launching the main activity now");
                        startActivity(launchIntent);
                    }
                }
            }
        }).start();
    }

    protected void onDestroy() {
        Log.d("Splash", "onDestroy");
        this.active = false;
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent anEvent) {
        if (anEvent.getAction() == MotionEvent.ACTION_DOWN) {
            elapsed = SPLASH_TIME_OUT;
            return true;
        }
        return false;
    }
}