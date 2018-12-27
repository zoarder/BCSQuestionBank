package com.nurdcoder.bcsquestionbank.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nurdcoder.bcsquestionbank.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Developer on 1/25/2017.
 */

public class WebViewActivity extends BaseActivity {
    private static final String TAG = "WebViewActivity";
    private WebView activity_webactivity_webview;

    private Activity activity;
    private Context context;
    private ProgressDialog pDialog;
    private String URL = null;
    private String Title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            URL = b.getString("url");
            Title = b.getString("title");
        }

        Log.e(TAG, "url: " + URL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_web_view_parent_tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Title);
        activity = this;
        context = getApplicationContext();

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.activity_web_view_content_layout_main_av);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        activity_webactivity_webview = (WebView) findViewById(R.id.activity_web_view_content_layout_main_wv);
        activity_webactivity_webview.getSettings().setJavaScriptEnabled(true);
        activity_webactivity_webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pDialog.setMessage("Loading...Please Wait...");
                showDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideDialog();
            }
        });
        activity_webactivity_webview.loadUrl(URL);
    }

    @Override
    void setContentView() {

    }

    @Override
    void setupActionBar() {

    }

    @Override
    void initializeEditTextComponents() {

    }

    @Override
    void initializeButtonComponents() {

    }

    @Override
    void initializeTextViewComponents() {

    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {

    }

    @Override
    void initializeViewComponentsEventListeners() {

    }

    @Override
    void removeViewComponentsEventListeners() {

    }

    @Override
    void exitThisWithAnimation() {
        finish();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }

    @Override
    void startNextWithAnimation(Intent intent, boolean isFinish) {

    }

    public void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                exitThisWithAnimation();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        exitThisWithAnimation();
    }
}
