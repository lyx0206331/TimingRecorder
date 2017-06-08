package com.adrian.timingrecorder.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adrian.timingrecorder.R;
import com.adrian.timingrecorder.activities.EditActivity;
import com.adrian.timingrecorder.activities.MainActivity;
import com.adrian.timingrecorder.adapters.PlanAdapter;
import com.adrian.timingrecorder.pojo.PlanInfo;
import com.adrian.timingrecorder.views.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";

    private RecyclerView mPlanListRV;
    private LinearLayoutManager linearLayoutManager;
    private List<PlanInfo> plans;
    private PlanAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String title;


    public PlanTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title
     * @return A new instance of fragment PlanTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanTaskFragment newInstance(String title) {
        PlanTaskFragment fragment = new PlanTaskFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_task, container, false);
        initViews(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initViews(View view) {
        mPlanListRV = (RecyclerView) view.findViewById(R.id.plan_rv);
        mPlanListRV.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mPlanListRV.setLayoutManager(linearLayoutManager);
        mPlanListRV.setItemAnimator(new DefaultItemAnimator());
        mPlanListRV.addItemDecoration(new MyItemDecoration());

        mAdapter = new PlanAdapter(getContext());
        mAdapter.setOnItemClickListener(new PlanAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v) {
//                Toast.makeText(getContext(), "id:" + v.getTag(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra("id", (int) v.getTag());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v) {

            }
        });
        mPlanListRV.setAdapter(mAdapter);

//        List<PlanInfo> plans = new ArrayList<>();
//        plans.add(new PlanInfo(1, "name1", System.currentTimeMillis(), System.currentTimeMillis(), 0, "备注:adasdfasdfasdfsdfs"));
//        plans.add(new PlanInfo(2, "name1", System.currentTimeMillis(), System.currentTimeMillis(), 0, "备注:adasdfasdfasdfsdfs"));
//        plans.add(new PlanInfo(3, "name1", System.currentTimeMillis(), System.currentTimeMillis(), 0, "备注:adasdfasdfasdfsdfs"));
//        plans.add(new PlanInfo(4, "name1", System.currentTimeMillis(), System.currentTimeMillis(), 0, "备注:adasdfasdfasdfsdfs"));
        plans = ((MainActivity) getActivity()).getAllPlans();
        mAdapter.setDatas(plans);
    }

    public void updateData() {
        mAdapter.addItem(((MainActivity) getActivity()).getNewPlan());
    }

}
