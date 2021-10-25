package com.example.employeedetails.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.R;
import com.google.android.material.tabs.TabLayout;


public class oldRegimeGiudelinesFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_old_regime_giudelines, container, false);
        Button nextBtn=v.findViewById(R.id.nextIDE);
        tabLayout=v.findViewById(R.id.tablayout);
        viewPager = getParentFragment().getView().findViewById(R.id.viewpager);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fragmentManager=getChildFragmentManager();
//                fragmentManager.beginTransaction().addToBackStack("guidelines").replace(R.id.frameLayoutContainer,new HRAExcemptionFragment()).commit();
                viewPager.setCurrentItem(1,true);
            }
        });
        return v;
    }
}