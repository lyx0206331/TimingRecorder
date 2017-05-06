package com.adrian.timingrecorder.activities;

import android.support.v4.app.Fragment;

/**
 * 需要切换Fragment的Activity基类
 * Created by Yan Kai on 2015/10/23.
 */
public class BaseFragmentActivity extends BaseActivity {

    private Fragment fromFragment;

    protected void switchFragment(Fragment fragment, int layoutId) {
        if (null == fragment || fromFragment == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            if (null == fromFragment) {
                getSupportFragmentManager().beginTransaction().add(layoutId, fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(fromFragment).add(layoutId, fragment).commit();
            }
        } else {
            getSupportFragmentManager().beginTransaction().hide(fromFragment).show(fragment).commit();
        }
        fromFragment = fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fromFragment = null;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
