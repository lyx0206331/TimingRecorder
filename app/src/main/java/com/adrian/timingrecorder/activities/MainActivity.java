package com.adrian.timingrecorder.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.adrian.flowingdrawer.ElasticDrawer;
import com.adrian.flowingdrawer.FlowingDrawer;
import com.adrian.timingrecorder.R;
import com.adrian.timingrecorder.test.flowingdrawer.MenuListFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mContainerRL;
    private FlowingDrawer mFlowingDrawer;
    private FloatingActionButton mAddFABtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main);
        mContainerRL = (RelativeLayout) findViewById(R.id.rl_main);
        mFlowingDrawer = (FlowingDrawer) findViewById(R.id.flowing_drawer);
        mAddFABtn = (FloatingActionButton) findViewById(R.id.fab);

        mFlowingDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
        setupFeed();
        setupMenu();

        mAddFABtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlowingDrawer.toggleMenu();
            }
        });
    }

    private void setupFeed() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
//            @Override
//            protected int getExtraLayoutSpace(RecyclerView.State state) {
//                return 300;
//            }
//        };
//        rvFeed.setLayoutManager(linearLayoutManager);
//        FeedAdapter feedAdapter = new FeedAdapter(this);
//        rvFeed.setAdapter(feedAdapter);
//        feedAdapter.updateItems();
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.fl_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.fl_container_menu, mMenuFragment).commit();
        }

//        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
//            @Override
//            public void onDrawerStateChange(int oldState, int newState) {
//                if (newState == ElasticDrawer.STATE_CLOSED) {
//                    Log.i("MainActivity", "Drawer STATE_CLOSED");
//                }
//            }
//
//            @Override
//            public void onDrawerSlide(float openRatio, int offsetPixels) {
//                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (mFlowingDrawer.isMenuVisible()) {
            mFlowingDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                Toast.makeText(this, "add btn", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "FAB", Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
                break;
        }
    }
}
