package com.adrian.timingrecorder.activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adrian.timingrecorder.R;

public class WelcomeActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(MainActivity.class);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    protected void loadData() {

    }
}
