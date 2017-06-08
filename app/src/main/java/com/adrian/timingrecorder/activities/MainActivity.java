package com.adrian.timingrecorder.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adrian.flowingdrawer.ElasticDrawer;
import com.adrian.flowingdrawer.FlowingDrawer;
import com.adrian.tabcloud3d.TagCloudView;
import com.adrian.timingrecorder.R;
import com.adrian.timingrecorder.fragments.CompletedTaskFragment;
import com.adrian.timingrecorder.fragments.PlanTaskFragment;
import com.adrian.timingrecorder.fragments.ViewPagerAdapter;
import com.adrian.timingrecorder.database.PlanDao;
import com.adrian.timingrecorder.pojo.PlanInfo;
import com.adrian.timingrecorder.test.flowingdrawer.MenuListFragment;
import com.adrian.timingrecorder.test.tabcloud.TextTagsAdapter;
import com.adrian.timingrecorder.test.tabcloud.VectorTagsAdapter;
import com.adrian.timingrecorder.test.tabcloud.ViewTagsAdapter;
import com.adrian.timingrecorder.utils.CommonUtil;
import com.adrian.timingrecorder.utils.Constants;
import com.adrian.timingrecorder.views.datepicker.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FlowingDrawer mFlowingDrawer;
    private FloatingActionButton mAddFABtn;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private List<String> titles;
    private ViewPagerAdapter adapter;

    private TagCloudView tagCloudView;
    private TextTagsAdapter textTagsAdapter;
    private ViewTagsAdapter viewTagsAdapter;
    private VectorTagsAdapter vectorTagsAdapter;

    private PlanDao planDao;
    private long tempId;

    private CustomDatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        if (Build.VERSION.SDK_INT >= 21) {  //5.0以上支持效果
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE //顶部状态栏
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;  //底部导航栏
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mFlowingDrawer = (FlowingDrawer) findViewById(R.id.flowing_drawer);
        mAddFABtn = (FloatingActionButton) findViewById(R.id.fab);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mFlowingDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
        setupFeed();
        setupMenu();

        mAddFABtn.setOnClickListener(this);

        fragments = new ArrayList<>();
        fragments.add(PlanTaskFragment.newInstance("PlanTask"));
        fragments.add(CompletedTaskFragment.newInstance("CompleteTask"));
        titles = new ArrayList<>();
        titles.add("未完成");
        titles.add("已完成");
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);

        tagCloudView = (TagCloudView) findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.TRANSPARENT);

        textTagsAdapter = new TextTagsAdapter(new String[20]);
        viewTagsAdapter = new ViewTagsAdapter();
        vectorTagsAdapter = new VectorTagsAdapter();

        tagCloudView.setAdapter(textTagsAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.CHINA);
        String now = sdf.format(new Date());
        datePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            }
        }, now, "2099-12-31 23:59");// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        datePicker.showSpecificTime(true);// 显示时和分
        datePicker.setIsLoop(true);// 允许循环滚动
    }

    @Override
    protected void loadData() {
        planDao = new PlanDao(this);
//        planDao.testDB();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
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
//                Snackbar.make(v, "FAB", Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
                tempId = planDao.insertData("newPlan", System.currentTimeMillis(), System.currentTimeMillis(), 0, "计划描述aaaaaaaaaaaaaaaa");

                ((PlanTaskFragment) fragments.get(0)).updateData();
//                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN, Locale.CHINA);
//                String now = sdf.format(new Date());
//                datePicker.show(now);// 日期格式为yyyy-MM-dd HH:mm

//                long cur = System.currentTimeMillis();
//                String date = CommonUtil.millis2date("yyyy-MM-dd HH:mm", cur);
//                Log.e("TAG", "millis:" + cur + " date:" + date);
//                Log.e("TAG", CommonUtil.date2millis("yyyy-MM-dd HH:mm", date) + "");
                break;
        }
    }

    public List<PlanInfo> getAllPlans() {
        return planDao.getAllData();
    }

    public PlanInfo getNewPlan() {
        return planDao.getPlanById(tempId);
    }
}
