package com.nurdcoder.bcsquestionbank.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate(Bundle savedInstanceState)");
        super.onCreate(savedInstanceState);
        setupViewComponents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeViewComponentsEventListeners();
    }

    protected void setupViewComponents() {
        Log.d(TAG, "initializeViewComponents()");
        setContentView();
        setupActionBar();
        initializeEditTextComponents();
        initializeButtonComponents();
        initializeTextViewComponents();
        initializeImageViewComponents();
        initializeOtherViewComponents();
        initializeViewComponentsEventListeners();
    }

    abstract void setContentView();

    abstract void setupActionBar();

    abstract void initializeEditTextComponents();

    abstract void initializeButtonComponents();

    abstract void initializeTextViewComponents();

    abstract void initializeImageViewComponents();

    abstract void initializeOtherViewComponents();

    abstract void initializeViewComponentsEventListeners();

    abstract void removeViewComponentsEventListeners();

    abstract void exitThisWithAnimation();

    abstract void startNextWithAnimation(Intent intent, boolean isFinish);
}
