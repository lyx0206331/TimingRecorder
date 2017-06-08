package com.adrian.timingrecorder.test.tabcloud;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.adrian.tabcloud3d.TagsAdapter;
import com.adrian.timingrecorder.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moxun on 16/1/19.
 */
public class TextTagsAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();

    public TextTagsAdapter(@NonNull String... data) {
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setTextSize(10);
        tv.setText("计划" + position);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "Tag " + position + " clicked.");
                Toast.makeText(context, "Tag " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "LongClick:" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        tv.setTextColor(Color.WHITE);
        Drawable greenDrawable = context.getResources().getDrawable(R.drawable.point_green);
        Drawable grayDrawable = context.getResources().getDrawable(R.drawable.point_gray);
        greenDrawable.setBounds(0, 0, greenDrawable.getMinimumWidth(), greenDrawable.getMinimumHeight());
        grayDrawable.setBounds(0, 0, grayDrawable.getMinimumWidth(), grayDrawable.getMinimumHeight());
        if (position % 2 == 0) {
            tv.setCompoundDrawables(null, greenDrawable, null, null);
        } else {
            tv.setCompoundDrawables(null, grayDrawable, null, null);
        }
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }
}