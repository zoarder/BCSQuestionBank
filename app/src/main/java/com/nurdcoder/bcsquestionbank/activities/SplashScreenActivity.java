package com.nurdcoder.bcsquestionbank.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nurdcoder.bcsquestionbank.R;

/**
 * Created by ZOARDER AL MUKTADIR on 11/21/2016.
 */

public class SplashScreenActivity extends BaseActivity {

    private ImageView mAppLogoImageView;
    private ProgressBar mDataLoadingProgressBar;
    private TextView mDataLoadingTextView;
    private int mProgressBarIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressBarIndex = 0;

        new CountDownTimer(5000, 100) {

            public void onTick(long millisUntilFinished) {
                onProgressDialog(getString(R.string.text_loading), mProgressBarIndex += 2);
            }

            public void onFinish() {
                onProgressDialog(getString(R.string.text_finished), 100);
                onTimeSpent();
            }
        }.start();

    }

    @Override
    void setContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
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
        mDataLoadingTextView = (TextView) findViewById(R.id.activity_splash_screen_data_loading_tv);

    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {
        mAppLogoImageView = (ImageView) findViewById(R.id.activity_splash_screen_logo_iv);
        mDataLoadingProgressBar = (ProgressBar) findViewById(R.id.activity_splash_screen_data_loading_pb);
        Animation shake = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.shake_1);
        mAppLogoImageView.startAnimation(shake);
    }

    @Override
    void initializeViewComponentsEventListeners() {

    }

    @Override
    void removeViewComponentsEventListeners() {

    }

    @Override
    void exitThisWithAnimation() {

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

    private void onTimeSpent() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startNextWithAnimation(intent, true);
    }

    private void onProgressDialog(String progressText, int totalProgress) {
        mDataLoadingTextView.setText(progressText);
        mDataLoadingProgressBar.setProgress(totalProgress);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
    }
}
