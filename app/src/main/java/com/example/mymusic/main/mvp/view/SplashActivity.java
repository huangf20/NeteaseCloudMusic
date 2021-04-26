package com.example.mymusic.main.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;

import com.example.mymusic.util.ActivityStarter;
import com.example.mymusic.util.ScreenUtils;
import com.example.mymusic.util.SharePreferenceUtil;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BasePresenter;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenUtils.setStatusBarColor(this, Color.parseColor("#91e46b"));
    }

    @Override
    protected void initData() {
        startCountDownTime();
    }

    private void startCountDownTime() {
        //millisInfuture：倒计时的总时长   countDownInterval：每次的时间间隔 单位均为毫秒
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                String authToken = SharePreferenceUtil.getInstance(SplashActivity.this).getAuthToken("");
                if (TextUtils.isEmpty(authToken)) {
                    ActivityStarter.getInstance().startLoginActivity(SplashActivity.this);
                } else {
                    ActivityStarter.getInstance().startMainActivity(SplashActivity.this);
                }
                SplashActivity.this.finish();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    protected void initModule() {

    }
    @Override
    public void onClick(View v) {

    }
}
