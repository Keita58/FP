package com.example.web;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowser extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_browser);

        Uri url=getIntent().getData();

        WebView webVista=(WebView)findViewById(R.id.webVista);

        webVista.setWebViewClient(new Callback());

        webVista.loadUrl(url.toString());

    }

    public class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_browser, menu);
        return true;
    }
}
