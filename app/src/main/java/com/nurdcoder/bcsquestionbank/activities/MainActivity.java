package com.nurdcoder.bcsquestionbank.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nurdcoder.bcsquestionbank.R;
import com.nurdcoder.bcsquestionbank.adapters.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private TextView activity_main_content_layout_empty_tv;
    private RecyclerView activity_main_content_layout_main_rv;

    private String[] content;
    private String[] url;

    private MainAdapter mAdapter;
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        context = getApplicationContext();

        if (content.length == 0) {
            activity_main_content_layout_empty_tv.setVisibility(View.VISIBLE);
            activity_main_content_layout_main_rv.setVisibility(View.GONE);
        } else {
            mAdapter = new MainAdapter(activity, content);
            activity_main_content_layout_main_rv.setAdapter(mAdapter);
            activity_main_content_layout_empty_tv.setVisibility(View.GONE);
            activity_main_content_layout_main_rv.setVisibility(View.VISIBLE);
        }

            // Load an ad into the AdMob banner view.
            AdView adView = (AdView) findViewById(R.id.activity_main_content_layout_main_av);
            AdRequest adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template").build();
            adView.loadAd(adRequest);

            // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
            Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_main);
        content = getResources().getStringArray(R.array.question_bank);
        url = getResources().getStringArray(R.array.question_bank_url);
    }

    @Override
    void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_parent_tb);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(title);
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
        activity_main_content_layout_empty_tv = (TextView) findViewById(R.id.activity_main_content_layout_empty_tv);
    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {
        activity_main_content_layout_main_rv = (RecyclerView) findViewById(R.id.activity_main_content_layout_main_rv);
        activity_main_content_layout_main_rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        activity_main_content_layout_main_rv.setLayoutManager(mLayoutManager);
        activity_main_content_layout_main_rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    void initializeViewComponentsEventListeners() {
        activity_main_content_layout_main_rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
//                    Snackbar.make(child, "Process Under Development", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    Intent intent = new Intent(activity, WebViewActivity.class);
                    intent.putExtra("title", content[position]);
                    intent.putExtra("url", "file:///android_asset/" + url[position]);
                    startNextWithAnimation(intent, false);
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
        activity_main_content_layout_main_rv.addOnItemTouchListener(null);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                List<Intent> targetShareIntents = new ArrayList<Intent>();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(shareIntent, 0);
                String encodedTitle = Base64.encodeToString((getResources().getString(R.string.app_name) + "").getBytes(), Base64.DEFAULT);
                Log.e("encodedTitle", encodedTitle);
                String uri = "http://scirf.com/scirfblogs/share.php?q=" + encodedTitle;
//                uri = uri.replaceAll(" ", "%20");
                if (!resInfos.isEmpty()) {
                    Log.e("Package Name", "Have package");
                    for (ResolveInfo resInfo : resInfos) {
                        String packageName = resInfo.activityInfo.packageName;
                        Log.e("Package Name", packageName);

                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setPackage(packageName);

                        // Messengers
                        if (packageName.contains("com.android.mms")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.google.android.talk")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.google.android.apps.messaging")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.viber.voip")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.whatsapp")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.facebook.orca")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.imo.android.imoim")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.skype.raider")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        // Social Media
                        if (packageName.contains("com.twitter.android")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.facebook.katana")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.google.android.apps.plus")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            targetShareIntents.add(intent);
                        }

                        // Mail Client
                        if (packageName.contains("com.yahoo.mobile.client.android")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.microsoft.office.outlook")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                            targetShareIntents.add(intent);
                        }

                        if (packageName.contains("com.google.android.gm")) {
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, uri);
                            intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                            targetShareIntents.add(intent);
                        }
                    }
                    if (!targetShareIntents.isEmpty()) {
                        System.out.println("Have Intent");
                        Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), getString(R.string.share_title));
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                        startActivity(chooserIntent);
                    } else {
                        Log.e("Package Name", "Do not Have Intent");
                    }
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }
}
