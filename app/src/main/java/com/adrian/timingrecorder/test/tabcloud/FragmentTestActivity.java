package com.adrian.timingrecorder.test.tabcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adrian.timingrecorder.R;

public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, TestFragment.newInstance())
                .commit();
    }
}
