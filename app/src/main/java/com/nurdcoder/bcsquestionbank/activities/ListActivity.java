package com.nurdcoder.bcsquestionbank.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nurdcoder.bcsquestionbank.adapters.ListAdapter;
import com.nurdcoder.bcsquestionbank.R;

public class ListActivity extends BaseActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private TextView activity_list_content_layout_empty_tv;
    private RecyclerView activity_list_content_layout_list_rv;

    private String[] content;

    private ListAdapter mAdapter;
    private Activity activity;
    private Context context;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        context = getApplicationContext();

        if (content.length == 0) {
            activity_list_content_layout_empty_tv.setVisibility(View.VISIBLE);
            activity_list_content_layout_list_rv.setVisibility(View.GONE);
        } else {
            mAdapter = new ListAdapter(activity, content);
            activity_list_content_layout_list_rv.setAdapter(mAdapter);
            activity_list_content_layout_empty_tv.setVisibility(View.GONE);
            activity_list_content_layout_list_rv.setVisibility(View.VISIBLE);
        }

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.activity_list_content_layout_list_av);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_list);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            content = b.getStringArray("content");
            title = b.getString("title");
        }
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_list_parent_tb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    void initializeEditTextComponents() {

    }

    @Override
    void initializeButtonComponents() {

    }

    @Override
    void initializeTextViewComponents() {
        activity_list_content_layout_empty_tv = (TextView) findViewById(R.id.activity_list_content_layout_empty_tv);
    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {
        activity_list_content_layout_list_rv = (RecyclerView) findViewById(R.id.activity_list_content_layout_list_rv);
        activity_list_content_layout_list_rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        activity_list_content_layout_list_rv.setLayoutManager(mLayoutManager);
        activity_list_content_layout_list_rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    void initializeViewComponentsEventListeners() {
        activity_list_content_layout_list_rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Snackbar.make(child, "Process Under Development", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    void removeViewComponentsEventListeners() {
        activity_list_content_layout_list_rv.addOnItemTouchListener(null);
    }

    @Override
    void exitThisWithAnimation() {
        finish();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }

    @Override
    void startNextWithAnimation(Intent intent, boolean isFinish) {
        if (isFinish) {
            finish();
        }
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_out);
    }

    @Override
    public void onBackPressed() {
        exitThisWithAnimation();
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
    public void onClick(View view) {

    }
}
