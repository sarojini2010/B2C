package com.example.thoughtchimp.freadom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by thoughtchimp on 8/3/2016.
 */
@SuppressLint("SetJavaScriptEnabled")

public class Youtubevideo extends ActionBarActivity  {
    WebView wv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeview);
        wv= (WebView) findViewById(R.id.youtubewebview);
        WebSettings webSettings = wv.getSettings();
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Bundle extras = getIntent().getExtras();
        String youtubeurl= extras.getString("Youtubeurl");
        wv.loadData("<html><body>Video<br><iframe width=\"350\" height=\"315\" src=\"https://www.youtube.com/embed/"+youtubeurl+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>","text/html", "utf-8");

    }
    @Override
    public void onPause(){
        super.onPause();
        try
        {
            if ( wv != null )
            {
                wv.clearCache(true);
                wv.getSettings().setAppCacheEnabled(false);
                wv.stopLoading();
                wv.destroy();
                wv = new WebView(this);
            }

            this.finish();
        }
        catch ( Exception e )

        {
            e.printStackTrace();
        }

        }
    @Override
    protected void onDestroy() {
        wv.destroy();
        wv = null;
        super.onDestroy();
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
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            // do something on back.
//            System.out.println("on finish");
//            wv.loadUrl("");
//            wv.removeAllViews();
//            wv.destroy();
//            wv = null;
//            finish();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}
