package com.example.thoughtchimp.dummyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by thoughtchimp on 8/1/2016.
 */
public class DoinPlanWebview  extends ActionBarActivity {

    private WebView webView;
    String url;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doinwebview);
        webView = (WebView) findViewById(R.id.doinwebview);
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.loadUrl(url);
    }

}
