package com.adrian.timingrecorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adrian.timingrecorder.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanTaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_task, container, false);
    }

}
