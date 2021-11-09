package com.example.employeedetails.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.employeedetails.Adapters.DeductionsAdapter;
import com.example.employeedetails.R;


public class DeductionsFragment extends Fragment {


    DeductionsAdapter deductionsAdapter;
    RecyclerView recyclerView;
    Button backbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_deductions, container, false);
        //        deductions fragment
        recyclerView=v.findViewById(R.id.deduction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deductionsAdapter=new DeductionsAdapter(getActivity());
        recyclerView.setAdapter(deductionsAdapter);
        backbutton=v.findViewById(R.id.deduction_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorFragment fragment=new CalculatorFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayoutContainer,fragment).commit();
            }
        });
        return v;
    }
}