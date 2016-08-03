package com.example.thoughtchimp.dummyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by thoughtchimp on 8/3/2016.
 */
public class Youtubevideo extends ActionBarActivity  {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeview);
        WebView wv= (WebView) findViewById(R.id.youtubewebview);
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
        Intent intent=getIntent();
        wv.loadUrl(intent.getStringExtra("<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/Youtubeurl\" frameborder=\"0\" allowfullscreen></iframe></body></html>"));

    }
}
