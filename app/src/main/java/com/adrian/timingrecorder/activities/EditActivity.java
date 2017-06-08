package com.adrian.timingrecorder.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adrian.timingrecorder.R;
import com.adrian.timingrecorder.utils.Constants;
import com.adrian.timingrecorder.views.datepicker.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends BaseActivity {

    private CustomDatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        setTitle(R.string.edit);
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.CHINA);
        String now = sdf.format(new Date());
        datePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(EditActivity.this, time, Toast.LENGTH_SHORT).show();
            }
        }, now, "2099-12-31 23:59");// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        datePicker.showSpecificTime(true);// 显示时和分
        datePicker.setIsLoop(true);// 允许循环滚动
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
